package game.player;

import game.Bateau;
import game.Mer;

import java.util.*;

/**
 * Classe Player  qui est une représentation d'un joueur de la bataille navale
 */

public abstract class Player {


    /**
     * Liste des tailles de chaque bateau de chaque joueur
     */
    public static final List<Integer> listTailleBateau = Arrays.asList(5,5,3,2,2);

    /**
     * Attribut name represente le nom du joueur
     */
    private String name;

    /**
     * Attribut listeBateau représente une Liste des bateaux
     */
    private List<Bateau> listeBateau;

    /**
     * Attribut bateauxCoules représente une
     * liste des bateaux coulés
     */
    private List<Bateau> bateauxCoules;

    /**
     * Attribut bateauxActules représente une
     * liste des bateaux présents actuelemmenet dans la grille
     */
    private List<Bateau> bateauxActuels;

    /**
     * Attribut coupsJoues represente une map d'une liste d'entiers pour
     * les coups joués
     */
    protected Set<List<Integer>> coupsJoues;


    /**
     * Constructeur standard
     * @param name Le nom du joueur
     */
    public Player(String name){
        this.name=name;
        this.coupsJoues=new HashSet<>();
        this.listeBateau=new ArrayList<>();
        this.bateauxCoules=new ArrayList<>();
        this.bateauxActuels=new ArrayList<>();
    }

    public String getName() { return name; }

    public List<Bateau> getListeBateau() {
        return listeBateau;
    }

    public Set<List<Integer>> getCoupsJouer() {
        return coupsJoues;
    }

    public List<Bateau> getBateauxCoules(){return bateauxCoules;}

    public List<Bateau> getBateauxActuels(){return bateauxActuels;}

    /**
     * Méthode abstraite qui retourne tout les coups joués
     * @return liste des coups joués
     */

    public abstract List<Integer> jouerCoup();

    /**
     * Méthode qui permet de savoir si on peut ajouter un bateau
     * avec une certaine taille dans une certaine case
     * dans la grille ou pas.
     * @param grille le tableau des bateaux
     * @param i Coordonnée qui correspond à la ligne
     * @param j Coordonnée qui correspond à la colonne
     * @param direction Attribut pour savoir si la direction du bateau
     * a ajouter dans la grille est horizontale ou verticale
     * @param tailleBateau La taille du bateau a ajouter dans la grille

     * @return Un booléen qui défini si un bateau peut etre
     * ajouter dans la grille ou pas.
     */

    private boolean estValide(Mer grille, int i, int j, int direction, int tailleBateau) {
        switch (direction){
            case(Bateau.DIRH):
                if(j+tailleBateau>9){
                    return false; }
                for (int k =j; k <j+tailleBateau ; k++) {
                    if(grille.getBateau(i,k)!=null){
                        return false; } }
                break;
            case (Bateau.DIRV):
                if (i + tailleBateau > 9) {
                    return false; }
                for(int m=i;m<i+tailleBateau;m++){
                    if(grille.getBateau(m,j)!=null){
                        return false; } }
                break; }
        return true;
    }


    /**
     * Méthode qui permet d'ajouter aléatoirement un bateau dans la grille
     * @param grille le tableau des bateaux
     * @param tailleBateau La taille du bateau à ajouter dans la grille
     */
    private void addBateau(Mer grille, int tailleBateau) {
        int iRandom;
        int jRandom;
        int direction;
        Random generator=new Random();
        do  {
            direction = generator.nextInt(2);
            iRandom = generator.nextInt(10);
            jRandom = generator.nextInt(10);
            }
        while (!(estValide(grille, iRandom, jRandom, direction, tailleBateau)));
        Bateau newBateau = new Bateau(iRandom,jRandom,tailleBateau,direction);
        this.listeBateau.add(newBateau);
        this.bateauxActuels.add(newBateau);
        grille.placerUnBateau(newBateau);
    }

    /**
     * Méthode qui permet de parcourir les différentes tailles
     * des bateaux déja défini dans la listeTailleBateau  puis les ajouter
     * dans la grille.
     * @param grille qui représente le tableau des bateaux
     */
    public void addBateaux(Mer grille)
    {
        for (int tailleBateau : listTailleBateau) {
            addBateau(grille, tailleBateau);
        }
    }
}