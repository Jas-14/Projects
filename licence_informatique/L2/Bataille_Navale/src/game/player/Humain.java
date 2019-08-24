package game.player;

import java.util.*;

/**
 * Classe Humain, qui est un joueur qui joue ses coups via le
 * clavier ou la souris
 */
public class Humain extends Player {

    /**
     * Attribut qui permet de stocker le scanner de l'humain
     */
    private Scanner s;

    /**
     * Constructeur standard
     * @param name Nom, dans notre projet, c'est une fonctionnalité
     * peu utilisée
     * @param s Le Scanner qui va être utilisé par le joueur
     */
    public Humain(String name,Scanner s){
        super(name);

        this.s=s;
    }
    @Override
    public List<Integer> jouerCoup(){

        List<Integer> coup=null;

        //On aurait pu écrire un do while pour ne pas gérer le cas null
        while (!(coupValide(coup))){


            System.out.println("Saisissez votre coup. Vous devez saisir la ligne, puis la colonne, ces deux valeurs sont entre 0 et 9"+System.lineSeparator()+"Si vous jouez un coup déjà joué"+
                    ", un nouveau coup sera demandé");

            try {
                int i = s.nextInt();
                int j = s.nextInt();
                coup=new ArrayList<>(Arrays.asList(i,j));

            }catch (InputMismatchException e){

                System.out.println("Attention, vous n'avez pas saisi des nombres!");
                s.next();
            }


        }

        return coup;


    }

    /**
     * Méthode pour évaluer si le coup donné par l'humain est
     * valide et peut être joué. Pour être valide, un coup doit
     * être différent null, se trouver dans la grille et ne pas
     * avoir été déjà joué
     * @param coup Le coup donné par l'humain
     * @return Un booléen qui indique si le coup est valide
     */
    public boolean coupValide(List<Integer> coup){

        if(coup==null){

            return false;
        }else {

            return !(this.coupsJoues.contains(coup)) && !(coup.get(0)<0) && !(10<coup.get(0)) && !(coup.get(1)<0) && !(10<coup.get(1));
        }

    }
}
