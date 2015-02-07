    spark-submit --master spark://opscenter:7077  \
        --class com.zenika.test.cassandra.spark.Main   \
        --executor-memory 128M                   \
        --total-executor-cores 2               \
        ~/
