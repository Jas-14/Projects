package virtual.instruction;

import errors.BadInstructionException;
import virtual.VirtualMachine;

import java.util.ArrayList;

public class Jmz extends Instruction{

    public Jmz(Integer modifierA,Integer addressA, Integer modifierB,Integer addressB){
        super(modifierA, addressA, modifierB, addressB);
    }

    public Jmz(ArrayList<Integer> field1, ArrayList<Integer> field2){
        this(field1.get(0),field1.get(1),field2.get(0),field2.get(1));
    }

    @Override
    public int execution(int pointer, VirtualMachine memory) {

        int tempPointer=(pointer +this.getAddressB())%1024;

        if(this.modifierB==2){

            tempPointer=this.indirectToRelative(memory.getFromMemory(tempPointer),tempPointer,VirtualMachine.TAILLE);
        }
        if (this.modifierA == 1) {
            if (this.getAddressA() == 0) {
                return tempPointer;
            }
        }
    	else{
                int tempPointer2 = (pointer + this.getAddressA()) % 1024;

                if(this.modifierA==2){

                    tempPointer2=this.indirectToRelative(memory.getFromMemory(tempPointer2),tempPointer2,VirtualMachine.TAILLE);

                }
                if ((memory.getFromMemory(tempPointer2).getAddressA() == 0)) {
                    return tempPointer;
                }
            }

        return (pointer + 1) % 1024;
        }



        @Override
        public void isValidConfig() throws BadInstructionException {
            if(this.getModifierB()==1){
                throw new BadInstructionException();
            }

        }

        @Override
        public Instruction clone(){

            return new Jmz(this.modifierA,this.getAddressA(),this.modifierB,this.getAddressB());
        }
    }