package game.player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Classe IA, qui est un joueur qui joue de manière aléatoire
 */
public class IA extends Player {

    /**
     * Attribut qui recense tout les coups possible à la
     * bataille navale
     */
    private List<List<Integer>> coupsPossibles;

    /**
     * Constructeur standard
     */
    public IA(){
        super("IA");

        this.coupsPossibles=new ArrayList<>();

        this.constructionCoupsPossibles();

    }

    /**
     * Méthode qui construit les coups possibles du joueur
     */
    private void constructionCoupsPossibles(){

        for (int i = 0; i <10 ; i++) {
            for (int j = 0; j <10 ; j++) {

                List<Integer> coords= Arrays.asList(i,j);
                this.coupsPossibles.add(coords);

            }

        }



    }

    @Override
    public List<Integer> jouerCoup(){

        Collections.shuffle(this.coupsPossibles);

        List<Integer> coup=this.coupsPossibles.get(0);

        this.coupsPossibles.remove(coup);

        return coup;

    }
}
