package virtual.instruction;

import errors.BadInstructionException;
import virtual.VirtualMachine;

import java.util.ArrayList;

public class Cmp extends Instruction{

    public Cmp(Integer modifierA, Integer addressA, Integer modifierB, Integer adressB){
        super(modifierA, addressA, modifierB, adressB);
    }

    public Cmp(ArrayList<Integer> field1, ArrayList<Integer> field2){
        this(field1.get(0),field1.get(1),field2.get(0),field2.get(1));
    }

    @Override
    public int execution(int pointer, VirtualMachine memory){
        if(this.getAddressA().equals(this.getAddressB())){
            return (pointer+1)%1024;
        }
        else{
            return (pointer+2)%1024;
        }
    }

    @Override
    public void isValidConfig() throws BadInstructionException{
        if(!(this.modifierA==1) || !(this.modifierB==1) ){
            throw new BadInstructionException();
        }
    }

    @Override
    public Instruction clone(){
        return new Cmp(this.modifierA,this.getAddressA(),this.modifierB,this.getAddressB());
    }

}