package games;
import players.GamePlayer;
import java.util.List;
import java.util.ArrayList;

/**
 * Classe permettant de jouer au TicTacToe. Le but du jeu est d'aligner ses trois symboles en hauteur, largeur ou en diagonale.
 */
public class TicTacToe extends AbstractGame{

	/**
	 * Grille du TicTacToe
	 */
	private GamePlayer [][] grid;

	/**
	 * Compteur de tour, qui permet de compter le nombre de tour. Sachant qu'il n'y a que 9 cases, il ne peut y avoir que 9 tours maximum
	 */
	protected int compteurTour;


	/**
	 * Construit une nouvelle instance de la classe TicTacToe
	 * @param player1 Le joueur 1
	 * @param player2 Le joueur 2
	 */
	public TicTacToe(GamePlayer player1, GamePlayer player2){
		super(player1,player2);

		/*On initialise la grille du TicTacToe, ça sera une grille de 3*3 de type player*/
		this.grid= new GamePlayer [3][3];

		this.compteurTour=0;

	}

	/*Méthode de AbstractGame*/
	@Override
	public boolean isOver(){


		if(this.compteurTour==9){

			return true;
		}

		if(this.ifWinner()){ //Méthode définie ligne 181

			return true;
		}

		return false;


	}


	/*Méthode dans AbstractGame*/
	@Override
	public GamePlayer getWinner(){

		if(!this.ifWinner()){ //Il aurait fallu chercher un moyen moins coûteux de savoir si il y a un gagnant (booléen)

			return null;
		}

		else if(this.currentPlayer==this.player1){

			return this.player2;
		}

		else{

			return this.player1;
		}
	}

	/*Méthode dans AbstractGame*/
	@Override
	public List<Integer> validMoves(){

		/*On crée la list moves, destinée à recevoir les coups valides*/
		List<Integer> moves= new ArrayList<>();

		/*Itération sur la grille*/

		for(int i=0;i<this.grid.length;i++) {
			for(int j=0;j<this.grid[0].length;j++) {

				/*Si il n'y a pas de joueur sur la case, il est possible de
				 jouer dessus, on l'ajoute à la liste
				 */

				if(this.grid[i][j]==null) {
					moves.add(caseToInt(i,j)); //Méthode définie ligne 450
				}
			}

		}

		return moves;

	}

	/*Méthode de AbstractGame*/
	@Override
	public String moveToString(List<Integer> moves) {

		return DrawGrid();//Méthode définie ligne 368

	}

	/*Méthode dans AbstractGame*/
	@Override
	public String situationToString(){

		/*On fait un builder afin de pouvoir rajouter des caractères à notre chaine*/
		StringBuilder situation_b=new StringBuilder("");

		for(int i=0;i<this.grid.length;i++){

			for(int j=0;j<this.grid[0].length;j++){

				/*Par défaut, on considère que la case est vide. Sinon, on mets le symbole du player correspondant*/
				String CaseK="   |";

				if(this.grid[i][j]!=null){

					CaseK=" "+this.symbolPlayer(this.grid[i][j])+" |"; //Méthode définie ligne 418
				}

				/*On ajoute la chaine à la grille*/
				situation_b.append(CaseK);

			}
		situation_b.append(System.lineSeparator());

		}

		//System.out.println(this.compteurTour);
		String situation_f=situation_b.toString();

		return situation_f;

	}

	/*Méthode dans AbstractGame*/
	@Override
	public void oneTurn(int move){

		this.moving(move); //Méthode définie ligne 403

		this.compteurTour=this.compteurTour+1;

	}

@Override
	public TicTacToe getCopy(){

		TicTacToe Copie=new TicTacToe(this.player1,this.player2);

		Copie.currentPlayer=this.currentPlayer;

		Copie.compteurTour=this.compteurTour;

		Copie.grid=getCopyGrid(); //Méthode féfinie ligne 483

		return Copie;




	}


