package geneticPgrm;

import virtual.Warrior;

/**
 * Classe MainGeneration contenant le main de notre partie
 * génération de programmes performants
 */
public class MainGeneration {

    /**
     * Main de l'application
     * @param args Arguments pour l'algorithme génétique
     */
    public static void main(String[] args) {

        WarriorGeneration generation=null;

        //Si aucun argument, on utilise le constructeur annexe
        if(args.length==0){

            generation=new WarriorGeneration();

        /*2 arguments, qui sont le nombre d'itérations et le
        nombres de warriors à générer au départ. On utilise le
        constructeur approprié, si les arguments sont corrects
         */
        }else if(args.length==2){

            int it=0;
            int nbWarriors=0;

            try{

                it=Integer.parseInt(args[0]);
                nbWarriors=Integer.parseInt(args[1]);
            }catch (NumberFormatException e){

                e.printStackTrace();
                System.exit(-1);
            }
            if(it==0 || nbWarriors ==0){

                System.err.println("Attention, itérations et nombre de Warriors ne peuvent pas prendre la valeur 0");
                System.exit(-1);
            }

            if(nbWarriors<40){

                System.err.println("Attention, le nombre de warriors doit être supérieur à 40 pour que ça soit intéressant pour notre méthode de sélection");
                System.exit(-1);
            }

            generation=new WarriorGeneration(it,nbWarriors);

        /*Sinon, on stoppe le programme pour éviter les problèmes*/
        }else {

            System.err.println("Attention, soit vous renseignez itérations et nbWarriors dans la ligne de commande, soit rien du tout");
            System.exit(-1);
        }

        Warrior w=generation.generation();

        //A la fin de l'exécution, on écrit le programme obtenu
        Writer.writePgrm(w,"GA"+w.getName());


    }
}
