package main;
import players.*; //On utilise l'entièreté du package players
import games.*;
import java.util.Scanner;

/**
 * Classe Main pour lancer l'application
 *
 */
public class DemoAppli {

	/**
	 * Méthode main de DemoAppli
	 * @param args est sous la forme suivante Jeu TypeJ1 TypeJ2 avec quelques subtilités.
	 * Si le jeu est Nim, alors immédiatement après Jeu suivront deux nombres n et k, n étant le nombre d'allumettes dans le tas et k le nombre maximum d'allumette que l'on peut retirer en un tour
	 * Si l'un des joueurs est une personne, alors immédiatement après TypeJ suivra le nom de cette personne
     * Exemple: pour lancer le jeu de Nim de 20 allumettes, 3 que l'on peut enlever au maximum, avec un Humain 
     * et une IA, il faut écrire Nim 20 3 Humain Nom IA
	 */
	public static void main(String[] args) {
		
		String jeu=""; //Variable recevant le nom du jeu
		int indice=0; //Variable nous permettant de manipuler le tableau args comme nous le voulons
		
		String explication=""+System.lineSeparator()+"Pour jouer sur l'application, vous devez exécuter l'application avec les"
				+ " arguments suivants: <jeu> <TypeJ1> <TypeJ2>."+System.lineSeparator()+"Si votre jeu est Nim, vous"
				+ " devez indiquer les paramètres n et k, n étant le nombre d'allumettes dans le tas"+System.lineSeparator()
				+ "et k le nombre d'allumettes maximum que l'on peut retirer par tour"+System.lineSeparator()+"Si l'un des joueurs"
				+ " est un humain, entrez \"Humain\" suivi de son nom."+System.lineSeparator()+"Si l'un des joueurs"
				+ " est un joueur aléatoire,entrez \"Random\"+"+System.lineSeparator()+"Si l'un des joueurs est une IA"
				+ " ,entrez \"IA\""+System.lineSeparator()+"Quelques exemples:"+System.lineSeparator()+"Si vous voulez lancer le"
				+ " jeu de Nim avec 20 allumettes, 3 que l'on peut enlever au maximum, avec un Humain et une IA, il faut lancer le"
				+ " l'application suivie de \"Nim 20 3 Humain Nom IA\""+System.lineSeparator()+"Si vous voulez lancer le jeu TicTacToe"
				+ " avec deux Humains, il faut lancer l'application suivie de \"TicTacToe Humain Nom Humain Nom\"";
		/*Ceci est un petit paragraphe qui apparaitra à chaque erreur afin de donner un petit mode d'emploi sur comment
		 lancer l'application
		 */
		
		//Scanner ouvert en prévision d'un ou de deux joueurs humains qui en auraient besoin
		Scanner scan=new Scanner(System.in);
		
		
		/*Bloc try pour tester si quelque chose est renseigné comme argument*/
		try {
			
			jeu=args[indice];
			
		} catch(IndexOutOfBoundsException s) {
			
			System.out.println("Erreur "+explication);
			
			System.exit(1); //Nous effectuons des exit afin de sortir du programme immédiatement
			
		}
		
		//Nous préparons les variables n et k au cas où Nim serait le jeu choisi
		int n=-1;
		int k=-1;
	
		
		//Test si le jeu est Nim, dans ce cas, le traitement est particulier
		if(jeu.equals("Nim")) {
			
			indice++; //On avance dans le tableau 
			
			//Préparation de la chaine représentant l'entier n et la chaine représentant l'entier k
			String chaineN="";
			String chaineK="";
			
			//Bloc try pour tester si il existe un deuxième argument sur la ligne de commande
			try {
			    chaineN=args[indice];
			
			}catch(IndexOutOfBoundsException s) {
				
				System.out.println("Manque d'argument "+explication);
				System.exit(1);
				
			}
			
			
			indice++; //On avance dans le tableau
			
			//Bloc try pour tester si il existe un troisième argument sur la ligne de commande
			try {
				
				chaineK=args[indice];	
			}catch(IndexOutOfBoundsException s) {
				
				System.out.println("Manque d'argument "+explication);
				System.exit(1);
				
			}
			
			indice++;
			
			//Bloc pour détecter si la chaîne est bien un nombre entier, comme attendu
			try {
				n=Integer.parseInt(chaineN);
			}catch(NumberFormatException s) {
				
				System.out.println("Erreur type d'argument "+explication);
				System.exit(1);
			}
			
			//Idem
			try {
				k=Integer.parseInt(chaineK);
				
			}catch(NumberFormatException s) {
				
				System.out.println("Erreur type d'argument "+explication);
				System.exit(1);
			}
			
		}
		
		//Si le jeu est TicTacToe, on se contente d'avancer dans le tableau
		else if(jeu.equals("TicTacToe")) {
			
			indice++;
			
		}
		else {
			
			System.out.println("Erreur lors du renseignement du jeu "+explication);
			System.exit(1);
			
		}
			
		
		//Création des GamePlayer
		
		
		//On suppose que le premier joueur entré est le joueur 1. On prépare la chaine pour connaître le type de joueur
		String TypeJ1="";
		
		
		//Bloc try pour savoir si cet argument existe
		try {
				
			TypeJ1=args[indice];
		}catch(IndexOutOfBoundsException s) {
				
			System.out.println("Erreur, manque d'argument "+explication);
			System.exit(1);
		}
		
		/*Java n'accepte pas de laisser une variable non initialisée durant tout le programme et ce même si on est sûr
		 qu'elle sera initialisée. Nous sommes obligés de mettre null
		 */
		
		GamePlayer J1=null;
			
		if(TypeJ1.equals("Humain")) {
			
			//On avance dans le tableau car on veut récupérer le nom de la personne
			indice++;
			
			String nom="";
			try {
				 nom=args[indice];
			}catch(IndexOutOfBoundsException s) {
					
				System.out.println("Erreur, manque d'argument "+explication);
				System.exit(1);
			}
			
			
				
			J1= new Human(nom,scan);
				
				
				
		} else if(TypeJ1.equals("Random")){
				
			J1=new RandomPlayer();
			
		

		} else if(TypeJ1.equals("IA")){
			  
			  J1=new MinMaxPlayer();
		  
		
		
		}else { /*Nous n'acceptons pas n'importe quelle chaine de caractère, afin de pouvoir rajouter facilement des 
				  types de joueur*/
				
			System.out.println("Erreur, il manque les joueurs "+explication);
			System.exit(1);
		}
		
		//On avance dans le tableau pour aller sur le prochain joueur
		indice++;
			
		String TypeJ2="";
			
		try {
				
			TypeJ2=args[indice];
		}catch(IndexOutOfBoundsException s) {
				
			System.out.println("Erreur, manque d'argument "+explication);
			System.exit(1);
		}
		    
		GamePlayer J2=null;
		    
        if(TypeJ2.equals("Humain")) {
				
			indice++;
				
			String nom="";
			try {
				 nom=args[indice];
			}catch(IndexOutOfBoundsException s) {
					
				System.out.println("Erreur, manque d'argument "+explication);
				System.exit(1);
			}
				
			J2= new Human(nom,scan);
				
				
				
		} else if(TypeJ2.equals("Random")){
				
			J2=new RandomPlayer();
				
		}else if(TypeJ2.equals("IA")){
			  
			J2=new MinMaxPlayer();
		  
		
		}else {
				
			System.out.println("Erreur, il manque un joueur "+explication);
			System.exit(1);
		}
        
        
        //Toutes les variables sont définies, nous avons exclus tout les cas d'erreur. Nous pouvons lancer le jeu
        
        AbstractGame game=null;
			
		if(jeu.equals("Nim")) {
			
			game=new Nim(n,k,J1,J2);
			
		}else {
			
			game=new TicTacToe(J1,J2);
		}
		
		Orchestrator boucle=new Orchestrator();
		
		boucle.playGame(game);
		
		scan.close(); //Fermeture du scanner ouvert au tout début de l'exécution du main
		

	}

}
