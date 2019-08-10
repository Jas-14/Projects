package virtual.instruction;

import errors.BadInstructionException;
import virtual.VirtualMachine;

import java.util.ArrayList;
/**
 *Classe ADD, instruction Redcode qui ajoute le champs A au champs A à l'adresse B.
 *Le champs A peut être une adresse ou un entier, le champs B est forcément une adresse.
 */
//Est-ce que ADD gère l'adressage relatif? ça n'apporte rien
public class Add extends Instruction{

    /**
     * Constructeur de la classe
     */
    public Add(Integer modifierA, Integer addressA, Integer modifierB, Integer addressB){
        super(modifierA, addressA, modifierB, addressB);
    }

    public Add(ArrayList<Integer> field1, ArrayList<Integer> field2){
        this(field1.get(0),field1.get(1),field2.get(0),field2.get(1));
    }


    @Override
    public int execution(int pointer, VirtualMachine memory){

        //Cas particulier ADD #A #B -> ADD #A #A+B
        if(this.getModifierB()==1){

            this.setAddressB(this.getAddressA()+this.getAddressB());
            return (pointer+1) % 1024;

        }

        // On initialise un pointeur pour obtenir l'adresse qui sera modifiée

        int tempPointer = (pointer+this.getAddressB()) % 1024;

        //Adressage relatif de B
        if(this.getModifierB()==2){

            tempPointer=this.indirectToRelative(memory.getFromMemory(tempPointer),tempPointer,VirtualMachine.TAILLE);

        }

        // On vérifie si l'adressage est immédiat
        //if(this.modifierA==1){

            //Adressage non relatif, on modifie le champs A de l'instruction ADD
            memory.getFromMemory(tempPointer).setAddressA(memory.getFromMemory(tempPointer).getAddressA()+this.addressA);
        //}
        /*else{

            //Adressage relatif, on modifie le champs A à l'adresse actuelle+A
            int tempPointer2 = (pointer+this.getAddressA()) % 1024;
            memory.getFromMemory(tempPointer).setAddressA(memory.getFromMemory(tempPointer).getAddressA()+memory.getFromMemory(tempPointer2).getAddressA());
        }*/

        //Le pointeur passe à l'instruction suivante
        return (pointer+1) % 1024;
    }

    @Override
    public void isValidConfig() throws BadInstructionException{

        //On souhaite que le modifier A ne soit uniquement '#'
        if(!(this.modifierA==1)){
            throw new BadInstructionException();
        }
    }

    @Override
    public Instruction clone(){
        return new Add(this.modifierA,this.getAddressA(),this.modifierB,this.getAddressB());
    }

}