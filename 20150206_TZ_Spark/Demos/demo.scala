// Chargement du fichier
val logs=sc.textFile("file:///home/gquintana/Work/Veille/Presentations/20150206_TZ_Spark/Demos/access.log.1")
logs.first()
// Parsing
val words=logs.map(line => line.split("[ \t]+"))
words.first()
// Filtrage erreurs 400
val error400=words.filter(l => l(8).toInt==400)
error400.count()
// Comptage par IP
val error400ByIp= error400.keyBy(l => l(0))
error400ByIp.countByKey()
// Cache
error400.cache()
// Lignee
logs.setName("logs")
words.setName("words")
error400.setName("error400")
error400ByIp.setName("error400ByIp")
error400ByIp.toDebugString
