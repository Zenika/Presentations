# Rédiger ou éditer des slides

## Principes

Le framework utilisé est [Reveal.js](https://github.com/hakimel/reveal.js).

Le framework Reveal, customisé pour les formations Zenika, est importé et installé dans la formation via `npm`.

Seul le contenu des slides se trouvent donc dans ce répertoire.

Il y a 2 types de fichiers. D'une part, `slides.json` permet d'indiquer les chapitre à inclure. D'autre part, les chapitres sont écrits dans des fichiers markdown.

## Inclusion du Markdown

Pour modifier la liste de fichiers chargés, éditer `slides.json`. Ce fichier contient une liste des chemins des fichiers markdown à inclure. Le chemin doit être relatif au fichier `slides.json`, lui même devant être situé à la racine du répertoire `Slides`.

Exemple: 

```json
[
  "premier_fichier.md",
  "chapitre2/second_fichier.md"
]
```

## Contenu

### Règles de rédaction

- Le contenu est éclaté dans les fichiers markdown, un par chapitre de la formation.
- La formation commence par un chapitre zéro, qui contient au minimum une page de titre avec le titre de la formation, une page qui présente le plan de la formation, et une page d'invitation aux questions. On peut éventellement ajouter des rappels concernant les horaires et autres informations pratiques.
- Tous les chapitres débutent par une page de titre avec le titre du chapitre et une page qui reprend le plan de la formation. La partie en cours en mise en évidence.
- Tous les chapitres se terminent par une page invitant aux questions, puis éventuellement par une page qui annonce un TP.

### Markdown

- Le [GitHub Flavored Markdown](https://help.github.com/articles/github-flavored-markdown) est supporté
- Chaque slides doit être séparées par 3 lignes blanches.

#### Pages spéciales

Certaines pages récurrentes des formations sont réalisée grâce à des classes css.

##### Pages de titre

Les pages de titre sont composées d'un titre de premier niveau d'un fond Zenika.

```markdown
# Titre du chapitre

<!-- .slide: class="page-title" -->
```

##### Pages de questions

Les pages de questions n'ont pas de titre.

```markdown
<!-- .slide: class="page-questions" -->
```

##### Pages de TP

Les pages de TP n'ont pas de titre, il suffit d'utiliser la classe correspondante au numéro de TP (de 1 à 15).

```markdown
<!-- .slide: class="page-tp1" -->
```

#### Pages de plan

- L'emphase sur la partie en cours dans les slides de plan est une emphase double : `**Partie en cours**`.
- Les liens entre slides étant possibles, il est intéressant d'un mettre sur les slides de plan. Un lien inter-slides est au format `#/<chapitre>/<slide>` sachant que les chapitres et les slides sont numérotés à partir de zéro, et que le numéro de slide est facultatif (zéro par défaut).

```markdown
## Plan

- [Partie 1](#/1)
- **[Partie 2](#/2)**
- [Partie 3](#/3)
```

#### Pages standards

- Les slides standards commencent par un titre de second niveau.
- Les mots importants sont à emphaser avec emphase simple : `*mot important*`.

#### Code

- Pour le code inline, utiliser la syntaxe Markdown classique, et pour les blocs de code, utiliser les blocs GFM avec spécification du langage.

<!-- Utilisation de <br/> ici car les balises pre et code perturbent la coloration syntaxique Markdown Extended de Sublime Text si elles s'ouvrent et se ferment sur deux lignes différentes. -->

<pre><code>```javascript<br/>function(arg) { return 'du javascript en couleur !'; }<br/>```</code></pre>

- Il n'y a pas de retour à la ligne automatique dans les blocs de code, il faut donc vérifier que les lignes rentrent bien dans la largeur de la slide.
- Si une ligne de code commence par une suite continue d'espaces trop longue, la ligne est mise à la ligne. Il est donc nécessaire d'indenter avec 2 espaces seulement.

#### Images

- Pour les images, écrire du HTML classique. La taille peut être modifié via l'attribut `width`, et la position en modifiant les marges. La classe `.with-border` active une fine bordure noire autour de l'image.

```html
<img 
  src="ressources/image.png" 
  alt="Une image" 
  width="90%" 
  style="margin-top: 10%"
  class="with-border"/>
```

- Pour ajouter une légende à une image, on peut utiliser les éléments HTML5 `figure` et `figcaption`.

```html
<figure>
    <img 
      src="ressources/image.png" 
      alt="Une image"/>
    <figcaption>Une superbe représentation de quelque chose</figcaption>
</figure>
```

- Le chemin de l'image doit être relatif à la racine du répertoire Slides.

#### Fragments

On peut indiquer que des éléments de slides ne doivent être révélés qu'au fur et à mesure que le formateur appuie sur le bouton. Il faut utiliser la classe `fragment`.

```markdown
- item qui n'apparait pas tout de suite <!-- .element: class="fragment" -->
```

#### Fonctionnalités avancées

Si besoin, on peut ajouter des attibuts HTML à la slide en cours où à un élément grâce aux syntaxes `<!-- .slide: ... -->` et `<!-- .element: ... -->`. Cela peut être utile pour donner un style particulier à un élément, par exemple pour le positionner.

#### Divers
D'autres exemples peuvent être trouvés directement dans les slides modèle.

# Consulter les slides

## Lancement

- Aller à la racine de la formation
- Lancer le serveur avec `grunt` ou `grunt displaySlides` dans une console
- Les slides se lancent directement sur votre navigateur favoris (fonctionne bien sous Chrome, devrait fonctionner également sous FF)
- Si le navigateur ne s'ouvre pas, aller à `http://localhost:8000`
- Vous arriverez sur le chapitre zéro, slide une, c'est-à-dire la page de garde de la formation
- Si le port 8000 pose un problème sur votre machine, vous pouvez choisir le port en rajoutant l'option `--port=9999`

## Lancement avec Docker
- Aller à la racine de la formation
- Lancer le serveur avec docker `docker run -it -v $PWD:/data --net=host --rm dockerfile/nodejs-bower-grunt grunt`
- Aller à `http://localhost:8000`

En cas de problème de démarrage du serveur, vous pouvez rétablir une situation normale en copiant le contenu du fichier .md dans le fichier html, en ajoutant une balise `<script>`, comme suit :

Remplacer 
```html
<!-- Slides will be inserted here -->
```

Par 
```html
<section 
  data-markdown="00_agenda.md" 
  data-vertical="^\r?\n\r?\n\r?\n"
  data-notes="^Notes :">
  <script type="text/template"> 
    # Titre de la formation

    ...
  </script>
</section>
```
*Note : * Les modifications dans les fichiers des chapitres (`*.md`), dans `slides.json`, ainsi que dans les ressources sont prises en compte à chaud si le serveur a été lancé avec `grunt`.
## Navigation

- Espace permet d'aller au slide suivant
- Flèches haut et bas pour naviguer au sein du chapitre
- Flèches gauche et droite pour naviguer entre les chapitres
- Les slides Plan sont faites de liens pour sauter directement aux différents chapitres
- La petite flèche en bas à gauche revient à la slide Plan maitresse (chapitre zéro, slide deux)
- Les fonctions précédent et suivant du navigateur fonctionne normalement

## Raccourcis clavier

- `espace` passe au slide suivant
- `haut/bas/gauche/droite` navigue dans les slides
- `o` donne accès à une vue avec du recul sur les slides
- `s` active le mode présentateur : une nouvelle fenêtre s'ouvre avec slide en cours, slide suivante, temps écoulé, notes
- `b` "éteind" la présentation, afin que les participants se concentre sur le présentateur
- `alt` + `clic` permet de zoomer / dézoomer sur une partie de la slide

## Exporter en PDF

### Manuellement

- Ouvrir les slides dans Chrome
- Ajouter `?print-pdf` à la fin de l'URL (`http://localhost:8000?print-pdf`)
- Contrôle-P pour amener les options d'impression
- Sélectionner :
  - Destination : Enregistrer au format PDF
  - Mise en page : Paysage
  - Marges : Aucun
- Enregistrer le PDF

### Automatiquement

`grunt generateSlidesPDF`

- Il est inutile de lancer le serveur au préalable, c'est fait automatiquement
- Il est possible d'utiliser un port différent grâce à `--port=9999`

:boom: Attention le rendu des pdf générés automatiquement est mauvais pour le moment, il est donc plus que préférable d'utiliser la méthode manuelle
