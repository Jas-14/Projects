package virtual.instruction;

import errors.BadInstructionException;
import virtual.VirtualMachine;
import virtual.Warrior;

import java.util.ArrayList;

/**
 * Classe DJZ, instruction Redcode qui décrémente le contenu du champ A puis compare la valeur à zéro.
 * Si la valeur est égale à zéro, on saute à l'instruction B, sinon on passe à l'instruction suivante.
 */

public class Djz extends Instruction{

    /**
     * Constructeur en cas de copie
     * @param modifierA Mode d'adressage du champ A
     * @param addressA Adresse du champ A
     * @param modifierB Mode d'adressage du champ B
     * @param addressB Adresse du champ B
     * @param w Dernier Warrior a avoir eu accès à cette instruction
     */
    private Djz(Integer modifierA, Integer addressA, Integer modifierB, Integer addressB,Warrior w){
        super(modifierA, addressA, modifierB, addressB,w);
    }

    /**
     * Constructeur standard
     * @param field1 Le champ 1
     * @param field2 Le champ 2
     */
    public Djz(ArrayList<Integer> field1, ArrayList<Integer> field2){
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
    public int execution(int pointer, VirtualMachine memory, Warrior w){

        this.setLastAccessed(w);

		  // Pointeur temporaire qui permet de récupérer les données à l'adresse B
        int tempPointer=Math.floorMod(pointer+this.getAddressB(),VirtualMachine.TAILLE);

        if(this.getModifierB()==2){
		  // Si l'adressage est indirect, modifie l'adresse du pointeur temporaire par cette nouvelle adresse.
            tempPointer=this.indirectToRelative(memory.getFromMemory(tempPointer),tempPointer);

        }
		
		if(this.getModifierB()==1){
			// Si l'adressage est direct, on modifie l'adresse du pointeur temporaire pour pointer sur l'instruction actuelle
			tempPointer=Math.floorMod(this.getAddressB(),VirtualMachine.TAILLE);
		}
		  
		  // Variable qui servira pour comparer
        int valueOfA;
        // Créer un deuxième pointeur temporaire pour l'adresse A qui sera utilisé si l'adressage est indirect
        int tempPointer2=Math.abs((pointer+this.getAddressA())%VirtualMachine.TAILLE);

        if(this.modifierA==1) {
        	// L'adressage est immédiat, on effectue les opération directement dans l'instruction actuelle
            this.setAddressA(Math.floorMod(this.addressA - 1,VirtualMachine.TAILLE));
            valueOfA = this.addressA;
        }else{

            // Si l'adressage est indirect, modifie l'adresse du pointeur temporaire 2 par cette nouvelle adresse.
            tempPointer2=this.indirectToRelative(memory.getFromMemory(tempPointer2),tempPointer2);

				
            Instruction destination=memory.getFromMemory(tempPointer2);

            destination.setAddressA(Math.floorMod(destination.getAddressA()-1,VirtualMachine.TAILLE));

            valueOfA=destination.getAddressA();

        }

        if(valueOfA==0){

            return tempPointer;
        }else{

            return Math.floorMod(pointer+1,VirtualMachine.TAILLE);

        }
    }

    /**
     * Méthode isValidConfig, qui vérifie si l'instruction est correctement écrite
     * @throws BadInstructionException
     */
    @Override
    public void isValidConfig() throws BadInstructionException{}

    /**
     * Méthode clone, qui clone l'instruction
     * @return Une nouvelle instruction, identique à celle sur laquelle la méthode a été appellée
     */
    @Override
    public Instruction clone(){
        return new Djz(this.modifierA,this.getAddressA(),this.modifierB,this.getAddressB(),this.lastAccessed);
    }
}