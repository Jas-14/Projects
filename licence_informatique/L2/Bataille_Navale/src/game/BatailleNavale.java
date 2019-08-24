package game;

import game.player.Player;

import java.util.List;

/**
 * Classe BatailleNavale, qui orchestre le jeu en
 * permettant de jouer les coups
 */
public class BatailleNavale{
	
	/**
	 * Attribut grilleJoueurH, qui est l'instance de type Mer
	 * pour le joueur Humain
	 */
	private Mer grilleJoueurH;
	
	/**
	 * Attribut grilleJoueurAlea, qui est l'instance de type Mer
	 * pour le joueur IA
	 */
	private Mer grilleJoueurAlea;
	
	/**
	 * Attribut grilleActu, qui est la grille du joueur
	 * qui va jouer son coup
	 */
	private Mer grilleActu;
	
	/**
	 * Attribut joueurH, qui est le joueur Humain
	 */
	private Player joueurH;
	
	/**
	 * Attribut joueurAlea, qui est le joueur IA
	 */
	private Player joueurAlea;
	
	/**
	 * Attribut joueurActu, qui est le joueur
	 * qui va jouer son coup
	 */
	private Player joueurActu;
	
	/**
	 * Constructeur de la classe
	 * @param joueurH Le joueur humain
	 * @param joueurAlea Le joueur Ia
	 */

	public BatailleNavale(Player joueurH,Player joueurAlea){

		this.joueurH=joueurH;
		this.joueurAlea=joueurAlea;

		this.grilleJoueurH=new Mer(this.joueurH,this.joueurAlea);
		this.grilleJoueurAlea=new Mer(this.joueurAlea,this.joueurH);

		this.joueurActu=this.joueurH;
		this.grilleActu=this.grilleJoueurAlea;
		
	}

	/**
	 * Méthode qui permet d'obtenir la grille du joueur Humain
	 * @return La grille du joueur Humain
	 */
	public Mer getGrilleJoueurH(){

		return this.grilleJoueurH;
	}

	/**
	 * Méthode qui permet d'obtenir la grille du joueur IA
	 * @return La grille du joueur IA
	 */
	public Mer getGrilleJoueurAlea(){

		return this.grilleJoueurAlea;
	}
	
	/**
	 * Méthode qui détermine si la partie est finie
	 * @return Un booléen qui est True si une liste est vide, False sinon
	 */
	public boolean fin(){
		
		return (this.joueurAlea.getBateauxActuels().isEmpty() || this.joueurH.getBateauxActuels().isEmpty());
		
	}
	
	/**
	 * Méthode qui change de joueur actuel
	 */
	private void changeJoueur(){
		if(this.joueurActu==this.joueurH){
			this.joueurActu=this.joueurAlea;
			this.grilleActu=this.grilleJoueurH;
		}
		else{
			this.joueurActu=this.joueurH;
			this.grilleActu=this.grilleJoueurAlea;
		}
	}
	
	/**
	 * Méthode qui effectue des tours de jeu
	 * @return Le nom du vainqueur
	 */
	public String jeu(){
		this.initGame();

		System.out.println("Bienvenue dans la bataille navale!"
		+"Il y a "+Player.listTailleBateau.size()+" bateaux à trouver.");
		while(!this.fin()){
			this.jouer();
			this.changeJoueur();
		}

		return "Le gagnant est "+this.gagnant().getName()+" !";
	}

	/**
	 * Méthode qui retourne le vainqueur de la partie
	 * @return Le Player qui ne possède pas de liste de bateaux vide
	 */
	public Player gagnant(){

 		if (this.joueurH.getBateauxActuels().isEmpty() )
		{
			return joueurAlea;
		}
		else
			return  joueurH;
	}
	
	/**
	 * Méthode qui effectue un coup
 	 */
	public void jouer(){

		System.out.println("C'est au tour de "+this.joueurActu.getName()+" de jouer");
		List<Integer> coup=this.joueurActu.jouerCoup();
		this.joueurActu.getCoupsJouer().add(coup);
		if(this.grilleActu.envoyerCoup(coup)){
			Bateau bateau=this.grilleActu.getBateau(coup.get(0),coup.get(1));
			bateau.estTouche();
			if(bateau.estCoule()){
				System.out.println("Un bateau de "+this.grilleActu.getProprietaire().getName()+" a coulé! "+ "Il lui en reste "+(this.grilleActu.getProprietaire().getBateauxActuels().size()-1));
				this.grilleActu.getProprietaire().getBateauxActuels().remove(bateau);
				this.grilleActu.getProprietaire().getBateauxCoules().add(bateau);
			}
		}
		System.out.println("Etat du jeu");
		System.out.println(this.grilleActu);
 
	}

	/**
	 * Méthode qui place les bateaux dans les grilles des joueurs
	 */
	public void initGame(){

		this.joueurH.addBateaux(this.grilleJoueurH);
		this.joueurAlea.addBateaux(this.grilleJoueurAlea);

	}
}