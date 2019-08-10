package geneticPgrm;

import virtual.instruction.Instruction;
import virtual.Warrior;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Classe Writer, utilisée pour écrire les programmes dans un
 * fichier
 */
public class Writer {

    /**
     * Méthode writePgrm, qui écrit le programme d'un Warrior
     * dans un fichier.
     * @param w Le Warrior qui possède le programme à écrire
     * @param nameFile Le nom du fichier
     */
    public static void writePgrm(Warrior w,String nameFile){


        File file=new File(nameFile);

        String separator=System.getProperty("line.separator");

        List<Instruction> pgrm=w.getPrgrm();

        try(BufferedWriter bw=new BufferedWriter(new FileWriter(file,true))) {

            for (Instruction i:pgrm) {

                bw.write(i.toString()+separator);

            }



        }catch (IOException e){

            e.printStackTrace();
        }

    }

}
