package com.zenika.android.tools.db;

import static android.provider.BaseColumns._ID;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.BaseColumns;
import com.zenika.android.tools.utils.Nulls;
import com.zenika.android.tools.utils.Arrays;

public abstract class AbstractContentProvider extends ContentProvider {

	public static final String		TAG							= AbstractContentProvider.class.getSimpleName() + " - ";

	private static final String		PROVIDER_AUTHORITY_PREFIX	= "com.zenika.provider.";
	private static String			sAuthority;

	private static final String		ANDROID_BASE_TYPE			= "vnd.android.cursor.";
	private static final String		ANDROID_DIR_TYPE			= ANDROID_BASE_TYPE + "dir/";
	private static final String		ANDROID_ITEM_TYPE			= ANDROID_BASE_TYPE + "item/";

	private static final String		ZEN_BASE_TYPE				= "vnd.zenika.";
	private static final String		ZEN_DIR_TYPE				= ANDROID_DIR_TYPE + ZEN_BASE_TYPE;
	private static final String		ZEN_ITEM_TYPE				= ANDROID_ITEM_TYPE + ZEN_BASE_TYPE;
	private static String			sDirTypePrefix;
	private static String			sItemTypePrefix;

	protected static final String	BASE_URI_PREFIX				= "content://" + PROVIDER_AUTHORITY_PREFIX;

	private SQLiteDatabase			mDatabase;
	private final static UriMatcher	sUriMatcher;

	static {
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	}

	@Override
	public boolean onCreate() {
		SQLiteOpenHelper helper = new DatabaseOpenHelper(getContext(), getDatabaseName(), getDatabaseVersion(), getTables());

		int i = 0;
		for(ITable table : getTables()) {
			sUriMatcher.addURI(getAuthority(), table.getName(), i++);
			sUriMatcher.addURI(getAuthority(), table.getName() + "/#", i++);
		}

		mDatabase = helper.getWritableDatabase();
		return mDatabase != null;
	}

	@Override
	public String getType(Uri uri) {
		int match = sUriMatcher.match(uri);
		if(match < 0) throw new IllegalArgumentException("Unsupported Uri: " + uri);

		ITable table = getTable(match);
		if(isId(match)) {
			return getItemTypePrefix() + table.getName();
		}
		return getDirTypePrefix() + table.getName();
	}

	private ITable getTable(int match) {
		return getTables()[match / 2];
	}

	private boolean isId(int match) {
		return match % 2 == 1;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		int match = sUriMatcher.match(uri);
		if(match < 0) throw new IllegalArgumentException("Unsupported Uri: " + uri);

		ITable table = getTable(match);

		if(isId(match)) {
			throw new IllegalArgumentException("Unsupported Single Row Uri: " + uri);
		}

		long id = mDatabase.insertOrThrow(table.getName(), null, values);
		Uri newUri = ContentUris.withAppendedId(uri, id);

		getContext().getContentResolver().notifyChange(newUri, null);

		return newUri;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int match = sUriMatcher.match(uri);
		if(match < 0) throw new IllegalArgumentException("Unsupported Uri: " + uri);

		ITable table = getTable(match);

		StringBuilder sb = new StringBuilder();
		String[] args = setSelectionArgs(match, uri, selection, selectionArgs, sb);

		int count = mDatabase.delete(table.getName(), sb.toString(), args);

		getContext().getContentResolver().notifyChange(uri, null);

		return count;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		int match = sUriMatcher.match(uri);
		if(match < 0) throw new IllegalArgumentException("Unsupported Uri: " + uri);

		ITable table = getTable(match);

		StringBuilder sb = new StringBuilder();
		String[] args = setSelectionArgs(match, uri, selection, selectionArgs, sb);

		int count = mDatabase.update(table.getName(), values, sb.toString(), args);

		getContext().getContentResolver().notifyChange(uri, null);

		return count;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		int match = sUriMatcher.match(uri);
		if(match < 0) throw new IllegalArgumentException("Unsupported Uri: " + uri);

		ITable table = getTable(match);

		StringBuilder sb = new StringBuilder();
		String[] args = setSelectionArgs(match, uri, selection, selectionArgs, sb);

		Cursor c = mDatabase.query(table.getName(), projection, sb.toString(), args, null, null, sortOrder);

		if(c != null) {
			c.setNotificationUri(getContext().getContentResolver(), uri);
		}

		return c;
	}

	private String[] setSelectionArgs(int match, Uri uri, String selection, String[] selectionArgs, StringBuilder sb) {

		sb.append(Nulls.replaceNull(selection, ""));
		String[] args = selectionArgs;
		if(isId(match)) {
			if(sb.length() > 0) {
				sb.append(" AND ");
			}
			String id = uri.getLastPathSegment();
			sb.append(_ID).append(" = ?");
			args = Arrays.append(String.class, selectionArgs, id);
		}
		return args;
	}

	protected abstract String getProviderName();

	protected abstract String getDatabaseName();

	protected abstract int getDatabaseVersion();

	protected abstract ITable[] getTables();

	private String getAuthority() {
		if(sAuthority == null) {
			sAuthority = PROVIDER_AUTHORITY_PREFIX + getProviderName();
		}
		return sAuthority;
	}

	private String getDirTypePrefix() {
		if(sDirTypePrefix == null) {
			sDirTypePrefix = ZEN_DIR_TYPE + getProviderName() + ".";
		}
		return sDirTypePrefix;
	}

	private String getItemTypePrefix() {
		if(sItemTypePrefix == null) {
			sItemTypePrefix = ZEN_ITEM_TYPE + getProviderName() + ".";
		}
		return sItemTypePrefix;
	}

	private static class DatabaseOpenHelper extends SQLiteOpenHelper {

		private int			version;
		private ITable[]	tables;

		public DatabaseOpenHelper(Context context, String name, int version, ITable[] tables) {
			super(context, name, null, version);
			this.version = version;
			this.tables = tables;
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			for(ITable table : tables) {
				table.upgrade(db, 0, version);
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			for(ITable table : tables) {
				table.upgrade(db, oldVersion, newVersion);
			}
		}

	}

	public interface ITable extends BaseColumns {

		String getName();

		void upgrade(SQLiteDatabase db, int oldVersion, int newVersion);
	}

}
