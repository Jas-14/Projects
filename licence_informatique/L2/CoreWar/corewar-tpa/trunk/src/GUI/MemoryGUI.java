package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

import main.CoreWarGame;
import virtual.VirtualMachine;

/**
 * Classe MemoryGUI qui créer la fenêtre principale du CoreWar
 * et permettre de lancer le jeu.
 */

public class MemoryGUI extends JFrame implements ActionListener{

	/**
	 * Attribut lancer, qui sera le bouton pour lancer la partie
	 */
	private JButton lancer;

	/**
	 * Attribut stop, qui sera le bouton pour quitter le jeu
	 */
	private JButton stop;

	/**
	 * Attribut model, objet de type CoreWarGame
	 */
	private CoreWarGame model;

	/**
	 * Attribut timer, qui permet d'avoir une pause entre deux tours de jeu
	 */
	private Timer timer;

	/**
	 * Attribut isTimerOn, qui permet de savoir si le timer est actif ou non
	 */
	private boolean isTimerOn;

	/**
	 * Constructeur de MemoryGUI
	 * @param model Le model qui contient les programmes RedCode
	 */
	public MemoryGUI(CoreWarGame model) {
		super("CoreWar");

		this.timer=new Timer();
		this.isTimerOn=false;

		try {
			this.model = new CoreWarGame(model.getNameFile1(), model.getNameFile2()); //Redéfini model pour initialiser la machine virtuelle
		}catch(Exception e){

			JOptionPane.showMessageDialog(null,e.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lancer = new JButton("Start");
		lancer.addActionListener(this);
		
		stop = new JButton("Quit");
		stop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(-1);
			}
		});
		
		this.setSize(new Dimension(1400,600));
		this.setLocationByPlatform(true);
		this.setResizable(false);
        
        setLayout(new BorderLayout());
        add(new MemoryView(this.model), BorderLayout.CENTER);
        JPanel bouton = new JPanel(new GridLayout(1,2));
        add(bouton, BorderLayout.SOUTH);
        bouton.add(lancer);
        bouton.add(stop);
    	pack();
    	setVisible(true);
		
	}


	/**
	 * Méthode pour lancer le jeu
	 * @param e L'événement cliquer sur le bouton lancer
	 */
	@Override
    public void actionPerformed(ActionEvent e) {

		if (!this.isTimerOn) {
			this.isTimerOn = true;
			try {
				timer.scheduleAtFixedRate(new TimerTask() {
					@Override
					public void run() {
						if (model.isTerminated()) {
							JOptionPane.showMessageDialog(null, "Le gagnant est "+model.winnner(false)+".");
							timer.cancel();
						} else {

							model.oneLoop(false);

						}
					}
				}, 0, 90);

			} catch (IllegalStateException ex) {

				ex.printStackTrace();
				System.exit(-1);
			}

		}
	}
}
