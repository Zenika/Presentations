package com.zenika.spark.demo;

import org.apache.spark.sql.api.java.Row;
import org.apache.spark.sql.api.java.StructType;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Arrays.*;
import static org.apache.spark.sql.api.java.DataType.*;

/**
 *
 *
 */
public class AccessLog {
    private final String ip;
    private final Date date;
    private final String verb;
    private final String path;
    private final Integer result;
    private final Long duration;

    public AccessLog(String ip, Date date, String verb, String path, Integer result, Long duration) {
        this.ip = ip;
        this.verb = verb;
        this.date = date;
        this.path = path;
        this.result = result;
        this.duration = duration;
    }

    public String getIp() {
        return ip;
    }

    public Date getDate() {
        return date;
    }
    public java.sql.Date getSqlDate() {
        return date == null ? null : new java.sql.Date(date.getTime());
    }

    public String getVerb() {
        return verb;
    }

    public String getPath() {
        return path;
    }

    public Integer getResult() {
        return result;
    }

    public Long getDuration() {
        return duration;
    }

    public static Pattern getPattern() {
        return PATTERN;
    }

    private static final Pattern PATTERN = Pattern.compile("([0-9]+\\.[0-9]+\\.[0-9]+\\.[0-9]+) - - \\[([^\\]]+)] \"([A-Z]+) ([^ ]+) ([A-Z0-9/\\.]+)\" ([0-9]+) ([0-9]+).*");
    public static AccessLog parse(String line) {
        Matcher matcher = PATTERN.matcher(line);
        if (matcher.matches()) {
            try {
                //                                            25/Jan/2015:11:16:44 +0100
                DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
                return new AccessLog(
                        matcher.group(1),
                        dateFormat.parse(matcher.group(2)),
                        matcher.group(3),
                        matcher.group(4),
                        Integer.parseInt(matcher.group(6)),
                        Long.parseLong(matcher.group(7))
                );
            } catch (ParseException|NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public static void main(String ... args) {
        AccessLog parse = AccessLog.parse("188.138.17.205 - - [25/Jan/2015:11:16:44 +0100] \"GET / HTTP/1.1\" 403 177 \"-\" \"-\"");
    }

    public static StructType structType() {
        return createStructType(asList(
                createStructField("ip", StringType, false),
                createStructField("date", DateType, false),
                createStructField("verb", StringType, false),
                createStructField("path", StringType, true),
                createStructField("result", IntegerType, true),
                createStructField("duration", LongType, true)
        ));
    }
    public Row toRow() {
        return Row.create(ip, getSqlDate(), verb, path, result, duration);
    }
}
