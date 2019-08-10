package games;
import players.GamePlayer;
import java.util.List;



/**
 * Classe abstraite afin de regrouper les jeux Nim et TicTacToe, ayant la même boucle de jeu*/

public abstract class AbstractGame{

	/**Le joueur 1*/
	protected GamePlayer player1;

	/**Le joueur 2*/
	protected GamePlayer player2;

	/**Le joueur actuel, initialisé au joueur 1*/
	protected GamePlayer currentPlayer;

	/**
	 * Construit une nouvelle instance de AbstractGame
	 * @param one Le joueur 1
	 * @param two Le joueur 2
	 */
	public AbstractGame(GamePlayer one, GamePlayer two){


		this.player1=one;
		this.player2=two;

		this.currentPlayer=this.player1;


	}


	/**
	 * Méthode abstraite permettant de jouer un coup
	 * @param move Le coup du joueur*/
	public abstract void oneTurn(int move);

	/**
	 * Méthode permettant de jouer un coup et de changer de joueur courant
	 * @param move Le coup du joueur*/

	public void oneTurnComplete(int move){

		if(validMoves().contains(move)){

			oneTurn(move);

			if(this.currentPlayer==this.player1){

				this.currentPlayer=this.player2;

			}
			else{

				this.currentPlayer=this.player1;

			}
		}
		else{

			System.out.println("Coup invalide");

		}
	}

	/**
	 * Accesseur joueur courant
	 * @return Le joueur courant*/
	public GamePlayer getCurrentPlayer(){

		return this.currentPlayer;

	}

	/**
	 * Méthode abstraite permettant de construire la liste des coups valides pour chaque jeu
	 * @return La liste des coups valides*/
	public abstract List<Integer> validMoves();


	/**
	 * Méthode abstraite retournant sous forme de chaîne la situation du jeu à l'instant t
	 * @return chaîne de caractère décrivant le jeu*/
	public abstract String situationToString();

	/**
	 * Méthode abstraite pour indiquer au joueur les coups disponibles
	 * @param moves Liste des coups valides
	 * @return chaîne de caractère donnant tout les coups possibles au joueur*/
	public abstract String moveToString(List<Integer> moves);

	/**
	 * Méthode abstraite qui évalue si la partie est finie
	 * @return Un booléen. True si la partie est finie. False sinon*/
	public abstract boolean isOver();

	/**
	 * Méthode abstraite retournant le joueur gagnant
	 * @return joueur gagnant*/
	public abstract GamePlayer getWinner();

	/**
	Méthode qui permet d'obtenir la copie distincte de la situation du jeu
	@return Une copie de l'instance actuelle*/
	public abstract AbstractGame getCopy();


}
