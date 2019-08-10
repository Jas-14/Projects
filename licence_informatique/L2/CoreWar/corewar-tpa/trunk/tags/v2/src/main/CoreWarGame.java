package main;

import errors.CorewarException;
import instruction.Instruction;
import virtual.Parser;
import virtual.Reader;
import virtual.VirtualMachine;
import virtual.Warrior;
import java.util.Queue;

/**
 * Classe qui organise tout le déroulement du CoreWar
 */
public class CoreWarGame {

    /**
     * La machine virtuelle dans laquelle se déroule le jeu
     */
    private VirtualMachine machine;

    /**
     * Constante qui détermine le nombre maximum de cycle pour lequel
     * le jeu peut tourner.
     */
    private static final int MAXCYCLE=8000;


    /**
     * Constructeur
     * @param nameFile1 Le nom du fichier 1
     * @param nameFile2 Le nom du fichier 2
     */
    public CoreWarGame(String nameFile1, String nameFile2){

        String realName2=nameFile2;

        //Pour différencier les warriors si les fichiers sont identiques
        if(realName2.equals(nameFile1)){

            realName2=realName2.concat("(1)");

        }

        /*
        Instanciation de deux Warrior pour les passer à la machine
        virtuelle.
         */
        Warrior w1=new Warrior(nameFile1,Parser.prgrmToListInstruction(Reader.readPgrm(nameFile1)));
        
        Warrior w2= new Warrior(realName2,Parser.prgrmToListInstruction(Reader.readPgrm(nameFile2)));

        try{
            this.machine=new VirtualMachine(w1,w2);
        }catch (CorewarException e){

            e.printStackTrace();
            System.exit(-1);

        }



    }

    /**
     * Boucle de jeu principale
     */
    public void gameLoop(){

        System.out.println("Match CoreWar."+System.lineSeparator()+this.machine.getW1()+" VS "+this.machine.getW2()+" !");

        //Récuparation de la file des processus
        Queue<Warrior> processQueue=this.machine.getProcessQueue();

        Warrior current=processQueue.peek();

        //Compteur de cycle. Un cycle=un coup d'horloge=2 tours de boucle while
        int c=0;

        while (!(this.isTerminated(current)) && c<2*MAXCYCLE){

            /*
            A chaque boucle, on retire le Warrior qui joue et on
            le remets à la fin de la boucle.
             */
            current=processQueue.remove();
            System.out.println(this.display(current));
            this.oneLoop(current);
            processQueue.add(current);
            c++;

        }

        System.out.println("Terminé");

        System.out.println("Gagnant: "+this.winnner());


    }

    /**
     * Méthode pour effectuer un tour
     * @param current Le Warrior qui joue à ce tour là
     */
    public void oneLoop(Warrior current){

        /*
        On récupère le pointeur afin d'exécuter l'instruction à
        la case mémoire indiquée, et on modifie le pointeur du
        Warrior après l'exécution.
         */
        int pointer=current.getPointer();

        Instruction i=this.machine.getFromMemory(pointer);

        pointer=i.execution(pointer,this.machine,current);

        current.setPointer(pointer);
    }

    /**
     * Partie display ligne de commande
     * @param w Le Warrior actuel
     * @return Une chaîne décrivant la situation actuelle
     */
    public String display(Warrior w){
			
		  /*if(Player==1){
		  	return "Joueur 1: Now in "+this.machine.getPointer1()+" : "+this.machine.getFromMemory(this.machine.getPointer1());
		  }
        else{
        	return "Joueur 2: Now in "+this.machine.getPointer2()+" : "+this.machine.getFromMemory(this.machine.getPointer2());
        }*/

		  return "Joueur "+w+" Now in "+w.getPointer()+" :"+this.machine.getFromMemory(w.getPointer())+"Texte test: "+this.machine.getFromMemory(w.getPointer()).getLastAccessed();

    }

    /**
     * Méthode pour déterminer si la partie est finie
     * @param current Le Warrior qui vient de jouer, pour arrêter
     *                la partie dès qu'il est mort.
     * @return true si un pointeur est mort, false sinon.
     */
    public boolean isTerminated(Warrior current){

        return current.getPointer()==-1;

    }

    /**
     * Méthode pour déterminer le gagnant.
     * @return Le Warrior vainqueur.
     */
    public Warrior winnner(){

        /*
        Cas simple: L'un des deux Warriors est mort.
        C'est celui qui a le pointeur à -1.
         */
        if(this.machine.getW1().getPointer()==-1){

            return this.machine.getW2();
        }
        if(this.machine.getW2().getPointer()==-1){

            return this.machine.getW1();
        }

        /*
        Cas Complexe: Le nombre maximal de cycle a été atteint. Pour
        sortir un vainqueur, on compte le nombre de case qui ont été
        exécuté par le Warrior (ou qui appartienne au Warrior).
        Celui qui a le plus de case est désigné vainqueur.
        Les cases type DAT #0 n'appartiennent à personne.
         */
        int nbCaseW1=0;
        int nbCaseW2=0;

        for (int i = 0; i <VirtualMachine.TAILLE ; i++) {

            Instruction I=this.machine.getFromMemory(i);

            if(I.getLastAccessed()!=null){

                if(I.getLastAccessed()==this.machine.getW1()){

                    nbCaseW1++;

                }else {

                    nbCaseW2++;

                }
            }



        }

        System.out.println("Les cases: "+nbCaseW1+" VS "+nbCaseW2);

        if(nbCaseW1>nbCaseW2){

            return this.machine.getW1();
        }

        return this.machine.getW2();

    }

}
