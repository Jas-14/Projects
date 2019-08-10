package virtual;
import virtual.instruction.Dat;
import virtual.instruction.Instruction;

import java.util.Random;
import java.util.List;
/**
 * Classe VirtualMachine permettant de simuler une machine avec une mémoire et le nécessaire pour obtenir et
 * exécuter les instructions contenues dans la mémoire
 *
 */
public class VirtualMachine {
    //Ajouter un autre pointeur pour en avoir un par joueur. Pointeur Rouge et Pointeur Bleu

    /**
     * Pointeur pour se déplacer dans le tableau
     */
    public int pointer;
    /**
     * Mémoire représentée par un tableau d'instruction
     */
    public Instruction[] memory; //Prochainement arraylist
    /**
     *Constante de la taille du tableau
     */
    public static final int TAILLE=1024;
    /**
     * Constructeur de la classe
     */

    public VirtualMachine(List<Instruction> prgrm){

        this.memory=new Instruction[TAILLE];
        this.initMemory();

        this.pointer=this.writeInstruction(prgrm);


    }

    //Constructeur pour test_parser
    public VirtualMachine(){

        this.memory=new Instruction[TAILLE];

        this.pointer=0;

    }

    /**
     * Getteur pointeur
     * @return La position du pointeur
     */
    public int getPointer() {
        return pointer;
    }

    /**
     * Setteur pointeur
     * @param pointer La nouvelle position du pointeur
     */
    public void setPointer(int pointer) {
        this.pointer = pointer;
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

            this.memory[i]=new Dat(1,0);

        }

    }

    /**
     * Méthode pour écrire à un endroit aléatoire de la mémoire le programme
     * à exécuter.
     * @param prgrm Les instructions à insérer dans la mémoire
     * @return La position de la première instruction à exécuter
     */

    public int writeInstruction(List<Instruction> prgrm){

        Random rand=new Random();

        int tempPointer=rand.nextInt(TAILLE);

        for (int i = 0; i <prgrm.size() ; i++) {

            this.memory[(i+tempPointer)%TAILLE]=prgrm.get(i);

        }

        return tempPointer;




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
	/**
	 *Méthode permettant de savoir si la partie est finie. (A modifier lors introduction mode deux joueurs)
	 */
	/*public boolean isTerminated(){
		if(this.pointer==null){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 *Méthode initialisant la position des joueurs aléatoirement. (A modifier lors introduction mode deux joueurs)
	 */
	/*public void setRandomly(List<Instruction> program){
		int k=this.pointer;
		for (Instruction instruction : program){
			this.memory[k]=instruction;
			k=(k+1)%TAILLE;
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
