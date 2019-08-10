package instruction;

import errors.BadInstructionException;
import virtual.VirtualMachine;
import virtual.Warrior;

import java.util.ArrayList;
/**
 * Classe CMP, instruction Redcode qui compare le champ A avec le champ B.
 * Si les champs sont égaux, on effectue l'instruction suivante sinon on saute cette instruction et on effectue celle d'après.
 * On considère qu'on ne peut pas comparer des instructions.
 */
public class Cmp extends Instruction{
	 /**
     * Constructeur de la classe
     */
    public Cmp(Integer modifierA, Integer addressA, Integer modifierB, Integer addressB,Warrior w){
        super(modifierA, addressA, modifierB, addressB,w);
    }

    public Cmp(ArrayList<Integer> field1, ArrayList<Integer> field2){
        super(field1,field2);
    }

    @Override
    public int execution(int pointer, VirtualMachine memory, Warrior w){

        this.setLastAccessed(w);

        int valueA=this.getAddressA();

        if(this.getModifierA()==2){

            valueA=memory.getFromMemory(valueA).getAddressA();

        }

        int valueB=this.getAddressB();

        if(this.getModifierB()==2){

            valueB=memory.getFromMemory(valueB).getAddressA();
        }



        if(valueA==valueB){
        	// Passe à l'instruction suivante
            return (pointer+1)%VirtualMachine.TAILLE;
        }
        else{
        	// Saute l'instruction suivante
            return (pointer+2)%VirtualMachine.TAILLE;
        }
    }

    @Override
    public void isValidConfig() throws BadInstructionException{
    	
    	// Si les champs sont autres chose que des entiers, on renvoie une erreur.
        if(this.modifierA==0 || this.modifierB==0){
            throw new BadInstructionException();
        }
    }

    @Override
    public Instruction clone(){
        return new Cmp(this.modifierA,this.getAddressA(),this.modifierB,this.getAddressB(),this.lastAccessed);
    }

}