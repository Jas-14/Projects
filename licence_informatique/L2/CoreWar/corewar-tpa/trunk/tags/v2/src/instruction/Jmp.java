package instruction;
import errors.BadInstructionException;
import virtual.VirtualMachine;
import virtual.Warrior;

import java.util.ArrayList;

/**
 * Classe JMP, instruction RedCode qui saute au champ A.
 * Le champ B n'est pas utilisé. Le champ A est obligatoirement en adressage relatif
 */
public class Jmp extends Instruction{

	 /**
	  * Constructeur de la classe
	  */	
    public Jmp(Integer modifierA,Integer addressA,Warrior w){
        super(modifierA, addressA, null, null,w);
    }

    public Jmp(ArrayList<Integer> field){
        super(field.get(0),field.get(1),null,null,null);
    }

    @Override
    public int execution(int pointer, VirtualMachine memory, Warrior w){
        this.setLastAccessed(w);

        int jumpTo=pointer+this.getAddressA()%VirtualMachine.TAILLE;
        if(this.modifierA==2){

            jumpTo=this.indirectToRelative(memory.getFromMemory(jumpTo),jumpTo,VirtualMachine.TAILLE);

        }

        return jumpTo;
    }

    @Override
    public void isValidConfig() throws BadInstructionException{
        if(this.modifierA==1){
            throw new BadInstructionException();
        }
    }

    @Override
    public Instruction clone(){

        return new Jmp(this.modifierA,this.getAddressA(),this.lastAccessed);
    }
}