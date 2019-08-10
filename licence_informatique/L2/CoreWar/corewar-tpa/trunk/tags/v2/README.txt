CoreWar Version 2.0

Auteurs: Jasmine Chaid Akacem, Hugo Baudin, Siega Bissombolo, Yacine Seye

Description: Ce logiciel permet de faire s'affronter deux programmes
RedCode dans une machine virtuelle. Interface console uniquement.

Mode d'emploi:

En se trouvant dans le dossier v2

Exécution via le jar:

ant packaging
java -jar corewarv2.jar Programme1.txt Programme2.txt

Attention, les fichiers doivent être situés dans le répertoire d'exécution
de cette ligne de commande ou plus bas dans l'arborescence. Ici, ils doivent se situer dans v2.

Exécution via le code source:

ant run -Darg0="Programme1.txt" -Darg1="Programme2.txt"

Javadoc:

ant javadoc
