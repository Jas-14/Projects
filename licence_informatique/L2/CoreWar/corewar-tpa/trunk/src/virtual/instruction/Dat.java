package virtual.instruction;

import errors.BadInstructionException;
import virtual.VirtualMachine;
import virtual.Warrior;

import java.util.ArrayList;

/**
 * Classe Dat, qui permet de représenter les données stockées qui ne sont pas
 * du code exécutable. Les données stockées sont des entiers.
 * Tout pointeur d'instruction qui arriverait sur une instruction de type DAT se retrouve mort.
 * Seul le modifier A et l'adresse A sont utilisés dans notre implémentation.
 */
public class Dat extends Instruction {

    /**
     * Constructeur en cas de copie
     * @param modifierA Mode d'adressage du champ A
     * @param addressA Adresse du champ A
     * @param w Dernier Warrior a avoir eu accès à cette instruction
     */
    public Dat(Integer modifierA,Integer addressA,Warrior w){
        super(modifierA,addressA,null,null,w);
        /*Pour des champs non spécifiés, on passe null, pour signifier que ces attributs sont non renseignés*/

    }

    /**
     * Constructeur standard
     * @param field Le champ unique
     */
    public Dat(ArrayList<Integer> field){
        super(field.get(0),field.get(1),null,null,null);
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

        /*Exécution se résumant à retourner le pointeur mort*/
        return -1;
    }

    /**
     * Méthode isValidConfig, qui vérifie si l'instruction est correctement écrite
     * @throws BadInstructionException
     */
    @Override
    public void isValidConfig() throws BadInstructionException {

        /*Par convention, on souhaite que le champ A soit signifié explicitement comme étant un entier*/
        if(!(this.getModifierA()==1)){

            throw new BadInstructionException();
        }

    }

    /**
     * Méthode clone, qui clone l'instruction
     * @return Une nouvelle instruction, identique à celle sur laquelle la méthode a été appellée
     */
    @Override
    public Instruction clone(){

        return new Dat(this.modifierA,this.getAddressA(),this.lastAccessed);
    }


}
