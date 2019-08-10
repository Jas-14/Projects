package players;
import games.AbstractGame;
import java.util.List;

/**
 * Classe MinMaxPlayer, un joueur implémentant l'algorithme MinMax pour jouer aux jeux de notre application.
 * Ce type de joueur est normalement imbattable contre un joueur humain. Néanmoins, au vu des problèmes d'optimisation
 * , il est déconseillé de faire jouer un MinMaxPlayer sur une partie de Nim contenant plus de 40 allumettes.
 */
public class MinMaxPlayer extends AbstractPlayer implements GamePlayer{
	
	/*Méthode de GamePlayer*/
	public int chooseMove(AbstractGame game){
		
		this.checkSituation(game);

		int bestVal=-2; //bestVal s'initialise à -2 car il n'y a pas de valeur de la méthode evaluer qui sera plus petite que -2
		int bestMove=0; //L'initialisation de bestMove n'a aucun impact sur la suite de l'exécution
		
		List<Integer> moves=game.validMoves(); //On calcule les coups possible pour cette situation
		
		for(int c:moves){ //On itère sur tout les mouvements possibles
			
			AbstractGame Situationprime=game.getCopy();
			/*On copie la situation actuelle afin de pouvoir faire des actions dessus sans interférer avec la 
			 situation actuelle
			 */
			
			//On exécute le coup sur la situation copiée
			Situationprime.oneTurnComplete(c);
			
			//On appelle une première fois la méthode evaluer afin de récupérer la valeur de cette situation
			/*On récupère l'opposé de la valeur retournée par evaluer. En effet, si elle permet au joueur adverse
			 * de gagner, alors elle permet au joueur courant de perdre, et vice versa.
			 */
			
			int vprime=-this.evaluer(Situationprime);
			
			if(vprime>bestVal){ 
			//Si c'est une situation meilleure que celle mémorisée dans bestVal, on la remplace
				
				bestVal=vprime;
				bestMove=c;
			}
		}
		//On retourne le meilleur coup évalué
		return bestMove;
		
	}
	

		
		
		
	
	/**
	 *Méthode evaluer permettant d'associer un réel, -1, 0 ou 1 à une situation. C'est l'implémentation de
	 *l'algorithme MinMax. Une situation valant -1 veut dire que l'IA est sûre de perdre dans cette situation. 
	 *Une situation valant 0 veut dire que l'IA est sûre de faire une partie nulle avec l'autre joueur dans cette
	 *situation. Une situation valant 1 veut dire que l'IA est sûre de gagner dans cette situation.
	 *@param Situation La situation que l'on évalue
	 *@return La valeur de la situation: -1, 0 ou 1
	 */
	public int evaluer(AbstractGame Situation){
		
		if(Situation.isOver()){ 
		/*Si la situation est une partie finie, on évalue le gagnant. Si c'est le currentPlayer qui gagne, la
		 * situation vaut 1, si c'est partie nulle, 0 et si le currentPlayer perds, la situation vaut -1
		 */
			
			if(Situation.getWinner()==Situation.getCurrentPlayer()){
				
				return 1;
			}
			
			else if(Situation.getWinner()==null){
				
				return 0;
			}
			else{
				
				return -1;
			}
		}
		
		else{ //Sinon, on doit évaluer les autres situations pouvant découler de la situation actuelle
			
		int res=-2;
		
		List<Integer> moves=Situation.validMoves();
		//On calcule la liste des coups possible et on itère dessus
		for(int c:moves){
			
			/*On copie la situation courante afin de pouvoir exécuter le coup et on appelle la méthode evaluer
			 * récursivement sur la situation copiée
			 */
			AbstractGame Situationprime=Situation.getCopy();
			
			Situationprime.oneTurnComplete(c);
			
			int vprime=-this.evaluer(Situationprime);
			
			if(res<vprime){ //Si la situation est meilleure que celle mémorisée, on mets à jour
				
				res=vprime;
			}
			
		}
		
		return res;
		
		}
		
	}
	
	
	
	
}
			
			
			
		
		
			