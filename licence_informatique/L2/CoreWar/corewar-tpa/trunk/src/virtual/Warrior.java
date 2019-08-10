package virtual;

import virtual.instruction.Instruction;

import java.util.List;

/**
 * Classe Warrior, qui permet de créer un objet qui
 * symbolise le Warrior. Il est beaucoup plus facile
 * ainsi de maintenir des statistiques.
 * Cette classe a une implémentation fixée de Comparable, mais
 * on pourrait imaginer la changer, pour faciliter certaines
 * manipulations au sein de l'algorithme génétique
 */
public class Warrior implements Comparable<Warrior> {

    /**
     * Attribut win, nombre de victoire de ce warrior
     */
    private int win;

    /**
     * Attribut deaths, nombre de défaite de ce warrior
     */
    private int deaths;

    /**
     * Attribut matches, nombre de matchs de ce warrior
     */
    private int matches;

    /**
     * Attribut prgrm, programme de ce warrior
     */
    private List<Instruction> prgrm;

    /**
     * Attribut pointer, pointeur sur instruction
     */
    private int pointer;

    /**
     * Attribut name
     */
    private String name;

    /**
     * Constructeur Standard
     * @param name Nom
     * @param prgrm Programme
     */
    public Warrior(String name,List<Instruction> prgrm){

        this.name=name;
        this.prgrm=prgrm;
        this.markInstruction();

        this.win=0;
        this.deaths=0;
        this.matches=0;

    }

    /**
     * Méthode pour augmenter le nombre de matchs
     */
    public void increaseMatches(){

        this.matches++;
    }

    /**
     * Méthode pour augmenter le nombre de victoires.
     * S'accorde à notre implémentation de l'algorithme
     * génétique
     */
    public void winMatch(){

        this.win++;

    }

    /**
     * Méthode pour augmenter le nombre de défaites.
     * S'accorde à notre implémentation de l'algorithme
     * génétique
     */
    public void looseMatch(){

        this.deaths++;

    }

    /**
     * Getteur du programme
     * @return Le programme du Warrior
     */
    public List<Instruction> getPrgrm(){

        return this.prgrm;
    }

    /**
     * Méthode pour désigner ce warrior comme étant le propriétaire
     * du programme. On ne peut l'effectuer que lorsque le Warrior est
     * créé.
     */
    private void markInstruction(){

        for (Instruction i :this.prgrm) {

            i.setLastAccessed(this);

        }


    }

    /**
     * Getteur du pointeur
     * @return Le pointeur
     */
    public int getPointer() {
        return pointer;
    }

    /**
     * Setteur du pointeur
     * @param pointer La nouvelle valeur du pointeur
     */
    public void setPointer(int pointer) {
        this.pointer = pointer;
    }

    public int getWin() {
        return win;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getMatches() {
        return matches;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Warrior "+name;
    }

    @Override
    public int compareTo(Warrior w){

        return w.getWin()-this.getWin();

    }

    /**
     * Méthode qui permet de remettre à 0 les victoires d'un
     * Warrior.
     */
    public void resetWin(){

        this.win=0;
    }
}
