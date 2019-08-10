package virtual;
import errors.InvalidSizePgrm;
import instruction.Dat;
import instruction.Instruction;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import java.util.List;

/**
 * Classe VirtualMachine permettant de simuler une machine avec une mémoire et le nécessaire pour obtenir et
 * exécuter les instructions contenues dans la mémoire
 *
 */
public class VirtualMachine {
    /**
     * Mémoire représentée par un tableau d'instruction
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
    public Queue<Warrior> processQueue;

    /**
     * Constructeur de la classe
     */
    public VirtualMachine(Warrior w1, Warrior w2) throws InvalidSizePgrm{

        this.memory=new Instruction[TAILLE];
        this.initMemory();
        //this.pointer1=0;
        //this.pointer2=0;

        this.w1=w1;
        this.w2=w2;

        this.processQueue=new ArrayDeque<>();

        int[] initPosition;

        try{
            initPosition=this.writeInstruction(w1.getPrgrm(),w2.getPrgrm());
        }catch (InvalidSizePgrm e){

            throw e;

        }

        w1.setPointer(initPosition[0]);
        w2.setPointer(initPosition[1]);

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

    //Constructeur pour test_parser
    public VirtualMachine(){

        this.memory=new Instruction[TAILLE];

        //this.pointer1=0;

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

    public void initMemory(){

        for (int i=0;i<TAILLE;i++){

            this.memory[i]=new Dat(1,0,null);

        }

    }

    /**
     * Méthode pour écrire à un endroit aléatoire de la mémoire le programme
     * à exécuter.
     * @param prgrm1 Les instructions du joueur1 à insérer dans la mémoire
     * @param prgrm2 Les instructions du joueur2 à insérer dans la mémoire
     */

    public int[] writeInstruction(List<Instruction> prgrm1, List<Instruction> prgrm2) throws InvalidSizePgrm {

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
        
        // Remplace la valeur des pointeurs des joueurs pour les initialiser au début de leurs intructions.

        return new int[]{tempPointer,tempPointer2};
        
    }
    




    //Les méthodes ci dessous ont été commenté pour éviter les erreurs à la compilation
	/*public VirtualMachine() {
		
		Random rand = new Random();
		
		this.pointer=rand.nextInt(TAILLE-min+1)+min; //Initialisation du pointeur à  la première case du tableau. Plus tard, il se placera au début du programme du joueur auquel il sera affecté
				
		this.memory= new Instruction [TAILLE]; //Initialise le tableau avec une taille de 1024
	}
	/**
	 * Getteur de l'attribut pointer
	 * @return La position du pointeur de la machine
	 */
	/*public int getPointer() {
		return this.pointer; 
	}
	/**
	 * Méthode permettant d'avoir un affichage sommaire de la machine
	 */
	/*public void toString(){
		for(int i=0; i<this.memory.length; i++){
			System.out.println(this.memory[i].getInstruction());
			System.lineSeparator();
		}
	}
	
	/*
	 Getteur de l'instruction courante, celle sur laquelle le pointeur est positionné
	public void getInstruction(){
		
	}*/
	
	
/*   -- Identifier les cases en fonction du joueur qui y a introduit une instruction. ex : this.machine[3] = joueur A. Créer un deuxième tableau qui se modifie en fonction
des actions sur le tableau "principal" ? Dans ce cas, il faudra ajouter une autre méthode pour le réaliser.
*/
}
