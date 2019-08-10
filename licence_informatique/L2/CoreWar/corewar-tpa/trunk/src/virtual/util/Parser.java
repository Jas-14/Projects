package virtual.util;

import virtual.instruction.*;

import java.util.*;

import errors.*; //On peut se permettre de tout importer car on utilise tout les types d'exception.

/**
 * Classe Parser implémentant la possibilité de convertir une ligne de type String en un objet de type Instruction, en gérant les erreurs pouvant survenir pendant l'opération.
 * Pour effectuer la transformation, nous divisons la ligne en trois champs
 * sous le modèle suivant: OP A B
 * Ensuite, nous évaluons l'opérateur et nous codons les champs avec
 * des ArrayList
 * Enfin, nous construisons l'objet de type Instruction et nous évaluons si
 * celui ci est exécutable au sein de la machine virtuelle.
 *
 * Le codage des champs avec des ArrayList permet de simplifier le
 * traitement et de rendre le code plus compréhensible. En terme de
 * performance, il n'y a presque aucun changement entre utiliser des
 * ArrayList ou utiliser des types primitifs. (Néanmoins le temps est plus
 * stable avec des ArrayList)
 */

public class Parser {

	/**
	 * Constante contenant les Mnemonics à 2 Fields du langage RedCode
	 * OP A B
	 */
	private static final Set<String> MNE2 = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("MOV", "JMZ","ADD","SUB","DJZ","CMP")));

	/**
	 * Constante contenant les Mnemonics à 1 Field du langage RedCode
	 * OP A
	 */

	private static final Set<String> MNE1= Collections.unmodifiableSet(new HashSet<>(Arrays.asList("DAT","JMP")));

	/**
	 * Constante HashMap pour associer un modifieur à un nombre
     * Rappel:
     * 0 : adresse relative (par rapport à l'instruction)
     * 1 : adressage immédiat, donc un entier
     * 2 : adressage indirect, par rapport au champ A pointé par cette adresse
	 */

	private static final HashMap<Character, Integer> MF2Int=new HashMap<Character, Integer>(){{

		put('#',1);
		put('@',2);

	}
	};



	/**
	 * Méthode pour vérifier si, lorsqu'un mode d'adressage
	 * est détecté, il existe. Si oui, on retourne son codage
	 * en entier
	 *
	 * @param Modifier Le modifieur à vérifier
     * @return La valeur du modifieur selon la hashmap codé
	 * @throws BadModifierException Si le mode d'adressage n'est pas reconnu
	 */
	public static Integer isGoodModifierField(Character Modifier) throws BadModifierException{

	    /*Si la hashmap ne peut pas faire correspondre à un entier le caractère Modifier, int_value va valoir null*/
		Integer int_value=MF2Int.get(Modifier);

		if(int_value==null){

			throw new BadModifierException();
		}


		return int_value;

	}


	/**
	 * Méthode pour vérifier que le champ est correctement écrit.
     * Si oui, on retourne une ArrayList qui va représenter le champ:
	 *
     * [codage du modifieur de champ, valeur du champ]
     *
	 * @param Field Le champ à vérifier
     * @return Une ArrayList codant le champ
	 * @throws BadModifierException Si un mode d'adressage n'est pas reconnu
	 * @throws FieldNotNumberException Si l'adresse dans le champ n'est pas un nombre
	 */
	public static ArrayList<Integer> isGoodField(String Field) throws BadModifierException,FieldNotNumberException{

		ArrayList<Integer> resultField=new ArrayList<>();

        /*On initialise le modifieur à 0, car c'est la valeur par "défaut" que nous avons choisi. On évalue directement si le champ est accompagné d'un modifieur. Si non, cela veut dire que nous sommes en adressage relatif, donc le modifieur est bel et bien égal à 0*/

		Integer modifier=0;
		if(Field.length()>1 && Field.charAt(0)!='-' && (!(Character.isDigit(Field.charAt(0))))){

			/*Ne pas utiliser charAt. Bug ici */
			modifier=isGoodModifierField(Field.charAt(0));


			/*On enlève le modifieur du field pour effectuer le parseInt*/
			Field=Field.substring(1,Field.length());

		}

		//On ajoute le modifieur à l'ArrayList

		resultField.add(modifier);

		/*try parseInt. Si il réussit, son résulat sera ajouté à
		l'ArrayList
		 */
		try {
			resultField.add(Integer.parseInt(Field));
		}catch (NumberFormatException e){

			throw new FieldNotNumberException();
		}

		return resultField;

	}


	/**
	 * Méthode pour vérifier que l'opérateur Com est une
	 * Mnemonics du RedCode. Ici, on évalue uniquement les
	 * Mnemonics à deux fields. (Voir méthode
	 * stringToInstruction)
	 *
	 * @param Com Résultat du split correspondant à la
	 * position de l'Opérateur
	 * @throws BadOperatorException Si l'opérateur n'est pas reconnu
	 */
	public static void isMnemonics(String Com) throws BadOperatorException {

		if(!MNE2.contains(Com)){

			throw new BadOperatorException();
		}

	}

	/**
	 * Méthode qui permet un traitement spécial pour les instructions à un seul champ, comme DAT ou JNZ
	 * @param line La ligne à convertir
     * @return L'instruction représentée par la ligne, si correcte
	 * @throws BadInstructionException Si l'instruction générée n'est pas exécutable
	 */
	public static Instruction oneFieldToInstruction(String [] line) throws BadInstructionException{

		ArrayList<Integer> field=null;

		//try pour évaluer le champ
		try{

			field=Parser.isGoodField(line[1]);

		}catch (CorewarException e){

			e.printStackTrace();
			System.exit(-1);
		}

		//On sépare l'opérateur de la ligne
        String op=line[0];

		Instruction i=null;

		switch (op){

            case "DAT":
                i=new Dat(field);
                break;
            case "JMP":
                i=new Jmp(field);
                break;
            default:
                System.err.println("Une erreur interne est survenue au moment de construire l'virtual.instruction"+System.lineSeparator()+"Le programme va s'arrêter");
                System.exit(-1);
        }

        //try pour évaluer si l'instruction est exécutable

		i.isValidConfig();


		return i;

	}

	/**
	 * Méthode permettant de convertir une ligne en instruction
	 *
	 * @param line La ligne à convertir
	 * @return Un objet de classe Instruction qui correspondra à la ligne lue, si correct
	 * @throws CorewarException Toute erreur susceptible d'être rencontrée
	 */
	public static Instruction stringToInstruction(String line) throws CorewarException{


		//resultP de type String[3] est un tableau contenant le résultat du split.
		/*La syntaxe du RedCode est la suivante: COM A B, sauf pour certaines instructions où elle est de COM A*/

		String[] resultP = line.split(" ", 3);

		/*
		On sauvegarde d'abord l'opérateur dans une variable
		locale, si la ligne n'est pas vide
		 */

		String op=null;
		try{
			op=resultP[0];
		}catch (ArrayIndexOutOfBoundsException e){

			throw new SyntaxErrorException();


		}


		/*On opère un tri dès maintenant entre les opérateurs à un field et ceux à 2 fields*/
		if((MNE1.contains(op))){
			if(resultP.length>2) {
			/*Si ils sont déjà incorrects, autant ne pas continuer*/
				throw new OneFieldException();

			}
			/*Sinon on retourne le tableau à une méthode auxiliaire prévue pour ces opérateurs*/
			return Parser.oneFieldToInstruction(resultP);

		}

		if(!(resultP.length==3)){

			/*Erreur peu précise*/
			throw new SyntaxErrorException();


		}

		/*On sauvegarde les champs dans des variables locales*/
		String fieldA = resultP[1];
		String fieldB = resultP[2];

		ArrayList<Integer> resultField1=null;
		ArrayList<Integer> resultField2=null;

		/*try pour évaluer les champs. Si ils sont bons, on
		récupère leur codage dans deux ArrayList
		 */

		try {
			Parser.isMnemonics(op);


			resultField1 = Parser.isGoodField(fieldA);
			resultField2 = Parser.isGoodField(fieldB);
		}catch (CorewarException e){
			throw e;
		}

		Instruction i=null;


		switch (op){

		    case "MOV":
		        i=new Mov(resultField1,resultField2);
		        break;
            case "JMZ":
                i=new Jmz(resultField1,resultField2);
                break;
            case "ADD":
                i=new Add(resultField1,resultField2);
                break;
            case "CMP":
                i=new Cmp(resultField1,resultField2);
                break;
            case "DJZ":
                i=new Djz(resultField1,resultField2);
                break;
            case "SUB":
                i=new Sub(resultField1,resultField2);
                break;
            default: //Normalement on ne tombe pas dessus
                System.err.println("Une erreur interne est survenue au moment de construire l'virtual.instruction"+System.lineSeparator()+"Le programme va s'arrêter");
                System.exit(-1);
        }

        //try pour évaluer si l'virtual.instruction est exécutable

		i.isValidConfig();


		return i;

	}

	/**
	 * Méthode pour convertir un programme en une liste d'instructions
	 * @param prgrm Le programme représenté comme une liste de String
	 * @return Une liste d'instructions représentant le programme
	 * @throws CorewarException Toute erreur susceptible d'être rencontrée
	 */
	public static List<Instruction> prgrmToListInstruction(List<String> prgrm) throws CorewarException{

		List<Instruction> instructions=new ArrayList<Instruction>(prgrm.size());

		for (String line:prgrm) {

			try {
				instructions.add(Parser.stringToInstruction(line));
			}catch (CorewarException e){

				throw e;

			}


		}

		return instructions;

	}

}
