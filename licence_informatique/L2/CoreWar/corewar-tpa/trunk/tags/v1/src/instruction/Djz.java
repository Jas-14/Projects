package virtual.instruction;

import errors.BadInstructionException;
import virtual.VirtualMachine;

import java.util.ArrayList;

public class Djz extends Instruction{

    public Djz(Integer modifierA, Integer addressA, Integer modifierB, Integer adressB){
        super(modifierA, addressA, modifierB, adressB);
    }

    public Djz(ArrayList<Integer> field1, ArrayList<Integer> field2){
        this(field1.get(0),field1.get(1),field2.get(0),field2.get(1));
    }

    @Override
    public int execution(int pointer, VirtualMachine memory){

        int tempPointer=(pointer+this.getAddressB())%1024;

        if(this.getModifierB()==2){

            tempPointer=this.indirectToRelative(memory.getFromMemory(tempPointer),tempPointer,VirtualMachine.TAILLE);

        }

        int valueOfA=0;
        int tempPointer2=(pointer+this.getAddressA())%1024;

        if(this.modifierA==1) {
            this.setAddressA(this.addressA - 1);
            valueOfA = this.addressA;
        }else{

            if(this.modifierA==2){

                tempPointer2=this.indirectToRelative(memory.getFromMemory(tempPointer2),tempPointer2,VirtualMachine.TAILLE);

            }

            Instruction destination=memory.getFromMemory(tempPointer2);

            destination.setAddressA(destination.getAddressA()-1);

            valueOfA=destination.getAddressA();

        }

        if(valueOfA==0){

            return tempPointer;
        }else{

            return (pointer+1)%1024;

        }

            /*if(this.addressA==0){

                if(this.getModifierB()==2){
                    int tempPointer=(pointer+this.getAddressB())%1024;
                    if (memory.getFromMemory(tempPointer).getAddressB()==null){
                        return (pointer+this.getAddressB()+memory.getFromMemory(tempPointer).getAddressA())%1024;
                    }
                    else{
                        return (pointer+this.getAddressB()+memory.getFromMemory(tempPointer).getAddressB())%1024;
                    }
                }

                else{
                    return (pointer+this.getAddressB())%1024;
                }
            }

            else{
                return (pointer+1)%1024;
            }
        }*/

        /*else{
            int tempPointer=(pointer+this.getAddressA())%1024;
            memory.getFromMemory(tempPointer).setAddressA(memory.getFromMemory(tempPointer).getAddressA()-1);

            if(memory.getFromMemory(tempPointer).getAddressA()==0){

                if(this.getModifierB()==2){
                    int tempPointer2=(pointer+this.getAddressB())%1024;

                    if (memory.getFromMemory(tempPointer2).getAddressB()==null){
                        return (pointer+this.getAddressB()+memory.getFromMemory(tempPointer2).getAddressA())%1024;
                    }
                    else{
                        return (pointer+this.getAddressB()+memory.getFromMemory(tempPointer2).getAddressB())%1024;
                    }
                }

                else{
                    return (pointer+this.getAddressB())%1024;
                }
            }

            else{
                return (pointer+1)%1024;
            }
        }*/
    }

    @Override
    public void isValidConfig() throws BadInstructionException{
        if(this.modifierB==1){
            throw new BadInstructionException();
        }
    }

    @Override
    public Instruction clone(){
        return new Djz(this.modifierA,this.getAddressA(),this.modifierB,this.getAddressB());
    }
}