package virtual.instruction;

import errors.BadInstructionException;
import virtual.VirtualMachine;

import java.util.ArrayList;

public class Sub extends Instruction{

    public Sub(Integer modifierA, Integer addressA, Integer modifierB, Integer adressB){
        super(modifierA, addressA, modifierB, adressB);
    }

    public Sub(ArrayList<Integer> field1, ArrayList<Integer> field2){
        this(field1.get(0),field1.get(1),field2.get(0),field2.get(1));
    }

    @Override
    public int execution(int pointer, VirtualMachine memory){

        //Cas particulier SUB #A #B -> ADD #A #A-B
        if(this.getModifierB()==1){

            this.setAddressB(this.getAddressA()-this.getAddressB());
            return (pointer+1) % 1024;

        }

        int tempPointer = (pointer+this.getAddressB()) % 1024;

        if(this.getModifierB()==2){

            tempPointer=this.indirectToRelative(memory.getFromMemory(tempPointer),tempPointer,VirtualMachine.TAILLE);

        }

        memory.getFromMemory(tempPointer).setAddressA(this.addressA-memory.getFromMemory(tempPointer).getAddressA());

        return (pointer+1) % 1024;
    }

    @Override
    public void isValidConfig() throws BadInstructionException{
        if(!(this.modifierA==1)){
            throw new BadInstructionException();
        }
    }

    @Override
    public Instruction clone(){
        return new Sub(this.modifierA,this.getAddressA(),this.modifierB,this.getAddressB());
    }

}