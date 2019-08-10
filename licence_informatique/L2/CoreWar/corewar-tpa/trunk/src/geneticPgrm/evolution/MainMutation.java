package geneticPgrm.evolution;

import virtual.Warrior;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe MainMutation, qui gère tous les outils que nous avons
 * implémentés pour la mutation
 */
public class MainMutation implements Evolution {


    /**
     * Attribut tools, l'ensemble des outils permettant de
     * faire muter nos warriors
     */
    private List<Evolution> tools;

    /**
     * Constructeur standard
     * @param tools La liste des outils de mutation
     */
    public MainMutation(List<Evolution> tools){

        this.tools=tools;

    }

    /**
     * Constructeur annexe
     */
    public MainMutation(){

        this.tools=new ArrayList<>(Arrays.asList(new LineMutation(),new AddLineMutation(),new DeleteLineMutation()));

    }

    @Override
    public void mutWarriors(List<Warrior> population,int bound){

        /*
        Pour chaque outil, on fait appel à sa méthode mutWarriors.
        Cela fait tourner n fois la liste de population, si il y
        a n outils, mais cela donne également n chances à un
        warrior de muter.
         */
        for (Evolution tool:tools) {

            tool.mutWarriors(population,bound);

        }
    }
}