	/**
	 * Méthode permettant de savoir si il existe un alignement gagnant dans la grille.
	 * Attention, méthode non optimisée. Il faudrait se souvenir du coup précédent pour parcourir la grille de façon intelligente.
	 * @return Un booléen. True si il existe un alignement gagnant. False sinon
	 */
	public boolean ifWinner(){

		/*On définit deux listes, coords qui contiendra les coordonnées de la case
		lue, et vector, qui contiendra les coordonnées du vecteur servant à se
		déplacer dans la grille.
		 */
		List<Integer> coords=new ArrayList<> ();
		List<Integer> vector=new ArrayList<> ();

		/*Pour plus d'efficacité dans le programme actuel, nous procédons ainsi
		 dans la recherche d'un alignement:
		 	Nous cherchons en premier si les diagonales sont des alignements gagnants,
		 en effet, si une diagonale permet au jeu de se terminer, nous économisons
		 deux boucles For.
		  	Nous parcourons ensuite les colonnes et les lignes.
		 */

		/*Nous commençons par la case (0,0)*/

		coords.add(0);
		coords.add(0);

		/*Puis par le vecteur (1,1), pour contrôler la diagonale principale*/
		vector.add(1);
		vector.add(1);

		/*Les tests seront les mêmes: nous évaluons d'abord si la case contient la
		 mention d'un joueur: si non, inutile de contrôler un potentiel alignement
		 gagant.
		 */
		if(this.grid[coords.get(0)][coords.get(1)]!=null){
			if(alignementJoueur(coords,vector)){ //Méthode définie ligne 296

				return true;
			}
		}

		/*Nous passons à la case de coordonnée (0,2): la méthode set permet de changer
		 la liste en une commande.
		 */

		coords.set(1,2);

		/*Nous changeons le vecteur pour avoir le vecteur (1,-1), ce qui correspond à
		 la deuxième diagonale.
		 */
		vector.set(1,-1);

		if(this.grid[coords.get(0)][coords.get(1)]!=null){
			if(alignementJoueur(coords,vector)){
				return true;
			}
		}

		/*Nous allons passer aux deux boucles For. Il est possible de ne faire qu'une
		 seule boucle, en jouant sur la parité de l'indice i afin de contrôler les
		 lignes et les colonnes en même temps. Néanmoins une version plus optimisée
		 de la méthode est attendue.
		 */

		/*Nous revenons à la case (0,0)*/
		coords.set(1,0);

		/*Nous allons commencer par contrôler les colonnes avec le vecteur (1,0)*/
		vector.set(1,0);


		for(int i=0;i<3;i++){
			if(this.grid[coords.get(0)][coords.get(1)]!=null){
				if(alignementJoueur(coords,vector)){

					return true;
				}
			}

			/*A chaque boucle, nous changeons la case afin de parcourir la colonne
			 suivante.
			 */
			coords.set(1,i+1);
		}

		/*Nous revenons à la case (0,0)*/
		coords.set(1,0);

		/*Nous allons enfin parcourir les lignes avec le vecteur (0,1)*/
		vector.set(0,0);
		vector.set(1,1);

		for(int i=0;i<3;i++){
			if(this.grid[coords.get(0)][coords.get(1)]!=null){

				if(alignementJoueur(coords,vector)){

					return true;
				}
			}

			coords.set(0,i+1);
		}

		return false;

	}




