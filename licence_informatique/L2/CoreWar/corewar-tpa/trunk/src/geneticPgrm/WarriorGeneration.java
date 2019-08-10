package geneticPgrm;

import geneticPgrm.evolution.Crossing;
import geneticPgrm.evolution.DefautCrossing;
import geneticPgrm.evolution.Evolution;
import geneticPgrm.evolution.MainMutation;
import geneticPgrm.fighting.Fighting;
import geneticPgrm.fighting.Tournament;
import geneticPgrm.selection.Best20Warriors;
import geneticPgrm.selection.Selection;
import virtual.Warrior;

import java.util.List;

/**
 * Classe WarriorGeneration, qui exécute l'algorithme pour
 * générer des programmes performants
 */
public class WarriorGeneration {

    /**
     * Attribut iterationsMax, le nombre de fois que la
     * boucle principale de l'algorithme va s'exécuter
     */
    private int iterationMax;

    /**
     * Attribut bound, le nombre maximal pour un champ d'adresse
     */
    private int bound;

    /**
     * Attribut linesMax, le nombre maximal de lignes pour un
     * programme généré aléatoirement
     */
    private int linesMax;

    /**
     * Attribut nbWarriorsInit, le nombre de Warriors à générer
     * aléatoirement
     */
    private int nbWarriorInit;

    /**
     * Attribut wayOfFight, qui définit l'objet utilisé pour
     * faire combattre les Warriors
     */
    private Fighting wayOfFight;

    /**
     * Attribut wayOfSelection, qui définit l'objet utilisé pour
     * sélectionner les meilleurs Warriors
     */
    private Selection wayOfSelection;

    /**
     * Attribut wayOfEvolution, qui définit l'objet utilisé pour
     * faire muter les Warriors
     */
    private Evolution wayOfEvolution;

    /**
     * Attribut wayOfCrossing, qui définit l'objet utilisé pour
     * croiser les Warriors
     */
    private Crossing wayOfCrossing;

    /**
     * Attribut statique DEFITERATION, qui définit le nombre
     * d'itérations par défaut
     */
    private static final int DEFITERATION=100;

    /**
     * Attribut statique DEFBOUND, qui définit le nombre maximal
     * pour un champ d'adresse par défaut
     */
    private static final int DEFBOUND=5;

    /**
     * Attribut statique DEFLINES, qui définit le nombre maximal
     * de lignes pour un programme par défaut
     */
    private static final int DEFLINES=10;

    /**
     * Attribut statique DEFNBWARRIORS, qui définit le nombre de
     * Warriors à générer par défaut
     */
    private static final int DEFNBWARRIORS=200;

    /**
     * Constructeur standard
     * @param it Le nombre d'itérations du programme
     * @param bound Le nombre maximal pour un champ d'adresse
     * @param linesMax Le nombre maximal de ligne pour un programme
     * @param nbWarriorInit Le nombre de Warriors à générer
     * @param f L'objet qui définit comment faire combattre les Warriors
     * @param s L'objet qui définit comment sélection les Warriors
     * @param e L'objet qui définit comment faire muter les Warriors
     * @param c L'objet qui définit comment croiser les Warriors
     */
    public WarriorGeneration(int it,int bound,int linesMax, int nbWarriorInit, Fighting f, Selection s,Evolution e,Crossing c){

        this.iterationMax=it;
        this.bound=bound;
        this.linesMax=linesMax;
        this.nbWarriorInit=nbWarriorInit;

        this.wayOfFight=f;
        this.wayOfSelection=s;
        this.wayOfEvolution=e;
        this.wayOfCrossing=c;

    }

    /**
     * Constructeur annexe par défaut
     */
    public WarriorGeneration(){

        this(WarriorGeneration.DEFITERATION,WarriorGeneration.DEFBOUND,WarriorGeneration.DEFLINES,WarriorGeneration.DEFNBWARRIORS,new Tournament(),new Best20Warriors(),new MainMutation(),new DefautCrossing());
    }

    /**
     * Constructeur standard possible
     * @param it Le nombre d'itérations du programme
     * @param nbWarriors Le nombre de Warriors à générer
     */
    public WarriorGeneration(int it,int nbWarriors){

        this(it,WarriorGeneration.DEFBOUND,WarriorGeneration.DEFLINES,nbWarriors,new Tournament(),new Best20Warriors(),new MainMutation(),new DefautCrossing());


    }

    /**
     * Méthode chooseBest, qui permet de choisir le meilleur
     * Warrior dans une population
     * @param populationFinal La population
     * @return Le meilleur Warrior de la population
     */
    public Warrior chooseBest(List<Warrior> populationFinal){

        this.wayOfFight.fight(populationFinal);

        return this.wayOfSelection.getBest(populationFinal);

    }

    /**
     * Méthode generation, l'algorithme qui génère des programmes
     * performants.
     * @return Le Warrior généré par cet algorithme
     */
    public Warrior generation(){

        List<Warrior> population= RandomWarrior.randomWarriors(this.bound,this.linesMax,this.nbWarriorInit);

        //Coeur du programme
        for (int i = 0; i <this.iterationMax ; i++) {

            this.wayOfFight.fight(population);

            population=this.wayOfSelection.doSelection(population);

            this.resetWins(population);

            this.wayOfEvolution.mutWarriors(population,this.bound);
            population=this.wayOfCrossing.crossAll(population);

        }

        return this.chooseBest(population);





    }

    public int getIterationMax(){

        return iterationMax;
    }

    private void resetWins(List<Warrior> population){

        for (Warrior w:population){

            w.resetWin();

        }
    }
}
