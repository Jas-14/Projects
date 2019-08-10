CoreWar Version 1.0

Auteurs: Jasmine Chaid Akacem, Hugo Baudin, Siega Bissombolo, Yacine Seye

Description: Ce logiciel permet d'exécuter un fichier écrit en RedCode
dans une machine virtuelle et d'en observer les états. Interface console uniquement.

Mode d'emploi:

En se trouvant dans le dossier v1

Exécution via le jar:

ant packaging
java -jar corewarv1.jar Programme.txt

Attention, Programme.txt doit être situé dans le répertoire d'exécution
de cette ligne de commande ou plus bas dans l'arborescence. Ici, il doit se situer dans tags.

Exécution via le code source:

ant run -Dargs="Programme.txt"

Javadoc:

ant javadoc
