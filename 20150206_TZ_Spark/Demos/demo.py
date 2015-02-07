# Chargement du fichier
logs=sc.textFile("file:///home/gquintana/Work/Veille/Presentations/20150206_TZ_Spark/Demos/access.log.1")
logs.first()

# Parsing
import re
words=logs.map(lambda line : re.split("[ \t]+", line))
words.first()

# Filtrage erreurs 400
error400=words.filter(lambda l: int(l[8])==400)
error400.count()
