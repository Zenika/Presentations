# Spark

<img 
  src="ressources/spark-logo.79fdd756.png" 
  alt="Spark" 
  style="margin-left: 70%; margin-top: 10%"
  />

<!-- .slide: class="page-title" -->



## RDD

* Resilient
* Distributed
* Dataset



## RDD

* Liste de partitions
* Fonction pour traiter chaque partition
* Noeuds les plus proches d'une partition
* RDDs parents



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



## Caching

* `cache`, `persist`
* Memoire et/ou Disque local
* Sérialisation ou pas
* Off-heap <img src="ressources/tachyon-logo.d02e0f61.png" alt="Tachyon" width="20%" style="display: inline; vertical-align: middle;"
/> 




## Partititioning

* A la source
* Par clé + hashage




## Demo

<!-- .slide: class="page-tp1" -->



## Ecosystème Spark

![Ecosysteme Spark](ressources/spark-ecosystem.6d566645.png)



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
* Compatible HiveQL?



## Spark Streaming

* Micro-batch
* *DStream*
  * Discrete Stream
  * Suite de RDD, 1 toutes les N secondes
  * Même API que Spark Core: transformations, actions

![Spark DStream ](ressources/spark-stream-dstream.4c506ac4.png)



## Stream Source

* Fichiers: local, HDFS
* "Broker": Kafka, ZeroMQ, Akka
* Autre: Twitter, Socket, Flume

![Spark DStream ](ressources/spark-stream-dstream.4c506ac4.png)



## Stateful Stream

* Window:
   * Détection de fraude, de tendance...
![Spark DStream ](ressources/spark-stream-window.5631db68.png)
* UpdateStateByKey:
   * Maintenir un état
   * 10 articles/utilisateur
* Stateful => checkpoint



## Demo

<!-- .slide: class="page-tp2" -->



## Cluster

![Cluster Spark](ressources/spark-cluster.8786a55c.png)



## Cluster manager

<img 
  src="ressources/spark-logo.79fdd756.png" 
  alt="Spark" width="20%" 
  style="display: inline; vertical-align: middle;"
  />
Standalone 


<img 
  src="ressources/hadoop-logo.00dad263.jpg" 
  alt="Hadoop" width="20%" 
  style="display: inline; vertical-align: middle;"
  />
YARN

<img 
  src="ressources/mesos-logo.4025444c.png" 
  alt="Mesos" width="20%" 
  style="display: inline; vertical-align: middle;"
  />
Mesos




## Demo

<!-- .slide: class="page-tp3" -->



## Hadoop

* Mêmes cas d'utilisation
* Intégration dans l'écosystème



## Plus ...

** Les slides qui suivent ne sont pas objectifs **



## Plus simple

<img 
  src="ressources/spark-hadoop-code.aa002ba3.png" 
  alt="Spark simple"
  />
<div style="position: relative; left:50%; top:-50%">

<li>API style <em>collection</em> habituelle</li>
<li>Spark Shell</li>
<li>Spark local</li>
</div>




## Plus rapide

[Spark officially sets a new record in large-scale sorting](http://databricks.com/blog/2014/11/05/spark-officially-sets-a-new-record-in-large-scale-sorting.html)

![Spark rapide](ressources/spark-hadoop-perf.70e63618.png)



## Plus vivant

![Spark contributors](ressources/spark-hadoop-contributors.97a8b43c.png)



## Plus léger

![Spark contributors](ressources/spark-hadoop-code2.74556382.png)



## Moins Java




## Moins répandu

Amazon, Autodesk, Baidu, eBay, Groupon, Kelkoo, NASA, Shazam, Yahoo... 



## Moins Google-friendly

<img src="ressources/spark-car.dc35b93f.jpg" style="display: inline"  width="30%"/> <img src="ressources/spark-io.0e092994.png" style="display: inline" width="30%"/> <img src="ressources/spark-web.6411680a.png" style="display: inline"  width="30%" />



<!-- .slide: class="page-questions" -->
