package virtual.instruction;

import errors.BadInstructionException;
import virtual.VirtualMachine;
import virtual.Warrior;

import java.util.ArrayList;

/**
 * Classe MOV, instruction RedCode qui déplace une instruction du field A au field B.
 */
public class Mov extends Instruction {

    /**
     * Constructeur de copie
     * @param modifierA Le mode d'adressage du champ A
     * @param addressA  L'adresse du champ A
     * @param modifierB Le mode d'adressage du champ B
     * @param addressB L'adresse du champ B
     * @param w Dernier Warrior a avoir eu accès à l'instruction
     */
    private Mov(Integer modifierA,Integer addressA,Integer modifierB,Integer addressB,Warrior w){
        super(modifierA,addressA,modifierB,addressB,w);
    }

    /**
     * Constructeur standard
     * @param field1 Le champ 1
     * @param field2 Le champ 2
     */
    public Mov(ArrayList<Integer> field1,ArrayList<Integer> field2){
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
    public int execution(int pointer, VirtualMachine memory, Warrior w) {

        this.setLastAccessed(w);


        //On trouve ce qu'il y a à copier

        int srcpointer = Math.floorMod(this.getAddressA() + pointer, VirtualMachine.TAILLE);

        //Cas entier
        if (this.getModifierA() == 1) {

            srcpointer = this.getAddressA();
        }

        if (this.getModifierA() == 2) {

            srcpointer = this.indirectToRelative(memory.getFromMemory(srcpointer), srcpointer);
        }

        //On trouve destination

        int destpointer = Math.floorMod(this.getAddressB() + pointer, VirtualMachine.TAILLE);

        if (this.getModifierB() == 1) {

            destpointer = Math.floorMod(this.getAddressB(), VirtualMachine.TAILLE);
        }

        if (this.getModifierB() == 2) {

            destpointer = this.indirectToRelative(memory.getFromMemory(destpointer), destpointer);
        }

        if (this.getModifierA() == 1) {

            Instruction destination = memory.getFromMemory(destpointer);

            destination.setAddressA(srcpointer);
        } else {

            memory.insertInstruction(memory.getFromMemory(srcpointer).clone(), destpointer);
        }

        return Math.floorMod(pointer + 1, VirtualMachine.TAILLE);

    }

    /**
     * Méthode isValidConfig, qui vérifie si l'instruction est correctement écrite
     * @throws BadInstructionException
     */
    @Override
    public void isValidConfig() throws BadInstructionException {}

    /**
     * Méthode clone, qui clone l'instruction
     * @return Une nouvelle instruction, identique à celle sur laquelle la méthode a été appellée
     */
    @Override
    public Instruction clone(){

        return new Mov(this.modifierA,this.getAddressA(),this.modifierB,this.getAddressB(),this.lastAccessed);

    }
}
