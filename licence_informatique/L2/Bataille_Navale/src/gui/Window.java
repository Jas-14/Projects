package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import game.*;

public class Window {
	
	/**
	 * Constructeur de la classe
	 *@param grille1 La grille du joueur Humain
	 *@param grille2 La grille du joueur Ia
	 */
	public Window(Mer grille1, Mer grille2){
		
		/*
		 * creation de la fenetre
		 */
		JFrame mainWindow = new JFrame("Bataille Navale");
		/*
		 * creation et coloration de la grille du joueur humain
		 */
		VueGrille grilleH=new VueGrille(grille1,grille2);
		grilleH.setBackground(new Color(0,132,158));
		/*
		 * cretaion et coloration de la grille du joueur machine
		 */
		VueGrille grilleA=new VueGrille(grille2,grille1);
		grilleA.setBackground(new Color(12,197,234));
		/*
		 * creation d'un panneau
		 */
		JPanel p = new JPanel(new GridLayout(1,2));
		/*
		 * ajout des deux grilles des joueurs dans le panneau qu'on vient de creer
		 */
		p.add(grilleH);
		p.add(grilleA);
		/*
		 * ajout du panneau dans la fenetre
		 */
		mainWindow.setContentPane(p);
		/*
		 * modification de la taille de la fenetre
		 */
		mainWindow.setSize(1000,530);
		/*
		 * on place la fenetre au milieu
		 */
		mainWindow.setLocationRelativeTo(null);
		/*
		 * on arrete l'execution quand la fenetre est fermee
		 */
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*
		 * on interdit de modifier la taille de la fenetre (graphiquement) par l'utilisateur
		 */
		mainWindow.setResizable(false);
		/*
		 * on rend la fenetre visible
		 */
		mainWindow.setVisible(true);
		
	}	
}