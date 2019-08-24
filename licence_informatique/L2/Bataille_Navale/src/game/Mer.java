package game;

import game.player.Player;
import util.AbstractListenableModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Classe Mer, qui est une représentation d'une grille de jeu
 * de la bataille navale
 */
public class Mer extends AbstractListenableModel {

    /**
     * Attribut statique pour définir comment représenter le
     * symbole "touché" en affichage console
     */
    private static final String TOUCHE="X";

    /**
     * Attribut statique pour définir comment représenter le
     * symbole "raté" en affichage console
     */
    private static final String RATE="!";

    /**
     * Attribut grille, un tableau de bateau
     */
    private Bateau[][] grille;

    /**
     * Attribut propriétaire, le joueur qui possède les bateaux
     * de la grille, et donc par extension, celui qui possède la
     * grille
     */
    private Player proprietaire;

    /**
     * Attribut attaquant, le joueur qui attaque les bateaux sur
     * cette grille
     */
    private Player attaquant;

    /**
     * Constructeur standard
     * @param proprietaire Le joueur qui possède les bateaux sur
     * cette grille
     * @param attaquant Le joueur qui attaque les bateaux sur
     * cette grille
     */
    public Mer(Player proprietaire, Player attaquant) {

        this.grille = new Bateau[10][10];
        this.proprietaire = proprietaire;
        this.attaquant = attaquant;

    }

    public Player getProprietaire() {
        return proprietaire;
    }

    public Player getAttaquant() {
        return attaquant;
    }

    /**
     * "Getteur" de bateau sur la grille
     * @param x Coordonnée qui correspond à la ligne
     * @param y Coordonnée qui correspond à la colonne
     * @return Le bateau sur cette case (ou null si il n'y a pas de
     * bateau)
     */
    public Bateau getBateau(int x, int y){

        return this.grille[x][y];
    }

    /**
     * Méthode qui permet de placer un bateau sur une grille.
     * Nous plaçons une instance du bateau sur chaque case que
     * le bateau occupe, selon sa taille et sa direction
     * @param b Le bateau à placer dans la grille
     */
    public void placerUnBateau(Bateau b){

        //Deux cas selon la direction
        switch (b.getDirection()){

            case(Bateau.DIRH):
                //On ne change pas de ligne mais on avance sur les colonnes
                for (int j = b.getY(); j <b.getY()+b.getTaille() ; j++) {

                    this.grille[b.getX()][j]=b;

                }
                break;
            case (Bateau.DIRV):
                //On ne change pas de colonne, mais on avance sur les lignes
                for(int i=b.getX();i<b.getX()+b.getTaille();i++){

                    this.grille[i][b.getY()]=b;
                }
                break;



        }



    }

    /**
     * Méthode qui permet d'envoyer le coup sur la grille, afin
     * de distinguer l'envoyer du coup d'une simple consultation
     * de la grille.
     * @param coord Les coordonnées de la case sur laquelle on
     * envoie le coup
     * @return Un booléen qui exprime si un bateau est touché par
     * le coup ou non
     */
    public boolean envoyerCoup(List<Integer> coord){

        //On mets à jour le modèle car là c'est un changement
        this.modelChange();

        return estTouche(coord);
    }

    /**
     * Méthode qui permet de savoir si un bateau est touché à
     * cette case
     * @param coord Les coordonnées de la case que l'on veut
     * vérifier
     * @return Un booléen qui exprime si un bateau est touché
     * par le coup ou non
     */
    public boolean estTouche(List<Integer> coord){

        return this.grille[coord.get(0)][coord.get(1)]!=null;

    }

    @Override
    public String toString() {

        /*
        Fonctionnement du toString:
        Pour chaque coordonnée, si elle a déjà été joué, on
        regarde si il y a un bateau à cette case. Si oui, on
        a touché un bateau, si non, c'est un tir raté
         */
        Set coupsJoues = this.attaquant.getCoupsJouer();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                List<Integer> coords = new ArrayList<>(Arrays.asList(i, j));

                String symbole = " ";
                if (coupsJoues.contains(coords)) {

                    if(this.grille[i][j]==null){

                        symbole=Mer.RATE;
                    }else{

                        symbole=Mer.TOUCHE;
                    }


                }

                builder.append(symbole).append(" ");

            }


            builder.append(System.lineSeparator());

        }

        return builder.toString();

    }

}
