package main;

import virtual.Reader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe de test Test_Write qui permet de tester si le CoreWar peut trouver
 * de la place pour deux programmes d'une taille conséquente, en sachant
 * qu'ils ne doivent pas se chevaucher.
 * Le test se base sur un programme initial de cinq lignes, qui peut être lu
 * par le parser sans erreur, et on va dupliquer ce programme pour allonger
 * le fichier. On teste jusqu'à un programme de 400 lignes, soit 800 lignes
 * d'instructions à mettre dans une mémoire de 1024 cases.
 */
public class Test_Write {

    public static void main(String[] args) {

        /*
        On récupère le code initial dans le fichier test.txt
         */
        String text= String.join(System.lineSeparator(),Reader.readPgrm("test.txt"));



        /*
        A chaque itération, on va append au fichier writeTest.red (créé à la
        première itération) la string text, et on va essayer d'entrer ces
        deux programmes dans la mémoire, en initialisant une instance de
        CoreWarGame. Si il y arrive, alors on affiche un message et on
        recommence.
         */
        for (int i = 0; i <80 ; i++) {

            File newFile=new File("writeTest.red");

            try(BufferedWriter bw=new BufferedWriter((new FileWriter(newFile,true)))){

                bw.write(text+System.lineSeparator());
            }catch(IOException e){

                e.printStackTrace();
            }

            new CoreWarGame("writeTest.red","writeTest.red");

            System.out.println("Ok, le CoreWar peut faire rentrer deux programmes de "+(5*(i+1))+" Lignes chacun.");


            
        }










    }


}
