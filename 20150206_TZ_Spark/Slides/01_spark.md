# Spark

<img 
  src="ressources/spark-logo.png" 
  alt="Spark" 
  style="margin-left: 70%; margin-top: 10%"
  />

<!-- .slide: class="page-title" -->



## Rappels Hadoop



### Ecosystème Hadoop

![HDFS](ressources/hadoop-ecosystem.jpg)



### Hadoop DFS

![Architecture](ressources/hdfs-architecture.gif)



### Hadoop MapReduce

![Architecture](ressources/hadoop-mapreduce.png)



## Ecosystème Spark

![Ecosysteme Spark](ressources/spark-ecosystem.png)



## RDD

* Resilient
* Distributed
* Dataset



## RDD

![Spark RDD](ressources/spark-rdd.png)



## Exemple RDD

```
val error400ByIp=sc.textFile("hdfs:///access.log") // RDD[String]
  .map(l => AccessLog.parse(l))                    // RDD[AccessLog]
  .filter(l => l.httpStatus==400)                  // RDD[AccessLog]
  .keyBy(l =>  l.sourceIp)                         // RDD[String, AccessLog]
  .countByKey()                                    // Map[String, long]
```




## RDD Source

* Fichiers: Local, HDFS, S3
* Base de données: Cassandra, MongoDB, ElasticSearch, JDBC



## Transformations

1 ou plusieurs RDD &rarr; 1 RDD.

Evaluation lazy

* `map`, `flatMap`, `filter`, `distinct`
* `groupBy`, `reduce`, `fold`, 
* `substract`, `intersection`, `union`, `cartesian`



## Actions

1 RDD &rarr; Donnée brute

Declenche la soumission d'un Job.

* `count`, `countByValue`, `min`, `max`
* `first`, `take`, `collect`, `foreach`

* `saveAsTextFile`, `saveAsObjectFile`



## PairRDD

Couples (clé,valeur)

* `mapValue`
* `groupByKey`, `reduceByKey`, `sortByKey`, 
* `join`, `leftOuterJoin`, `rightOuterJoin`, `cogroup`
* `partitionBy`, `coalesce`

* `saveAsSequenceFile` 



## DAG

![DAG](ressources/spark-dag.png)



## Caching

* `cache`, `persist`
* Memoire et/ou Disque local
* Sérialisation ou pas
* <img src="ressources/tachyon-logo.png" alt="Tachyon" width="20%" style="display: inline; vertical-align: middle;" /> 
    - Off-heap
    - Partagé/réutilisé entre jobs



## RDD

* Liste de partitions
* Fonction pour traiter chaque partition
* Noeuds les plus proches d'une partition
* RDDs parents



## Partitions, Tasks et Stages

![Spark](ressources/spark-tasks.png)



## Partitioning

* A la source
* Par clé + hashage: `.partitionBy`, `coalesce`
* Shuffling: `join`, `groupByKey`, `reduceByKey`



## Demo

<!-- .slide: class="page-tp1" -->



## Cluster

![Cluster Spark](ressources/spark-cluster.png)



## Cluster manager

<img 
  src="ressources/spark-logo.png" 
  alt="Spark" width="20%" 
  style="display: inline; vertical-align: middle;"
  />
Standalone 


<img 
  src="ressources/hadoop-logo.jpg" 
  alt="Hadoop" width="20%" 
  style="display: inline; vertical-align: middle;"
  />
YARN

<img 
  src="ressources/mesos-logo.png" 
  alt="Mesos" width="20%" 
  style="display: inline; vertical-align: middle;"
  />
Mesos



## Développement

* *Multi-langages:* Scala, Java, Python, R, SQL
* *Shell:* `spark-shell`, `pyspark`, `spark-sql`
* *Test:*
    - Mode local
    - De/vers les collections standards



## Connecteurs

* Standards: 
    - File, HDFS, S3, JDBC
* Extensions: 
    - Cassandra, ElasticSearch, MongoDB...
    - [spark-packages.org](http://spark-packages.org/)




## Formats

* Standards: 
    - TextFile, ObjectFile, SequenceFile (K/V), Parquet (Schema), JSON (Schema)
    - Hadoop InputFormat/OutputFormat
* Extensions: 
    - CSV, ProtoBuf, Avro, LZO, ...
    - [spark-packages.org](http://spark-packages.org/)




## Déploiement

* Packaging fat Jar (Java, Scala) ou Zip (Python)
* `spark-submit`
    - mode: client ou cluster
    - sizing executors
* Spark Job Server



## Demo

<!-- .slide: class="page-tp3" -->



## Spark SQL

* Ex-Shark




## SchemaRDD

* Table: Lignes &times; Colonnes
* Description des colonne: nom, type
   * Manuel
   * Détection (JSON, Parquet, DB)



## SQL

* `select ... from ... join ... where ... group by ... order by`
* Rule based optimizer: Catalyst 
* Push down



## HiveQL

* Syntaxe et fonctions HiveQL
* Métastore Hive (JDBC)




## Spark Streaming

* Micro-batch
* *DStream*
  * Discrete Stream
  * Suite de RDD, 1 toutes les N secondes
  * Même API que Spark Core: transformations, actions

![Spark DStream ](ressources/spark-stream-dstream.png)



## Stream Source

* Fichiers: local, HDFS
* "Broker": Kafka, ZeroMQ, Akka
* Autre: Twitter, Socket, Flume

![Spark DStream ](ressources/spark-stream-dstream.png)



## Stateful Stream

* Window:
   * Détection de fraude, de tendance...
![Spark DStream ](ressources/spark-stream-window.png)
* UpdateStateByKey:
   * Maintenir un état
   * 10 articles/utilisateur
* Stateful => checkpoint



## Demo

<!-- .slide: class="page-tp2" -->



## Hadoop

* Mêmes cas d'utilisation
* Intégration dans l'écosystème



## Plus ...

** Les slides qui suivent ne sont pas objectifs **



## Plus simple

<img 
  src="ressources/spark-hadoop-code.png" 
  alt="Spark simple"
  />
<div style="position: relative; left:50%; top:-50%">

<li>API style <em>collection</em> habituelle</li>
<li>Spark Shell</li>
<li>Spark local</li>
</div>




## Plus rapide

[Spark officially sets a new record in large-scale sorting](http://databricks.com/blog/2014/11/05/spark-officially-sets-a-new-record-in-large-scale-sorting.html)

![Spark rapide](ressources/spark-hadoop-perf.png)



## Plus vivant

![Spark contributors](ressources/spark-hadoop-contributors.png)



## Plus léger

![Spark contributors](ressources/spark-hadoop-code2.png)



## Moins Java




## Moins répandu

Amazon, Autodesk, Baidu, eBay, Groupon, Kelkoo, NASA, Shazam, Yahoo... 



## Moins Google-friendly

<img src="ressources/spark-car.jpg" style="display: inline"  width="30%"/> <img src="ressources/spark-io.png" style="display: inline" width="30%"/> <img src="ressources/spark-web.png" style="display: inline"  width="30%" />



<!-- .slide: class="page-questions" -->
