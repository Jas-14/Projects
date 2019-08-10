package virtual.instruction;
import errors.BadInstructionException;
import virtual.VirtualMachine;

import java.util.ArrayList;

/**
 * Classe JMP, instruction RedCode qui saute au champs A.
 */
public class Jmp extends Instruction{


    public Jmp(Integer modifierA,Integer addressA){
        super(modifierA, addressA, null, null);
    }

    public Jmp(ArrayList<Integer> field){
        super(field.get(0),field.get(1),null,null);
    }

    @Override
    public int execution(int pointer, VirtualMachine memory){
        return (pointer+this.getAddressA())%1024;
    }

    @Override
    public void isValidConfig() throws BadInstructionException{
        if(this.modifierA!=0){
            throw new BadInstructionException();
        }
    }

    @Override
    public Instruction clone(){

        return new Jmp(this.modifierA,this.getAddressA());
    }
}