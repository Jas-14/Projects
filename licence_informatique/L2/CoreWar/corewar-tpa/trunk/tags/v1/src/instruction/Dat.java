package virtual.instruction;

import errors.BadInstructionException;
import virtual.VirtualMachine;

import java.util.ArrayList;

/**
 * Classe Dat, qui permet de représenter les données stockées qui ne sont pas
 * du code exécutable. Les données stockées sont des entiers.
 * Tout pointeur d'instruction qui arriverait sur une instruction de type DAT se retrouve mort.
 * Seul le modifier A et l'adresse A sont utilisés dans notre
 * implémentation.
 */
public class Dat extends Instruction {

    public Dat(Integer modifierA,Integer addressA){
        super(modifierA,addressA,null,null);
        /*Pour des champs non spécifiés, on passe null, pour signifier que ces attributs sont non renseignés*/

    }

    public Dat(ArrayList<Integer> field){
        super(field.get(0),field.get(1),null,null);
    }

    @Override
    public int execution(int pointer, VirtualMachine memory) {

        /*Exécution se résumant à retourner le pointeur mort*/
        return -1;
    }

    @Override
    public void isValidConfig() throws BadInstructionException {

        /*Par convention, on souhaite que le champ A soit signifié explicitement comme étant un entier*/
        if(!(this.getModifierA()==1)){

            throw new BadInstructionException();
        }

    }

    @Override
    public Instruction clone(){

        return new Dat(this.modifierA,this.getAddressA());
    }


}
