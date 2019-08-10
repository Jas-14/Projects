package virtual.instruction;

import errors.BadInstructionException;
import virtual.VirtualMachine;
import virtual.Warrior;

import java.util.ArrayList;
/**
 * Classe CMP, virtual.instruction Redcode qui compare le champ A avec le champ B.
 * Si les champs sont égaux, on effectue l'instruction suivante sinon on saute cette
 * instruction et on effectue celle d'après.
 * Les deux champs sont obligatoirement des entiers.
 */
public class Cmp extends Instruction{

    /**
     * Constructeur en cas de copie
     * @param modifierA Mode d'adressage du champ A
     * @param addressA Adresse du champ A
     * @param modifierB Mode d'adressage du champ B
     * @param addressB Adresse du champ B
     * @param w Dernier Warrior a avoir eu accès à l'instruction
     */
    private Cmp(Integer modifierA, Integer addressA, Integer modifierB, Integer addressB,Warrior w){
        super(modifierA, addressA, modifierB, addressB,w);
    }

    /**
     * Constructeur standard
     * @param field1 Champ 1
     * @param field2 Champ 2
     */
    public Cmp(ArrayList<Integer> field1, ArrayList<Integer> field2){
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
        if(this.getAddressA().equals(this.getAddressB())){
        	// Passe à l'instruction suivante
            return Math.floorMod(pointer+1,VirtualMachine.TAILLE);
        }
        else{
        	// Saute l'instruction suivante
            return Math.floorMod(pointer+2,VirtualMachine.TAILLE);
        }
    }

    /**
     * Méthode isValidConfig, qui vérifie si l'instruction est correctement écrite
     * @throws BadInstructionException
     */
    @Override
    public void isValidConfig() throws BadInstructionException{
    	
    	// Si les champs sont autres chose que des entiers, on renvoie une erreur.
        if(!(this.modifierA==1) || !(this.modifierB==1) ){
            throw new BadInstructionException();
        }
    }

    /**
     * Méthode clone, qui clone l'instruction
     * @return Une nouvelle instruction, identique à celle sur laquelle la méthode a été appellée
     */
    @Override
    public Instruction clone(){
        return new Cmp(this.modifierA,this.getAddressA(),this.modifierB,this.getAddressB(),this.lastAccessed);
    }

}