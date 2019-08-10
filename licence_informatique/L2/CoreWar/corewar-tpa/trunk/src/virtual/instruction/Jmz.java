package virtual.instruction;

import errors.BadInstructionException;
import virtual.VirtualMachine;
import virtual.Warrior;

import java.util.ArrayList;

/**
 * Classe JMZ, instruction RedCode qui saute au champ B si le champ A est égal à zéro.
 */
public class Jmz extends Instruction{

    /**
     * Constructeur de copie
     * @param modifierA Le mode d'adressage du champ A
     * @param addressA L'adresse du champ A
     * @param modifierB Le mode d'adressage du champ B
     * @param addressB L'adresse du champ B
     * @param w Dernier Warrior a avoir eu accès à cette instruction
     */
    private Jmz(Integer modifierA,Integer addressA, Integer modifierB,Integer addressB,Warrior w){
        super(modifierA, addressA, modifierB, addressB,w);
    }

    /**
     * Constructeur standard
     * @param field1 Le champ 1
     * @param field2 Le champ 2
     */
    public Jmz(ArrayList<Integer> field1, ArrayList<Integer> field2){
        super(field1,field2);
    }

    /**
     * Méthode execution, qui exécute l'instruction
     * @param pointer Le pointeur du Warrior qui appel l'instruction
     * @param memory La machine virtuelle
     * @param w Le Warrior qui appel l'instruction
     * @return La nouvelle position de pointer
     */
    @Override
    public int execution(int pointer, VirtualMachine memory, Warrior w) {

        this.setLastAccessed(w);
		  // Pointeur temporaire qui permet de récupérer les données à l'adresse B
        int tempPointer=Math.floorMod(pointer +this.getAddressB(),VirtualMachine.TAILLE);

        if(this.modifierB==2){
				// Si l'adressage est indirect, modifie l'adresse du pointeur temporaire par cette nouvelle adresse.
            tempPointer=this.indirectToRelative(memory.getFromMemory(tempPointer),tempPointer);
        }

		if(this.getModifierB()==1){
			// Si l'adressage est direct, on modifie l'adresse du pointeur temporaire pour pointer sur l'virtual.instruction actuelle
			tempPointer=Math.floorMod(this.getAddressB(),VirtualMachine.TAILLE);
		}

        if (this.modifierA == 1) {
        		// L'adressage est immédiat, on effectue les opération directement dans l'instruction actuelle
            if (this.getAddressA() == 0) {
                return tempPointer;
            }
        }
    	  else{
    			 // Créer un deuxième pointeur temporaire pour l'adresse A qui sera utilisé si l'adressage est relatif
             int tempPointer2 = Math.floorMod(pointer + this.getAddressA(),VirtualMachine.TAILLE);

             if(this.modifierA==2){

                 tempPointer2=this.indirectToRelative(memory.getFromMemory(tempPointer2),tempPointer2);

             }
             if ((memory.getFromMemory(tempPointer2).getAddressA() == 0)) {
                 return tempPointer;
             }
         }
			
		  // Si rien n'est égal à zéro, on saute à l'instruction suivante
        return Math.floorMod(pointer + 1, VirtualMachine.TAILLE);
    }


    /**
     * Méthode isValidConfig, qui vérifie si l'instruction est correctement écrite
     * @throws BadInstructionException
     */
    @Override
    public void isValidConfig() throws BadInstructionException {}

    /**
     * Méthode clone, qui clone l'instruction
     * @return Une nouvelle instruction, identique à celle sur laquelle la méthode a été appellée
     */
    @Override
    public Instruction clone(){
        return new Jmz(this.modifierA,this.getAddressA(),this.modifierB,this.getAddressB(),this.lastAccessed);
    }
}