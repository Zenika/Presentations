package com.zenika.spark.demo;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

/**
 * Created by gquintana on 05/02/15.
 */
public class Main {
    public static void main(String[] args) {
        JavaSparkContext sc = new JavaSparkContext("local[2]","Zenika");
        // Chargement du fichier
        JavaRDD<String> logs = sc.textFile("file:///home/gquintana/Work/Veille/Presentations/20150206_TZ_Spark/Demos/access.log.1");
        System.out.println("First Logs:"+logs.first());
        // Parsing
        JavaRDD<String[]> words = logs.map(line -> line.split("[ \t]+"));
        System.out.println("First split Logs:"+words.first());
        words.first();
        // Filtrage erreurs 400
        JavaRDD<String[]> error400 = words.filter(l -> Integer.parseInt(l[8]) == 400);
        error400.count();
        // Comptage par IP
        JavaPairRDD<String, String[]> error400ByIp = error400.keyBy(l -> l[0]);
        error400ByIp.countByKey();
        // Cache
        error400.cache();
    }
}
