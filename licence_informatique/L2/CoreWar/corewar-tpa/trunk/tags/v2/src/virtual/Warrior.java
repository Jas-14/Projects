package virtual;

import instruction.Instruction;

import java.util.List;

/**
 * Classe Warrior. La classe possède le programme à exécuter, mais peut être également considéré comme un processus de la machine.
 */
public class Warrior {

    /**
     * Nombre de victoire de ce warrior
     */
    private int win;

    /**
     * Nombre de défaite de ce warrior
     */
    private int deaths;

    /**
     * Nombre de matchs de ce warrior
     */
    private int matches;

    /**
     * Programme de ce warrior
     */
    public List<Instruction> prgrm;

    /**
     * Pointeur
     */
    private int pointer;

    /**
     * Nom
     */
    private String name;

    /**
     * Constructeur
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
     * Méthode pour augmenter le nombre de victoires
     */
    public void winMatch(){

        this.win++;

    }

    /**
     * Méthode pour augmenter le nombre de défaites
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

    @Override
    public String toString() {
        return "Warrior{" +
                "name='" + name + '\'' +
                '}';
    }
}
