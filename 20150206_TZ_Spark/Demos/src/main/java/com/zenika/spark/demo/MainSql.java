package com.zenika.spark.demo;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.api.java.JavaSQLContext;
import org.apache.spark.sql.api.java.JavaSchemaRDD;
import org.apache.spark.sql.api.java.Row;
import org.apache.spark.sql.hive.api.java.JavaHiveContext;

/**
 * Created by gquintana on 05/02/15.
 */
public class MainSql {
    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf()
                .setMaster("local[2]")
                .setAppName("Zenika")
                .set("spark.eventLog.enabled","true");
        JavaSparkContext sc = new JavaSparkContext(sparkConf);
        //JavaSQLContext sqlc = new JavaSQLContext(sc);
        JavaHiveContext sqlc = new JavaHiveContext(sc);
        // Chargement du fichier
        JavaRDD<String> logs = sc.textFile("file:///home/gquintana/Work/Veille/Presentations/20150206_TZ_Spark/Demos/access.log.1");
        // Parsing
        JavaRDD<AccessLog> accessLogs = logs.map(line -> AccessLog.parse(line));
        // Register Access_Log table
        JavaRDD<Row> rows = accessLogs.filter(accessLog -> accessLog!=null).map(accessLog -> accessLog.toRow());
        JavaSchemaRDD rowsSql = sqlc.applySchema(rows, AccessLog.structType());
        rowsSql.registerTempTable("access_log");
        // Requete
        JavaSchemaRDD ipCountSql = sqlc.sql("select ip, count(*) from access_log where result=404 group by ip");
        ipCountSql.foreach(row -> System.out.println(">>>>>>: "+row.getString(0)+": "+row.getLong(1)));
        ipCountSql.saveAsParquetFile("/home/gquintana/Work/Veille/Presentations/20150206_TZ_Spark/Demos/ip_count.parquet");
        //
        JavaSchemaRDD ipLength = sqlc.sql("select ip, length(ip) as ip_lengthp   from access_log group by ip");
        ipLength.foreach(row -> System.out.println(">>>>>>: " + row.getString(0) + ": " + row.getInt(1)));
    }

}
