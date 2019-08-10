package main;

import virtual.instruction.Instruction;
import virtual.Parser;
import virtual.Reader;
import virtual.VirtualMachine;

import java.util.List;

public class Master {

    private VirtualMachine machine;


    public Master(String nameFile){

        List<Instruction> prgrm=Parser.prgrmToListInstruction(Reader.readPgrm(nameFile));

        this.machine=new VirtualMachine(prgrm);



    }

    public void gameLoop(){

        System.out.println("Début");

        int save=this.machine.getPointer();

        while (this.machine.getPointer()!=-1){

            System.out.println(this.display());
            this.oneLoop();

        }

        System.out.println("Terminé");

        System.out.println(this.machine.getFromMemory(save));


    }

    public void oneLoop(){

        int pointer=this.machine.getPointer();

        Instruction i=this.machine.getFromMemory(this.machine.getPointer());

        pointer=i.execution(pointer,this.machine);

        this.machine.setPointer(pointer);
    }

    public String display(){

        return "Now in "+this.machine.getPointer()+" : "+this.machine.getFromMemory(this.machine.getPointer());

    }

}
