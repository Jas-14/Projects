package main;

import virtual.instruction.Instruction;
import virtual.Parser;
import virtual.VirtualMachine;

/**
 * Classe permettant de tester le parser et l'exécution relative des instructions.
 * Fonctionnement: En argument de la ligne de commande, on rentre une instruction RedCode. Elle sera parsée pour être transformée en instance de la classe appropriée, elle sera mise à l'indice 0 de la mémoire et sera exécutée. Comme preuve, nous afficherons la position du pointeur et l'instruction qui a été exécutée.
 */
public class Test_Parser {

    public static void main(String args[]){

        Instruction i=Parser.stringToInstruction(String.join(" ",args));

        VirtualMachine vm=new VirtualMachine();

        vm.memory[0]=i;

        int k=i.execution(vm.pointer,vm);

        System.out.println("Position pointeur "+k+System.lineSeparator()+"Instruction "+i);


    }
}
