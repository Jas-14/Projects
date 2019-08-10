package instruction;

import errors.BadInstructionException;
import virtual.VirtualMachine;
import virtual.Warrior;

import java.util.ArrayList;

/**
 * Classe JMZ, instruction RedCode qui saute au champ B si le champ A est égal à zéro.
 * Le champ B est une adresse, avec adressage relatif, ou non.
 */
public class Jmz extends Instruction{
	 
	 /**
	  * Constructeur de la classe
	  */
    public Jmz(Integer modifierA,Integer addressA, Integer modifierB,Integer addressB,Warrior w){
        super(modifierA, addressA, modifierB, addressB,w);
    }

    public Jmz(ArrayList<Integer> field1, ArrayList<Integer> field2){
        super(field1,field2);
    }

    @Override
    public int execution(int pointer, VirtualMachine memory, Warrior w) {

        this.setLastAccessed(w);
		  // Pointeur temporaire qui permet de récupérer les données à l'adresse B
        int tempPointer=(pointer +this.getAddressB())%VirtualMachine.TAILLE;

        if(this.modifierB==2){
				// Si l'adressage est indirect, modifie l'adresse du pointeur temporaire par cette nouvelle adresse.
            tempPointer=this.indirectToRelative(memory.getFromMemory(tempPointer),tempPointer,VirtualMachine.TAILLE);
        }
        if (this.modifierA == 1) {
        		// L'adressage est immédiat, on effectue les opération directement dans l'instruction actuelle
            if (this.getAddressA() == 0) {
                return tempPointer;
            }
        }
    	  else{
    			 // Créer un deuxième pointeur temporaire pour l'adresse A qui sera utilisé si l'adressage est relatif
             int tempPointer2 = (pointer + this.getAddressA()) % VirtualMachine.TAILLE;

             if(this.modifierA==2){

                 tempPointer2=this.indirectToRelative(memory.getFromMemory(tempPointer2),tempPointer2,VirtualMachine.TAILLE);

             }
             if ((memory.getFromMemory(tempPointer2).getAddressA() == 0)) {
                 return tempPointer;
             }
         }
			
		  // Si rien n'est égale à zéro, on saute à l'instruction suivante
        return (pointer + 1) % VirtualMachine.TAILLE;
     }



        @Override
        public void isValidConfig() throws BadInstructionException {
            if(this.getModifierB()==1){
                throw new BadInstructionException();
            }

        }

        @Override
        public Instruction clone(){

            return new Jmz(this.modifierA,this.getAddressA(),this.modifierB,this.getAddressB(),this.lastAccessed);
        }
    }