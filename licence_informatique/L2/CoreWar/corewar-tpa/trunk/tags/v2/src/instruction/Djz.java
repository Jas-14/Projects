package instruction;

import errors.BadInstructionException;
import virtual.VirtualMachine;
import virtual.Warrior;

import java.util.ArrayList;

/**
 * Classe DJZ, instruction Redcode qui décrémente le contenu du champ A puis compare la valeur à zéro.
 * Si la valeur est égale à zéro, on saute à l'instruction B, sinon on passe à l'instruction suivante.
 * Le champ A peut être une adresse ou un entier. Le champ B est uniquement une adresse, avec adressage relatif ou indirect.
 */

public class Djz extends Instruction{
	/**
	 * Constructeur de la classe
	 */

    public Djz(Integer modifierA, Integer addressA, Integer modifierB, Integer addressB,Warrior w){
        super(modifierA, addressA, modifierB, addressB,w);
    }

    public Djz(ArrayList<Integer> field1, ArrayList<Integer> field2){
        super(field1,field2);
    }

    @Override
    public int execution(int pointer, VirtualMachine memory, Warrior w){

        this.setLastAccessed(w);

		  // Pointeur temporaire qui permet de récupérer les données à l'adresse B
        int tempPointer=(pointer+this.getAddressB())%VirtualMachine.TAILLE;

        if(this.getModifierB()==2){
		  // Si l'adressage est indirect, modifie l'adresse du pointeur temporaire par cette nouvelle adresse.
            tempPointer=this.indirectToRelative(memory.getFromMemory(tempPointer),tempPointer,VirtualMachine.TAILLE);

        }
		  
		  // Variable qui servira pour comparer
        int valueOfA=0;
        // Créer un deuxième pointeur temporaire pour l'adresse A qui sera utilisé si l'adressage est relatif
        int tempPointer2=(pointer+this.getAddressA())%VirtualMachine.TAILLE;

        if(this.modifierA==1) {
        	// L'adressage est immédiat, on effectue les opération directement dans l'instruction actuelle
            this.setAddressA(this.addressA - 1);
            valueOfA = this.addressA;
        }else{

            if(this.modifierA==2){
				// Si l'adressage est indirect, modifie l'adresse du pointeur temporaire 2 par cette nouvelle adresse.
                tempPointer2=this.indirectToRelative(memory.getFromMemory(tempPointer2),tempPointer2,VirtualMachine.TAILLE);

            }
				
            Instruction destination=memory.getFromMemory(tempPointer2);

            destination.setAddressA(destination.getAddressA()-1);

            valueOfA=destination.getAddressA();

        }

        if(valueOfA==0){

            return tempPointer;
        }else{

            return (pointer+1)%VirtualMachine.TAILLE;

        }
    }

    @Override
    public void isValidConfig() throws BadInstructionException{
        if(this.modifierB==1){
            throw new BadInstructionException();
        }
    }

    @Override
    public Instruction clone(){
        return new Djz(this.modifierA,this.getAddressA(),this.modifierB,this.getAddressB(),this.lastAccessed);
    }
}