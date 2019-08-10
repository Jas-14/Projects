package tests;

import geneticPgrm.RandomWarrior;

import java.util.Random;

/**
 * Classe de test ayant servi à effectuer des expérimentations
 */
public class Test_Random {

    /**
     * Méthode main
     * @param args non utilisé
     */
    public static void main(String[] args) {

        for (int i =10; i <Math.pow(10,3)+1 ; i+=10) {

            long time=System.currentTimeMillis();

            RandomWarrior.randomWarriors(5,10,i);

            System.out.println(i+" "+(System.currentTimeMillis()-time));
            
        }

        /*for (int i = 0; i <5 ; i++) {
            System.out.println("Programme "+i);
            RandomWarrior.randomPgrm(5, 5);

        }*/

        //long begin=System.currentTimeMillis();
        //List<Warrior> popTest=RandomWarrior.randomWarriors(5,5,20);

        //System.out.println("Done in "+(System.currentTimeMillis()-begin));

        //Fighting testF=new Tournament();

        //testF.fight(popTest);

        //Selection testS=new Best20Warriors();

        //List<Warrior> bests=testS.doSelection(popTest);

       // Evolution testE=new MainMutation();

        //testE.mutWarriors(popTest,5);

        //Crossing testC=new DefautCrossing();

        //List<Warrior> newPop=testC.crossAll(popTest);

        //System.out.println(newPop.size());

        //Warrior e=popTest.get(0);

       // Writer.writePgrm(e);

        /*WarriorGeneration testWG=new WarriorGeneration(100,5,5,100,new Tournament(),new Best20Warriors(),new MainMutation(),new DefautCrossing());

        Warrior e=testWG.generation();

        Writer.writePgrm(e,"after20");*/

    }
}
