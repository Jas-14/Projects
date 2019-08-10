package geneticPgrm.evolution;

import virtual.Warrior;
import virtual.instruction.Instruction;

import java.util.List;
import java.util.Random;

/**
 * Classe DeleteLineMutation, qui permet de retirer une ligne
 * d'un programme, selon une probabilité.
 */
public class DeleteLineMutation implements Evolution {

    /**
     * Attribut probabilité, pour définir la probabilité de
     * mutation
     */
    private double probability;

    /**
     * Constructeur standard
     * @param probability La probabilité de mutation
     */
    public DeleteLineMutation(double probability){

        this.probability=probability;

    }

    /**
     * Constructeur annexe
     */
    public DeleteLineMutation(){

        this(0.2);
    }

    /**
     * Méthode mutOneWarrior, qui permet de supprimer une ligne
     * du programme sélectionné
     * @param w Le Warrior sélectionné
     * @param population La population dont il provient
     */
    private void mutOneWarrior(Warrior w,List<Warrior> population){

        Random rand=new Random();

        List<Instruction> pgrm=w.getPrgrm();

        int n=rand.nextInt(pgrm.size());

        pgrm.remove(n);

        /*
        Si le programme est vide, le warrior n'existe plus,
        on le supprime de la population
         */
        if(pgrm.isEmpty()){

            population.remove(w);
        }

    }

    @Override
    public void mutWarriors(List<Warrior> population,int a){


        /*Parcours des warriors avec une boucle simple, car
        l'arraylist ne supporte pas qu'on la parcourt avec
        un itérateur et qu'on supprime des éléments en même temps
         */
        for (int i = 0; i <population.size() ; i++) {

            double p=Math.random();

            /*On tire un nombre entre 0 et 1. Si ce nombre est
            plus petit ou égal à la probabilité fixée, alors
            on fait muter le warrior.*/
            if(p<=this.probability){

                this.mutOneWarrior(population.get(i),population);
            }

        }




    }



}
