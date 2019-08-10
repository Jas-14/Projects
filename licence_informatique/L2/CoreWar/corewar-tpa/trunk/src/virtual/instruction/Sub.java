package virtual.instruction;

import errors.BadInstructionException;
import virtual.VirtualMachine;
import virtual.Warrior;

import java.util.ArrayList;

/**
 *Classe SUB, virtual.instruction Redcode qui soustrait le champ A au champ A à l'adresse B.
 */
public class Sub extends Instruction{

    /**
     * Constructeur de copie
     * @param modifierA Le mode d'adressage du champ A
     * @param addressA L'adresse du champ A
     * @param modifierB Le mode d'adressage du champ B
     * @param addressB L'adresse du champ B
     * @param w Dernier Warrior a avoir eu accès à cette instruction
     */
    private Sub(Integer modifierA, Integer addressA, Integer modifierB, Integer addressB,Warrior w){
        super(modifierA, addressA, modifierB, addressB,w);
    }

    /**
     * Constructeur standard
     * @param field1 Le champ 1
     * @param field2 Le champ 2
     */
    public Sub(ArrayList<Integer> field1, ArrayList<Integer> field2){
        super(field1,field2);
    }

    /**
     * Méthode execution, qui exécute l'instruction
     * @param pointer Le pointeur du Warrior qui appel l'instruction
     * @param memory La machine virtuelle
     * @param w Le Warrior qui appel l'instruction
     * @return La nouvelle position de pointer
     */
    @Override
    public int execution(int pointer, VirtualMachine memory, Warrior w){

        this.setLastAccessed(w);

        //Cas particulier SUB #A #B -> ADD #A-B #B
        if(this.getModifierB()==1){

            this.setAddressA(Math.floorMod(this.getAddressA()-this.getAddressB(),VirtualMachine.TAILLE));
            return Math.floorMod(pointer+1, VirtualMachine.TAILLE);

        }

        int tempPointer = Math.floorMod(pointer+this.getAddressB(),VirtualMachine.TAILLE);

        if(this.getModifierB()==2){

            tempPointer=this.indirectToRelative(memory.getFromMemory(tempPointer),tempPointer);

        }

        memory.getFromMemory(tempPointer).setAddressA(Math.floorMod(this.addressA-memory.getFromMemory(tempPointer).getAddressA(),VirtualMachine.TAILLE));

        return Math.floorMod(pointer+1,VirtualMachine.TAILLE);
    }

    /**
     * Méthode isValidConfig, qui vérifie si l'instruction est correctement écrite
     * @throws BadInstructionException
     */
    @Override
    public void isValidConfig() throws BadInstructionException{
        if(!(this.modifierA==1)){
            throw new BadInstructionException();
        }
    }

    /**
     * Méthode clone, qui clone l'instruction
     * @return Une nouvelle instruction, identique à celle sur laquelle la méthode a été appellée
     */
    @Override
    public Instruction clone(){
        return new Sub(this.modifierA,this.getAddressA(),this.modifierB,this.getAddressB(),this.lastAccessed);
    }

}