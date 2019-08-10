package tests;

import java.util.*;
import virtual.util.*;
import errors.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe permettant de tester le parser. A servi à tester le
 * bon fonctionnement des instructions également.
 */
public class Test_Parser {

    /**
     * Méthode main
     * @param args Non utilisé
     */
    public static void main(String args[]){

        /*Instruction i=Parser.stringToInstruction(String.join(" ",args));

        VirtualMachine vm=new VirtualMachine();

        vm.memory[0]=i;

        int k=i.execution(vm.pointer1,vm);

        System.out.println("Position pointeur "+k+System.lineSeparator()+"Instruction "+i);*/


        String prgrm=String.join(System.lineSeparator(),Reader.readPgrm("test.txt"));

        for (int i = 0; i <80 ; i++) {

            File newFile=new File("writeTest.red");

            try(BufferedWriter bw=new BufferedWriter((new FileWriter(newFile,true)))){

                bw.write(prgrm+System.lineSeparator());
            }catch(IOException e){

                e.printStackTrace();
            }


	           List<String> toparse=Reader.readPgrm("writeTest.red");

              long time=System.nanoTime();
               try{
                 Parser.prgrmToListInstruction(toparse);
                 System.out.println(toparse.size()+" "+(System.nanoTime()-time)/(Math.pow(10,3)));
               }catch(CorewarException e){

                 e.printStackTrace();
               }




    }
}

}
