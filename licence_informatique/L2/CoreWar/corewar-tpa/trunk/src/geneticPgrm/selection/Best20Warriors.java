package geneticPgrm.selection;

import virtual.Warrior;

import java.util.Collections;
import java.util.List;

/**
 * Classe Best20Warriors, qui permet de choisir les 20
 * meilleurs warriors de notre population.
 */
public class Best20Warriors implements Selection {

    @Override
    public List<Warrior> doSelection(List<Warrior> population){

        /*Il suffit de trier la population, qui se retrouve
        rangée dans l'ordre décroissant des victoires*
         */
        Collections.sort(population);

        return population.subList(0,21);


    }

    @Override
    public Warrior getBest(List<Warrior> population){

        return this.doSelection(population).get(0);

    }
}
