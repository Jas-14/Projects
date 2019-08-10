package main;

import errors.CorewarException;
import errors.InvalidSizePgrm;
import virtual.instruction.Instruction;
import util.AbstractModeleEcoutable;
import virtual.util.Parser;
import virtual.util.Reader;
import virtual.VirtualMachine;
import virtual.Warrior;
import java.util.Queue;

/**
 * Classe qui organise tout le déroulement du CoreWar
 */
public class CoreWarGame extends AbstractModeleEcoutable {

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
     * Le nom du premier fichier
     */
    private String nameFile1;

    /**
     * Le nom du second fichier
     */
    private String nameFile2;

    /**
     * Le nombre de cycle actuels
     */
    private int nbC;


    /**
     * Constructeur pour la première partie de l'interface
     * graphique
     * @param defautText Le texte à afficher par défaut à
     * la place du nom des fichiers
     */
    public CoreWarGame(String defautText){
        super();

        this.nameFile1=defautText;
        this.nameFile2=defautText;


    }

    public String getNameFile1() {
        return nameFile1;
    }

    public void setNameFile1(String nameFile1) {
        this.nameFile1 = nameFile1;
        //Il faut actualiser le modèle
        this.fireChangement();
    }

    public String getNameFile2() {
        return nameFile2;
    }

    public void setNameFile2(String nameFile2) {
        this.nameFile2 = nameFile2;
        //Il faut actualiser le modèle
        this.fireChangement();
    }

    /**
     * Constructeur utilisé pour l'algoGénétique
     * @param w1 Le premier Warrior
     * @param w2 Le second Warrior
     */
    public CoreWarGame(Warrior w1,Warrior w2){

        this.machine=new VirtualMachine(w1,w2);
        try{
            this.initGame();
        }catch (CorewarException e){

            e.printStackTrace();
            System.exit(-1);
        }

        }


    /**
     * Constructeur utilisé lorsque les noms des fichiers sont connus
      * @param nameFile1 Le nom du premier fichier
     * @param nameFile2 Le nom du second fichier
     * @throws CorewarException A renvoyer au GUI pour affichage
     */
    public CoreWarGame(String nameFile1, String nameFile2)throws CorewarException{

        String realName2=nameFile2;

        //Pour différencier les warriors si les fichiers sont identiques
        if(realName2.equals(nameFile1)){

            realName2=realName2.concat("(1)");

        }

        Warrior w1 = new Warrior(nameFile1, Parser.prgrmToListInstruction(Reader.readPgrm(nameFile1)));

        Warrior w2 = new Warrior(realName2, Parser.prgrmToListInstruction(Reader.readPgrm(nameFile2)));

        this.machine = new VirtualMachine(w1, w2);

        this.initGame();


    }

    public VirtualMachine getMachine() {
        return machine;
    }

    /**
     * Méthode pour initialiser la machine virtuelle
     * @throws InvalidSizePgrm Si les programmes ne peuvent
     * pas rentrer dans la machine
     */
    public void initGame() throws InvalidSizePgrm{

        this.machine.setMachine();


    }

    /**
     * Boucle de jeu principale
     */
    public void gameLoop(){

        System.out.println("Match CoreWar."+System.lineSeparator()+this.machine.getW1()+" VS "+this.machine.getW2()+" !");

        //Compteur de cycle. Un cycle=un coup d'horloge=2 tours de boucle while
        this.nbC=0;

        while (!(this.isTerminated())){

            this.oneLoop(true);

        }

        System.out.println("Terminé");

        System.out.println("Gagnant: "+this.winnner(true));


    }


    /**
     * Boucle d'une séquence de jeu
     * @param isConsole Contrôle si il faut faire de l'affichage dans la console
     */
    public void oneLoop(boolean isConsole){

        //On récupère le warrior
        Warrior current=this.machine.getProcessQueue().remove();

        //Display
        if(isConsole) {
            System.out.println(this.display(current));
        }

        //On récupère son pointeur et l'instruction
        int pointer=current.getPointer();
        Instruction i=this.machine.getFromMemory(pointer);

        //Exécution et résultat
        pointer=i.execution(pointer,this.machine,current);
        current.setPointer(pointer);

        //On le remet dans la file
        this.machine.getProcessQueue().add(current);

        this.nbC++;
        System.out.println(this.nbC);
        this.fireChangement();
    }

    /**
     * Display en ligne de commande de la situation
     * @param w Le Warrrior actuel
     * @return Une chaîne représentant la situation à l'état actuel
     */
    public String display(Warrior w){

		  return "Joueur "+w+" Now in "+w.getPointer()+" : "+this.machine.getFromMemory(w.getPointer());

    }

    /**
     * Méthode pour déterminer si la partie est finie
     * @return true si un pointeur est mort, false sinon.
     */

    public boolean isTerminated(){

        //Aucun risque de tomber sur pointeur nul dans ce cas
        return this.machine.getW1().getPointer()==-1 || this.machine.getW2().getPointer()==-1 || this.nbC>2*MAXCYCLE;
    }

    /**
     * Méthode pour déterminer le gagnant.
     * @param isConsole Contrôle si il faut faire de l'affichage dans la console
     * @return Le Warrior vainqueur.
     */
    public Warrior winnner(boolean isConsole){

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

        if(isConsole) {

            System.out.println("Les cases: " + nbCaseW1 + " VS " + nbCaseW2);
        }

        if(nbCaseW1>nbCaseW2){

            return this.machine.getW1();
        }

        return this.machine.getW2();

    }

}
