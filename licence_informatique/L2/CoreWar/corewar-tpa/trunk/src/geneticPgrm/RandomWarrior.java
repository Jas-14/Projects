package geneticPgrm;

import errors.BadInstructionException;
import errors.CorewarException;
import main.CoreWarGame;
import virtual.instruction.Instruction;
import virtual.util.Parser;
import virtual.Warrior;

import java.util.*;

/**
 * Classe RandomWarrior, qui permet de générer aléatoirement des
 * Warriors pour notre algorithme génétique
 */
public class RandomWarrior {

    /**
     * Attribut statique OP, qui définit tout les opérateurs
     * de notre logiciel
     */
    private static final List<String> OP= Arrays.asList("ADD","CMP","DAT","DJZ","JMP","JMZ","MOV","SUB");

    /**
     * Attribut statique MF, qui définit tout les modes d'adressages de notre logiciel
     */
    private static final List<String> MF= Arrays.asList("","#","@");

    /**
     * Méthode getRandomOP, qui permet d'obtenir un opérateur
     * aléatoire
     * @param generator Le générateur pseudo-aléatoire
     * @return L'opérateur tiré au hasard
     */
    private static String getRandomOP(Random generator){

        return OP.get(generator.nextInt(OP.size()));

    }

    /**
     * Méthode getRandomField, qui permet d'obtenir un champ
     * aléatoire
     * @param generator Le générateur pseudo aléatoire
     * @param bound Le nombre maximal pour un champ d'adresse
     * @return Une chaîne de caractère représentant le champ
     */
    private static String getRandomField(Random generator,int bound){

        StringBuilder field=new StringBuilder();

        /*On ajoute un mode d'adresse au hasard parmi
        ceux de la liste MF
         */
        field.append(MF.get(generator.nextInt(MF.size())));

        /* Quand on pourra gérer les négatifs
        field.append(generator.nextInt(2*bound+1)-bound);
        */

        int a=generator.nextInt(bound+1);

        /*On génère l'adresse et on l'ajoute*/
        field.append(a);

        return field.toString();
    }

    /**
     * Méthode randomLine, qui permet de générer une instruction
     * aléatoirement
     * @param bound Le nombre maximal pour un champ d'adresse
     * @return Une instruction générée aléatoirement
     */
    public static Instruction randomLine(int bound){

        //C'est ici que l'on crée notre générateur pseudo-aléatoire
        Random generator=new Random();

        StringBuilder op=new StringBuilder();

        //On ajoute notre opérateur
        op.append(RandomWarrior.getRandomOP(generator)).append(" ");

        /*Nombre de champs à générer. S'adapte en fonction
        de l'opérateur
         */
        int nbFields=2;

        if(op.toString().contains("DAT") | op.toString().contains("JMP")){

            nbFields=1;

        }

        boolean notValid=true;

        StringBuilder line;

        Instruction current=null;

        /*Tant que l'instruction n'est pas valide*/
        while (notValid){

            /*
            On fait une copie du premier StringBuilder et
            on génère les champs nécessaires
             */
            line=new StringBuilder(op);
            for (int i = 0; i <nbFields ; i++) {

                line.append(RandomWarrior.getRandomField(generator,bound));

                //On ajoute les espaces si nécessaires
                if(i+1!=nbFields){

                    line.append(" ");
                }

            }

            /*
            Dans ce try:
            -Si l'erreur est catchée (une erreur forcément jetée
            par stringToInstruction), alors la variable
            notValid ne change pas de valeur.
            -Sinon, notValid passe à false et la boucle s'arrête
             */
            try{

                current= Parser.stringToInstruction(line.toString());
                notValid=false;

            }catch (CorewarException e){

                notValid=true;
            }

        }


        return current;


    }

    /**
     * Méthode randomPgrm qui permet de générer des programmes
     * de manière aléatoire
     * @param bound Le nombre maximal pour un champ d'adresse
     * @param lines Le nombre maximal pour les lignes
     * @return Un programme généré aléatoirement
     */
    public static List<Instruction> randomPgrm(int bound,int lines){

        /*On choisit aléatoirement le nombre de lignes entre 1
        et lines
         */
        Random generator=new Random();
        int nbLines=generator.nextInt(lines-1)+1;


        List<Instruction> pgrm=new ArrayList<>();

        for (int i = 0; i <nbLines ; i++) {

            pgrm.add(RandomWarrior.randomLine(bound));


        }

        return pgrm;

    }


    /**
     * Méthode randomWarriors qui génère des warriors
     * aléatoirement
     * @param bound Le nombre maximal pour un champ d'adresse
     * @param lines Le nombre maximal pour les lignes
     * @param number Le nombre de warriors à générer
     * @return Une liste de Warriors générés aléatoirement
     */
    public static List<Warrior> randomWarriors(int bound,int lines,int number){

        List<Warrior> populationGenerated=new ArrayList<>();

        for (int i = 0; i <number ; i++) {

            populationGenerated.add(new Warrior(Integer.toString(i), RandomWarrior.randomPgrm(bound, lines)));

        }


        return populationGenerated;

    }



}
