package geneticPgrm.evolution;

import geneticPgrm.RandomWarrior;
import virtual.instruction.Instruction;
import virtual.Warrior;

import java.util.List;
import java.util.Random;

/**
 * Classe LineMutation, qui permet de remplacer une ligne par
 * une nouvelle ligne dans un programme.
 */
public class LineMutation implements Evolution {

    /**
     * Attribut probabilité, qui définit la probabilité de mutation
     */
    private double probability;

    /**
     * Constructeur standard
     * @param probability La probabilité de mutation
     */
    public LineMutation(double probability){

        this.probability=probability;
    }

    /**
     * Constructeur annexe
     */
    public LineMutation(){

        this(0.38);
    }

    /**
     * Méthode mutOneWarrior, qui permet de muter le Warrior
     * sélectionné.
     * @param w Le Warrior sélectionné
     * @param bound Le nombre maximum dans un champ d'adresse
     */
    private void mutOneWarrior(Warrior w,int bound){

        List<Instruction> pgrm=w.getPrgrm();

        Random rand=new Random();

        Instruction i=RandomWarrior.randomLine(bound);

        int n=rand.nextInt(pgrm.size());

        /*
        Pour remplacer une ligne, on doit d'abord effacer la
        ligne présente à l'indice tiré au hasard, puis ajouter
        la nouvelle instruction
         */
        pgrm.remove(n);

        pgrm.add(n,i);


    }

    @Override
    public void mutWarriors(List<Warrior> population,int bound){

        //Parcours des Warriors
        for (Warrior w:population) {

            double p=Math.random();

            /*On tire un nombre entre 0 et 1. Si ce nombre est
            plus petit ou égal à la probabilité fixée, alors
            on fait muter le warrior.*/
            if(p<=this.probability){

                this.mutOneWarrior(w,bound);
            }

        }


    }
}
