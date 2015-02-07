Détails du modèle dans le [wiki](https://github.com/Zenika/Formation--Modele/wiki)

# Organisation

- Slides
- CahierExercices
- Exercices
- Installation
- SessionsNotes

*Note : Chaque répertoire contient un README précisant son usage*

# Installation

Pour générer et afficher la formation, il est nécessaire d'avoir `node` et `npm` d'installé (voir [node.js](http://nodejs.org/)).

Exécuter la commande suivante :
`npm install`

## Utilisation avec Docker

Pour ceux qui veulent se passer de l'installation de `node` et `npm`, et qui ont `docker`, il suffit de lancer la commande suivante : 
`docker run -it -v $PWD:/data --net=host --rm dockerfile/nodejs-bower-grunt npm install`
