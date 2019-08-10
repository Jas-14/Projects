package instruction;

import errors.BadInstructionException;
import virtual.VirtualMachine;
import virtual.Warrior;

import java.util.ArrayList;

/**
 *Classe SUB, instruction Redcode qui soustrait le champ A au champ A à l'adresse B.
 *Le champ A est forcément un entier, le champ B est forcément une adresse.
 */
public class Sub extends Instruction{

    public Sub(Integer modifierA, Integer addressA, Integer modifierB, Integer addressB,Warrior w){
        super(modifierA, addressA, modifierB, addressB,w);
    }

    public Sub(ArrayList<Integer> field1, ArrayList<Integer> field2){
        super(field1,field2);
    }

    @Override
    public int execution(int pointer, VirtualMachine memory, Warrior w){

        this.setLastAccessed(w);

        //Cas particulier SUB #A #B -> ADD #A #A-B
        if(this.getModifierB()==1){

            this.setAddressB(this.getAddressA()-this.getAddressB());
            return (pointer+1) % VirtualMachine.TAILLE;

        }

        int tempPointer = (pointer+this.getAddressB()) % VirtualMachine.TAILLE;

        if(this.getModifierB()==2){

            tempPointer=this.indirectToRelative(memory.getFromMemory(tempPointer),tempPointer,VirtualMachine.TAILLE);

        }

        memory.getFromMemory(tempPointer).setAddressA(this.addressA-memory.getFromMemory(tempPointer).getAddressA());

        return (pointer+1) % VirtualMachine.TAILLE;
    }

    @Override
    public void isValidConfig() throws BadInstructionException{
        if(!(this.modifierA==1)){
            throw new BadInstructionException();
        }
    }

    @Override
    public Instruction clone(){
        return new Sub(this.modifierA,this.getAddressA(),this.modifierB,this.getAddressB(),this.lastAccessed);
    }

}