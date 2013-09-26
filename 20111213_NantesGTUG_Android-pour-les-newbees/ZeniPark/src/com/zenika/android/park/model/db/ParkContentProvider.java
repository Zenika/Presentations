package com.zenika.android.park.model.db;

import com.zenika.android.park.model.db.tables.Parkings;
import com.zenika.android.tools.db.AbstractContentProvider;

public class ParkContentProvider extends AbstractContentProvider {

	private static final String	PROVIDER_NAME		= "zeniparks";
	private static final String	DATABASE_NAME		= "zeniparks.db";
	private static final int	DATABASE_VERSION	= 1;

	public static final String	BASE_URI			= BASE_URI_PREFIX + PROVIDER_NAME + "/";
	
	private static final ITable[] TABLES = {new Parkings()};

	@Override
	protected String getProviderName() {
		return PROVIDER_NAME;
	}

	@Override
	protected String getDatabaseName() {
		return DATABASE_NAME;
	}

	@Override
	protected int getDatabaseVersion() {
		return DATABASE_VERSION;
	}

	@Override
	protected ITable[] getTables() {
		return TABLES;
	}

}
