package geneticPgrm.evolution;

import geneticPgrm.RandomWarrior;
import virtual.Warrior;
import virtual.instruction.Instruction;

import java.util.List;
import java.util.Random;

/**
 * Classe ADLineMutation. C'est une classe effectuant le rajout
 * d'une ligne supplémentaire générée aléatoirement, selon une
 * probabilité.
 */
public class AddLineMutation implements Evolution {

    /**
     * Attribut probabilité, pour définir la probabilité de
     * mutation.
     */
    private double probability;

    /**
     * Constructeur standard
     * @param proba La probabilité de rajouter une ligne
     */
    public AddLineMutation(double proba){

        this.probability=proba;

    }

    /**
     * Constructeur annexe
     */
    public AddLineMutation(){

        this(0.2);

    }

    /**
     * Méthode permettant de faire muter un warrior
     * @param w Le Warrior à faire muter
     * @param a L'entier qui limite le nombre maximal présent sur un champ d'adresse.
     */
    private void mutOneWarrior(Warrior w,int a){

        Instruction i= RandomWarrior.randomLine(a);

        //On récupère le programme
        List<Instruction> pgrm=w.getPrgrm();

        Random rand=new Random();

        int n=rand.nextInt(pgrm.size());

        /*La méthode add pour une arraylist permet de rajouter
        un élément et de décaler toutes les autres, de manière
        efficace
         */
        pgrm.add(n,i);

    }

    @Override
    public void mutWarriors(List<Warrior> population,int a){

        //Parcours des warriors
        for (Warrior w:population) {

            double p=Math.random();

            /*On tire un nombre entre 0 et 1. Si ce nombre est
            plus petit ou égal à la probabilité fixée, alors
            on fait muter le warrior.
             */
            if(p<=this.probability){

                this.mutOneWarrior(w,a);
            }

        }



    }
}
