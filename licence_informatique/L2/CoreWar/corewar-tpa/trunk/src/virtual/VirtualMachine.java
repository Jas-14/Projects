package virtual;

import errors.InvalidSizePgrm;
import virtual.instruction.Dat;
import virtual.instruction.Instruction;
import util.AbstractModeleEcoutable;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import java.util.List;

/**
 * Classe VirtualMachine permettant de simuler une machine avec une mémoire et le
 * nécessaire pour obtenir et exécuter les instructions contenues dans la mémoire.
 *
 */
public class VirtualMachine extends AbstractModeleEcoutable {
    /**
     * Mémoire représentée par un tableau d'virtual.instruction
     */
    public Instruction[] memory;
    /**
     *Constante de la taille du tableau
     */
    public static final int TAILLE=1024;

    /**
     * Warrior 1 de la machine
     */
    private Warrior w1;

    /**
     * Warrior 2 de la machine
     */
    private Warrior w2;

    /**
     * File des processus de la machine
     */
    private Queue<Warrior> processQueue;

    /**
     * Constructeur standard
     * @param w1 Le Warrior 1
     * @param w2 Le Warrior 2
     */
    public VirtualMachine(Warrior w1, Warrior w2){

        this.memory=new Instruction[TAILLE];
        this.initMemory();

        this.w1=w1;
        this.w2=w2;

        this.processQueue=new ArrayDeque<>();

        this.processQueue.add(w1);
        this.processQueue.add(w2);


    }

    /**
     * Getteur Warrior 1
     * @return Warrior 1
     */
    public Warrior getW1() {
        return w1;
    }

    /**
     * Getteur Warrior 2
     * @return Warrior 2
     */
    public Warrior getW2() {
        return w2;
    }


    /**
     * Méthode qui permet d'initialiser la machine avant un combat de Warriors
     * @throws InvalidSizePgrm Si les programmes sont trop grands pour entrer dans la machine
     */
    public void setMachine()throws InvalidSizePgrm{

        int[] initPosition;

        try{
            initPosition=this.writeInstruction(w1.getPrgrm(),w2.getPrgrm());
        }catch (InvalidSizePgrm e){

            throw e;

        }

        w1.setPointer(initPosition[0]);
        w2.setPointer(initPosition[1]);


    }

    /**
     * Getteur file des processus
     * @return La file des processus
     */
    public Queue<Warrior> getProcessQueue(){

        return this.processQueue;
    }

    /**
     * Méthode pour récupérer une instruction dans la mémoire
     * @param j La position à laquelle on veut récupérer notre instruction
     * @return L'instruction à l'indice j
     */
    public Instruction getFromMemory(int j){

        Instruction I=null;
        try{

            I=this.memory[j];
        }catch(IndexOutOfBoundsException e){

            e.printStackTrace();
            System.exit(-1);
        }

        return I;
    }

    /**
     * Méthode pour insérer une instruction dans la mémoire. Ou écraser l'emplacement mémoire avec une nouvelle instruction
     * @param I L'instruction à mettre en mémoire
     * @param j L'indice de l'emplacement mémoire
     */
    public void insertInstruction(Instruction I,int j){

        try{

            this.memory[j]=I;

        }catch (IndexOutOfBoundsException e){

            e.printStackTrace();
            System.exit(-1);
        }

    }

    /**
     * Méthode pour initialiser la mémoire avec des DAT 0
     */

    private void initMemory(){

        for (int i=0;i<TAILLE;i++){

            this.memory[i]=new Dat(1,0,null);

        }

    }

    /**
     * Méthode pour écrire à un endroit aléatoire de la mémoire le programme
     * à exécuter.
     * @param prgrm1 Les instructions du joueur1 à insérer dans la mémoire
     * @param prgrm2 Les instructions du joueur2 à insérer dans la mémoire
     * @return Un tableau contenant les deux pointeurs générés
     * @throws InvalidSizePgrm Si les programmes sont trop grands pour tenir dans la
     * mémoire
     */

    private int[] writeInstruction(List<Instruction> prgrm1, List<Instruction> prgrm2) throws InvalidSizePgrm {

        if(prgrm1.size()+prgrm2.size()>1024){

            throw new InvalidSizePgrm();

        }
        Random rand=new Random();

        int tempPointer=rand.nextInt(TAILLE);

        int tempPointer2=tempPointer;

        /*
        Schéma:

        tempPointer      tempPointer+pgrm1.size()
        |                 |
        v                 v
        ...................

        On veut que tempPointer2 soit après la fin du pgrm1 ou que
        tempPointer2+prgrm2.size() soit avant le début du prgrm1

         */
        while (!(tempPointer2>(tempPointer+prgrm1.size())) && !((tempPointer2+prgrm2.size())<tempPointer)){

            tempPointer2=rand.nextInt(TAILLE);

        }

        for (int i = 0; i <prgrm1.size() ; i++) {

            this.memory[(i+tempPointer)%TAILLE]=prgrm1.get(i);

        }
        
        for (int j = 0; j <prgrm2.size() ; j++) {

            this.memory[(j+tempPointer2)%TAILLE]=prgrm2.get(j);

        }

        return new int[]{tempPointer,tempPointer2};
        
    }

}
