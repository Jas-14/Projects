package tests;

import virtual.util.Reader;
import errors.CorewarException;
import main.CoreWarGame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe de test Test_Write qui permet de tester si le CoreWar peut trouver
 * de la place pour deux programmes d'une taille conséquente, en sachant
 * qu'ils ne doivent pas se chevaucher.
 */
public class Test_Write {

    /**
     * Méthode main
     * @param args Non utilisé
     */
    public static void main(String[] args) {

        /*
        On récupère le code initial dans le fichier test.txt
         */
        String text= String.join(System.lineSeparator(),Reader.readPgrm("test.txt"));



        /*
        A chaque itération, on va append au fichier writeTest.red (créé à la
        première itération) la string text, et on va essayer d'entrer ces
        deux programmes dans la mémoire, en initialisant une instance de
        CoreWarGame.
         */
        for (int i = 0; i <351 ; i++) {

            File newFile=new File("writeTest.red");

            try(BufferedWriter bw=new BufferedWriter((new FileWriter(newFile,true)))){

                bw.write(text+System.lineSeparator());
            }catch(IOException e){

                e.printStackTrace();
            }

            long time=System.nanoTime();
            try{
            new CoreWarGame("writeTest.red","writeTest.red");}catch (CorewarException e){}

            System.out.println((i+1)+" "+(System.nanoTime()-time)/Math.pow(10,3));


            
        }










    }


}
