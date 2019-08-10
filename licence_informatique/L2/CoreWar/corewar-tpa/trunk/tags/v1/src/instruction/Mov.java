package virtual.instruction;

import errors.BadInstructionException;
import virtual.VirtualMachine;

import java.util.ArrayList;

/**
 * Classe MOV, instruction RedCode qui déplace une instruction du field A au field B. Le field A peut être un entier mais le field B est obligatoirement une adresse, relative ou indirecte.
 *
 * Pas encore d'implémentation pour adressage indirect
 */
public class Mov extends Instruction {

    public Mov(Integer modifierA,Integer addressA,Integer modifierB,Integer addressB){
        super(modifierA,addressA,modifierB,addressB);
    }

    public Mov(ArrayList<Integer> field1,ArrayList<Integer> field2){
        super(field1,field2);
    }

    @Override
    public int execution(int pointer, VirtualMachine memory) {
        /*L'addressage est relatif. Etre sur une instruction revient à être en position 0*/
        int temppointer=0;

        /*
        Si l'adresse A est en fait un entier, c'est qu'il faut déplacer l'entier sur une autre adresse, signifiée dans l'adresse B
         */
        if(this.getModifierA()==1){

            /*On mets le pointeur temporaire à l'adresse voulue pour avoir l'instruction de destination dans laquelle sera copié l'entier*/
            temppointer=(pointer+this.getAddressB()) % 1024;

            Instruction destination=memory.getFromMemory(temppointer);

            /*adressage indirect*/
            if(this.getModifierB()==2){

                /*L'adresse recherchée est sur l'adresse A de
                l'instruction que l'on vient de récupérer
                 */
                temppointer=this.indirectToRelative(destination,temppointer,VirtualMachine.TAILLE);

                destination=memory.getFromMemory(temppointer);

            }

            destination.setAddressA(this.getAddressA());

            return (pointer+1) % 1024;


        }else{

            /*On mets le pointeur temporaire à l'adresse voulue pour avoir l'instruction qui sera copiée*/
            temppointer=(pointer+this.getAddressA()) % 1024;
            Instruction source=memory.getFromMemory(temppointer);

            /*On trouve l'adresse de destination de l'instruction à déplacer*/
            temppointer=(pointer+this.getAddressB()) % 1024;

            /*Adressage indirect*/
            if(this.getModifierB()==2){

                /*L'adresse recherchée est sur l'adresse A de
                l'instruction que l'on vient de récupérer
                 */

                temppointer=this.indirectToRelative(memory.getFromMemory(temppointer),temppointer,VirtualMachine.TAILLE);

            }

            memory.insertInstruction(source.clone(),temppointer);

            return (pointer+1) % 1024;
        }




    }

    @Override
    public void isValidConfig() throws BadInstructionException {

        /*L'instruction MOV ne peut pas être exécutée si le second champ
        est un entier
         */
        if(this.getModifierB()==1){

            throw new BadInstructionException();
        }
    }

    @Override
    public Instruction clone(){

        return new Mov(this.modifierA,this.getAddressA(),this.modifierB,this.getAddressB());

    }
}
