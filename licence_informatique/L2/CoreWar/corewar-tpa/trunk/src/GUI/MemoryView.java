package GUI;

import errors.CorewarException;
import errors.InvalidSizePgrm;
import main.CoreWarGame;
import util.EcouteurModele;
import virtual.VirtualMachine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

/**
 * Classe MemoryView, qui dessine l'affichage de la mémoire selon les mises à jour
 */

public class MemoryView extends JPanel implements EcouteurModele {

	/**
	 * Attribut memory, qui est la machine virtuelle avec les programmes chargés en son sein
	 */
	private VirtualMachine memory;

	/**
	 * Attribut model, objet de type CoreWarGame
	 */
	private CoreWarGame model;

	/**
	 * Constructeur de la clase
	 * @param model
	 */
	public MemoryView(CoreWarGame model) {

		this.memory=model.getMachine();
		this.model=model;
		setPreferredSize(new Dimension(1300,200));
		this.model.ajoutEcouteur(this);


	}

	/**
	 * Méthode modelMisAJour, qui capte un model afin de le metter à jour graphiquement
	 * @param src La source, si besoin
	 */
	@Override
	public void modeleMisAJour(Object src) {
		this.repaint();
		this.validate();
	}


	/**
	 * Méthode paintComponent, qui efface puis dessine une représentation de la machine virtuelle
	 * @param g Outils de Swing pour utiliser paintComponent
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int cpt = 0;
		for(int j=0; j<160; j+=10) {
			for(int i=0; i<1280; i+=20) {
				if(memory.getFromMemory(cpt).getLastAccessed()==memory.getW1()) {
					g.setColor(Color.RED);
				}
				else if(memory.getFromMemory(cpt).getLastAccessed()==memory.getW2()) {
					g.setColor(Color.BLUE);
				}
				else {
					g.setColor(Color.LIGHT_GRAY);
				}
				g.fillRect(i, j, 20, 10);
				g.setColor(Color.WHITE);
				g.drawRect(i, j, 20, 10);
				cpt++;
			}
		}
	}
}