	/**
	 * Méthode permettant d'évaluer si un objet joueur est présent sur tout un
	 alignement, prenant en argument la case de départ et le vecteur représentant
	 l'alignement considéré
	 * @param coords Les coordonnées de la case considérée
	 * @param vector Le vecteur considéré pour évaluer l'alignement
	 * @return Un booléen. True si un joueur est bel et bien aligné selon le vecteur. False sinon.
	 */
	public boolean alignementJoueur(List<Integer> coords,List<Integer> vector){

		/*Nous évaluons en premier si la case de coordonnées coords et la case adjacente
		 formant le début de l'alignement sont de même valeur. Si non, inutile de
		 continuer à comparer
		 */
		if(this.grid[coords.get(0)][coords.get(1)]==this.grid[coords.get(0)+vector.get(0)][coords.get(1)+vector.get(1)]){

			/*Si oui, on compare alors la case de coordonnées coords avec la dernière
			 case de l'alignement*/

			if(this.grid[coords.get(0)][coords.get(1)]==this.grid[coords.get(0)+2*vector.get(0)][coords.get(1)+2*vector.get(1)]){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;

		}
	}



/*Méthode désormais inutilisée*/
/*	/**
	 * Méthode permettant de convertir un entier en une string représentant la case associée, accompagnée de l'entier
	 * @param move L'entier de la grille
	 * @return String représentant la case associée à l'entier*/
/*	public String oneMoveToString(int move){

		/*Définition des coordonnées
		int i,j;

		List<Integer> Case= new ArrayList<>();

		Case=intTocase(move);

		i=Case.get(0);
		j=Case.get(1);


		return "Case("+i+","+j+") avec le chiffre "+move;

	}

/*Ancien moveToString*/
	/*Méthode dans AbstractGame*/
	/*@Override
	public String moveToString(List<Integer> moves){

		StringBuilder builder=new StringBuilder("");

		for(int i=0;i<moves.size();i++){

			builder.append(oneMoveToString(moves.get(i)));
			builder.append(System.lineSeparator());


		}

		String Affichage=builder.toString();

		return Affichage;
	}*/

/**
Méthode permettant de dessiner une grille avec dans chaque case, le numéro qui correspond
à cette case
@return String qui représente la grille sur laquelle on joue*/
	private String DrawGrid(){

		StringBuilder builder=new StringBuilder("");

		builder.append(" _____ _____ _____ ");

		builder.append(System.lineSeparator());

		int e = 1;
		int f = 2;
		int g = 3;
		for(int i=0; i<3; i++){
			builder.append("|     |     |     |");
			builder.append(System.lineSeparator());
			builder.append("|  "+e+"  |  "+f+"  |  "+g+"  |");
			builder.append(System.lineSeparator());
			builder.append("|_____|_____|_____|");
			builder.append(System.lineSeparator());
			e = e+3;
			f = f+3;
			g = g+3;
		}

		String affichage=builder.toString();

		return affichage;
	}





	/**
	 * Méthode permettant d'enregistrer un coup dans la grille. On suppose que le coup est valide
	 * @param move Le coup à enregistrer dans la grille*/
	public void moving(int move ){

			List<Integer> Case= new ArrayList<>();

			Case=intTocase(move); //Méthode définie ligne 462

			this.grid[Case.get(0)][Case.get(1)]=this.currentPlayer;

	}


	/**
	 * Retourne le bon symbole pour chaque joueur. On suppose que le joueur 1 joue avec le symbole "+", et le deuxième avec "o"
	 * @param player Le joueur dont on veut obtenir le symbole
	 * @return Le symbole associé au joueur passé en argument*/
	public char symbolPlayer(GamePlayer player){

		if(player==this.player1){

			return '+';

		}
		else{

			return 'o';

		}

	}

	/**
	 * Méthode convertisant une case à l'entier correspondant.
	* On implémente simplement la suite Un=1+3*n car on remarque que la numérotation des cases est représentée par 1+ le numéro de la
	colonne
	* @param i Le numéro de la ligne
	* @param j Le numéro de la ligne
	* @return L'entier associé à la case
	*/
	/*Modèle utilisé
	     –––––––––––
		 |1 | 2 | 3|
		 –––––––––––
		 |4 | 5 | 6|
		 –––––––––––
		 |7 | 8 | 9|
		 –––––––––––
	 */
	public int caseToInt(int i,int j){

		return 1+3*i+j;

	}


	/**
	 * Méthode convertisant un entier à la case correspondante, selon le modèle ci dessus. Il suffit d'appliquer la suite "à l'envers" pour la
	 ligne et utiliser le modulo 3 pour la colonne
	 * @param move L'entier représentant une case
	 * @return Une liste contenant le numéro de la ligne et le numéro de la colonne de la case*/
	public List<Integer> intTocase(int move){

		int i;
		int j;

		i=(move-1)/3;
		j=(move-1)%3;

		List<Integer> Case= new ArrayList<>();

		Case.add(i);
		Case.add(j);

		return Case;

	}

/**
Méthode permettant de copier proprement la grille du TicTacToe
@return Une nouvelle grille qui est la grille de la situation actuelle
*/
	private GamePlayer [][] getCopyGrid(){

		GamePlayer [][] copie=new GamePlayer [3][3];

		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){

				copie[i][j]=this.grid[i][j];
			}

		}

		return copie;

	}


}
