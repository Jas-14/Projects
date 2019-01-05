package games;
import players.GamePlayer;
import java.util.List;
import java.util.ArrayList;

/**
 * Classe Nim représentant le jeu de Nim. Le jeu nécessite un paquet d'allumettes de taille n. Le but du jeu est de ne pas être
 * la personne à retirer la dernière allumette, sachant qu'à chaque tour, on ne peut pas enlever plus de k allumettes. k et n sont des
 * nombres entiers.*/

public class Nim extends AbstractGame{

	/**
	 * Taille initiale du paquet d'allumettes*/
	private int originalSize;

	/**
	 * Nombre d'allumettes maximum qu'il est possible de retirer par tour*/

	private int maxMatches;

	/**
	 * Nombre d'allumettes restantes*/

	private int currentNbMatches;

	/**
	 * Construit une nouvelle instance de la classe Nim
	 * @param size La taille du paquet d'allumettes
	 * @param maxMatches Le nombre d'allumettes maximum qu'il est possible de retirer
	 * @param player1 Le joueur 1
	 * @param player2 Le joueur 2
	 */
	public Nim(int size,int maxMatches, GamePlayer player1, GamePlayer player2){
		super(player1,player2);

		this.originalSize=size;

		this.maxMatches=maxMatches;

		/*Au début, la taile courante du tas est la taille initiale*/
		this.currentNbMatches=size;

	}

	/**
	 * Accesseur du nombre d'allumettes initial A VOIR SI ON GARDE
	 * @return Le nombre d'allumettes initial*/
	public int getInitialNbMatches(){

		return this.originalSize;

	}

	/**
	 * Accesseur du nombre d'allumettes actuel
	 * @return Le nombre d'allumettes actuel*/
	public int getNbMatches(){

		return currentNbMatches;

	}

	/**
	 * Méthode permettant au joueur courant de retirer des allumettes du tas
	 * @param nbMatches Le nombre d'allumettes que le joueur veut retirer*/
	public void pullMatches(int nbMatches){

		this.currentNbMatches=this.currentNbMatches-nbMatches;
	}


	/*Méthode dans AbstractGame*/
	@Override
	public List<Integer> validMoves(){

		List<Integer> allMoves= new ArrayList<>();

		int max;

		/*Les coups autorisés sont ceux allant de 1 à maxMatches ou 1 à currentNbMatches si currentNbMatches<maxMatches*/

		if(this.currentNbMatches>=this.maxMatches){

			max=this.maxMatches;
		}
		else{

			max=this.currentNbMatches;

		}


		for(int i=1;i<max+1;i++){

			allMoves.add(i);
		}

		return allMoves;

	}


	/*Méthode dans AbstractGame*/
	@Override
	public boolean isOver(){
		return this.currentNbMatches==0;
	}

	/*Méthode dans AbstractGame*/
	@Override
	public GamePlayer getWinner(){

		return this.currentPlayer;
	}


	/*Méthode dans AbstractGame*/
	/*A voir si on modifie pour donner en plus un affichage graphique*/
	@Override
	public String situationToString(){

		StringBuilder builder=new StringBuilder("");

		int k=15;

		if(this.currentNbMatches<k) {

			k=this.currentNbMatches;
		}

		for(int i=0; i<k; i++){
				builder.append("c=======");
				builder.append(System.lineSeparator());
			}
		builder.append(System.lineSeparator());
		builder.append("Il reste "+this.currentNbMatches+ " allumettes");

		String affichage=builder.toString();

		return affichage;

	}


	/*Méthode dans AbstractGame*/
	@Override
	public void oneTurn(int move){

		pullMatches(move);


	}

	/*Méthode dans AbstractGame*/
	@Override
	public String moveToString(List<Integer> moves){

		StringBuilder builder=new StringBuilder("");

		for(int i=0;i<moves.size();i++){

			builder.append(moves.get(i)+" ");

		}

		String Affichage=builder.toString();

		return Affichage;

	}


	public Nim getCopy(){

		/*Une recopie de l'instance de Nim acutelle peut se contenter de commencer à jouer avec un
		tas d'allumettes qui correspond au nombre d'allumettes actuel*/
		Nim Copie=new Nim(this.currentNbMatches,this.maxMatches,this.player1,this.player2);

		Copie.currentPlayer=this.currentPlayer;

		return Copie;

	}





}
