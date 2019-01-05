Mode d'emploi de l'application:

compilation de l'application : A partir de la racine
mkdir build
cd src
javac -d ../build main/DemoAppli.java

lancer l'application :
java -cp ../build main.DemoAppli <Jeu> <TypeJ1> <TypeJ2>

<Jeu>: Le jeu auquel vous voulez jouer. Attention, si vous
voulez jouer à Nim, la syntaxe est la suivante:

  Nim <n> <k> <TypeJ1> <TypeJ2>

Avec n le nombre d'allumettes au total et k le nombre d'allumettes
que l'on peut retirer par tour au maximum.

<TypeJ>: Le type de joueur qui va jouer.
Si vous voulez jouer, mettez "Humain", un espace et votre nom.
Si vous voulez faire jouer un joueur aléatoire, mettez "Random"
Si vous voulez faire jouer une IA, mettez "IA"

Le J1 sera forcément le premier à jouer.

Les arguments sont sensibles à la case, en cas d'erreur, une explication
s'affichera dans la console.

Exemples:

Si vous voulez lancer le jeu de Nim avec 25 allumettes,3 retirées au maximum
par tour, un joueur aléatoire et un humain:

java -cp ../build main.DemoAppli Nim 25 3 Random Humain monNom

Si vous voulez lancer le TicTacToe avec deux IA:

java -cp ../build main.DemoAppli TicTacToe IA IA


La documentation java est disponible pour cette application. Veuillez
l'ouvrir sous Google Chrome de préférence ou rajouter la ligne
"<meta charset="utf-8">" au document index.html généré pour que les
caractères accentués soient lisibles.
