package virtual.instruction;
import errors.BadInstructionException;
import virtual.VirtualMachine;
import virtual.Warrior;

import java.util.ArrayList;

/**
 * Classe JMP, instruction RedCode qui saute au champ A.
 * Le champ B n'est pas utilisé.
 */
public class Jmp extends Instruction{

    /**
     * Constructeur de copie
      * @param modifierA Le mode d'adressage du champ A
     * @param addressA Adresse du champ A
     * @param w Dernier Warrior a avoir eu accès à cette instruction
     */
    private Jmp(Integer modifierA,Integer addressA,Warrior w){
        super(modifierA, addressA, null, null,w);
    }

    /**
     * Constructeur standard
     * @param field Le champ unique
     */
    public Jmp(ArrayList<Integer> field){
        super(field.get(0),field.get(1),null,null,null);
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

        int jumpTo=Math.floorMod(pointer+this.getAddressA(),VirtualMachine.TAILLE);
        if(this.getModifierA()==2){

            jumpTo=this.indirectToRelative(memory.getFromMemory(jumpTo),jumpTo);

        }

		if(this.getModifierA()==1){
			jumpTo=Math.floorMod(this.getAddressA(),VirtualMachine.TAILLE);
		}

        return jumpTo;
    }

    /**
     * Méthode isValidConfig, qui vérifie si l'instruction est correctement écrite
     * @throws BadInstructionException
     */
    @Override
    public void isValidConfig() throws BadInstructionException{
    }

    /**
     * Méthode clone, qui clone l'instruction
     * @return Une nouvelle instruction, identique à celle sur laquelle la méthode a été appellée
     */
    @Override
    public Instruction clone(){

        return new Jmp(this.modifierA,this.getAddressA(),this.lastAccessed);
    }
}