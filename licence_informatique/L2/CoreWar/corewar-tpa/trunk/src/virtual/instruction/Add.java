package virtual.instruction;

import errors.BadInstructionException;
import virtual.VirtualMachine;
import virtual.Warrior;

import java.util.ArrayList;
/**
 *Classe ADD, instruction Redcode qui ajoute le champ A au champ A à l'adresse B.
 *Le champ A est forcément un entier
 */
public class Add extends Instruction{

    /**
     * Constructeur en cas de copie
     * @param modifierA Mode d'adressage du champ A
     * @param addressA Adresse du champ A
     * @param modifierB Mode d'adressage du champ B
     * @param addressB Adresse du champ B
     * @param w Dernier Warrior à avoir eu accès à cette instruction
     */
    private Add(Integer modifierA, Integer addressA, Integer modifierB, Integer addressB,Warrior w){
        super(modifierA, addressA, modifierB, addressB,w);
    }

    /**
     * Constructeur standard
     * @param field1 Le champ 1
     * @param field2 Le champ 2
     */
    public Add(ArrayList<Integer> field1, ArrayList<Integer> field2){
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

        //Cas particulier ADD #A #B -> ADD #A+B #B
        if(this.getModifierB()==1){

            this.setAddressA(this.getAddressA()+this.getAddressB());
            return Math.floorMod(pointer+1, VirtualMachine.TAILLE);

        }

        // On initialise un pointeur pour obtenir l'adresse qui sera modifiée

        int tempPointer = Math.floorMod(pointer+this.getAddressB(),VirtualMachine.TAILLE);

        //Adressage relatif de B
        if(this.getModifierB()==2){

            tempPointer=this.indirectToRelative(memory.getFromMemory(tempPointer),tempPointer);

        }

        memory.getFromMemory(tempPointer).setAddressA(Math.floorMod(memory.getFromMemory(tempPointer).getAddressA()+this.addressA,VirtualMachine.TAILLE));


        //Le pointeur passe à l'instruction suivante
        return Math.floorMod(pointer+1,VirtualMachine.TAILLE);

    }

    /**
     * Méthode isValidConfig, qui vérifie si l'instruction est correctement écrite
     * @throws BadInstructionException
     */
    @Override
    public void isValidConfig() throws BadInstructionException{

        //On souhaite que le modifier A soit uniquement '#'
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
        return new Add(this.modifierA,this.getAddressA(),this.modifierB,this.getAddressB(),this.lastAccessed);
    }

}