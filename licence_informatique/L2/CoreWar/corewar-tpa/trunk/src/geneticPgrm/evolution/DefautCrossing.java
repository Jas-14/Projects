package geneticPgrm.evolution;

import virtual.instruction.Instruction;
import virtual.Warrior;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Classe DefautCrossing qui définit notre manière standard de
 * croiser les warriors.
 */
public class DefautCrossing implements Crossing {

    /**
     * Attribut nbCross qui définit le nombre de croisements à
     * effectuer pour chaque warrior.
     */
    private int nbCross;

    /**
     * Constructeur standard
     * @param nbCross Le nombre de croisement à effectuer pour
     * chaque warrior.
     */
    public DefautCrossing(int nbCross){

        this.nbCross=nbCross;
    }

    /**
     * Constructeur annexe
     */
    public DefautCrossing(){

        this(3);
    }

    @Override
    public List<Warrior> crossAll(List<Warrior> population){

        List<Warrior> newPopolation=new ArrayList<>(population);

        /*
        Si nous voulons croiser nos warriors n fois, nous devons
        faire tourner la boucle de croisement n fois. Nous ne
        croisons que les warriors de la liste population, passée
        en argument
         */
        for (int i = 0; i <this.nbCross+1 ; i++) {

            /*Liste que l'on pourra vider pour choisir nos
            warriors à croiser
             */
            List<Warrior> tempPopulation=new ArrayList<>(population);

            while (!(tempPopulation.isEmpty())){

                Warrior w1=this.chooseRandomW(tempPopulation);

                tempPopulation.remove(w1);

                /*On essaye de retirer deux éléments de la liste
                afin de les croiser. Si ce n'est pas possible, ce
                n'est pas grave, il ne se passe rien.
                 */
                if(!(tempPopulation.isEmpty())){

                    Warrior w2=this.chooseRandomW(tempPopulation);

                    tempPopulation.remove(w2);

                    Warrior result=this.cross(new ArrayList<>(Arrays.asList(w1,w2)));

                    newPopolation.add(result);

                }


            }



        }


        return newPopolation;


    }

    /**
     * Méthode chooseRandomW qui permet de choisir un Warrior au
     * hasard dans une population donnée.
     * @param population La population dans laquelle on veut choisir notre Warrior.
     * @return Le Warrior sélectionné
     */
    private Warrior chooseRandomW(List<Warrior> population){

        Random generator=new Random();

        return population.get(generator.nextInt(population.size()));

    }

    @Override
    public Warrior cross(List<Warrior> warriorsToCross){


        List<Instruction> pgrm=new ArrayList<>();

        /*
        Afin de croiser plus facilement nos programmes, nous
        effectuons une copie de ces derniers.
         */
        List<List<Instruction>> allCopy=this.getCopyPgrm(warriorsToCross);

        /*
        Le nombre de lignes du nouveau programme est déterminé
        par la moyenne des lignes des programmes à croiser
         */
        int lines=this.means(warriorsToCross);

        Random rand=new Random();

        //Pour chaque ligne du nouveau programme:
        for (int i = 0; i <lines ; i++) {

            //On choisit un programme au hasard
            int n=rand.nextInt(allCopy.size());

            List<Instruction> selectPgrm=allCopy.get(n);

            //On prends sa première instruction
            Instruction toCopy=selectPgrm.get(0);

            //On la retire
            selectPgrm.remove(toCopy);

            //On l'ajoute au nouveau programme
            pgrm.add(toCopy);

            /*Si le programme sélectionné est désormais vide,
            on le retire de la liste.
             */
            if(selectPgrm.isEmpty()){

                allCopy.remove(selectPgrm);
            }

        }

        return new Warrior(this.name(warriorsToCross),pgrm);


    }

    /**
     * Méthode sumOfSize, qui permet de compter le nombre de
     * lignes des programmes de tout les warriors de la liste.
     * @param ws La liste des warriors concernée
     * @return Le nombre total de lignes des programmes
     */
    private int sumOfSize(List<Warrior> ws){

        int sum=0;

        for (Warrior w:ws) {

            sum=sum+w.getPrgrm().size();

        }

        return sum;

    }

    /**
     * Méthode getCopyPgrm qui permet d'effectuer une copie
     * profonde des programmes des warriors de la liste.
     * @param ws Les warriors concernés
     * @return Une liste des copies des programmes des Warriors
     */
    private List<List<Instruction>> getCopyPgrm(List<Warrior> ws){

        List<List<Instruction>> allPgrm=new ArrayList<>();

        //Pour chaque Warrior:
        for (Warrior w:ws) {

            List<Instruction> newPgrm=new ArrayList<>();

            //On récupère son programme
            List<Instruction> currentPgrm=w.getPrgrm();

            //Pour chaque instruction de son programme
            for (Instruction i:currentPgrm) {

                //On ajoute sa copie au programme copie
                newPgrm.add(i.clone());

            }

            allPgrm.add(newPgrm);


        }


        return allPgrm;

    }

    /**
     * Méthode means, permettant d'effectuer la moyenne des
     * lignes de tout les programmes des warriors de la liste.
     * @param ws Les warriors concernés
     * @return La moyenne des lignes
     */
    private int means(List<Warrior> ws){

        int sum=this.sumOfSize(ws);

        return sum/ws.size();

    }

    /**
     * Méthode name, permettant de nommer le nouveau Warrior
     * obtenu par croisement
     * @param ws Les warriors qui ont permi d'obtenir ce
     * croisement.
     * @return Le nom généré
     */
    private String name(List<Warrior> ws){

        /*
        Nous "additionnons" les noms des warriors. Même
        si on finit par dépasser la limite physique de
        représentation des int, ça reste de taille
        acceptable au niveau du nombre de caractère
         */
        int name=0;

        for (Warrior w:ws) {

            name+=Math.floorMod(Integer.parseInt(w.getName()),Integer.MAX_VALUE);

        }



        return Integer.toString(name);

    }
}
