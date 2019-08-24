package gui;

import game.player.Humain;
import game.player.Player;
import util.ModelListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

import game.*;

/**
 * Classe VueGrille, qui st la représentation des grilles des joueurs,
 * des coups joués et des bateaux
 */
public class VueGrille extends JPanel implements ModelListener, MouseListener {

	/**
	 * Attribut statique qui définit le coefficient entre
	 * les coordonnées de la fenêtre et de la grille
	 */
	private static int COEFF=50;
	
	/**
	 * Attribut grillePrincipale, qui est la grille
	 * du joueur qui attaque
	 */
    private Mer grillePrincipale;
	
	/**
	 * Attribut grilleAdversaire, qui est la grille
	 * du joueur qui prend un coup
	 */
    private Mer grilleAdversaire;
	
	/**
	 * Constructeur de la classe
	 * @param grilleP La grille principale
	 * @param grilleA La grille adversaire
	 */
    public VueGrille(Mer grilleP,Mer grilleA) {
    	super();
		this.grillePrincipale=grilleP;
		this.grilleAdversaire=grilleA;

		this.addMouseListener(this);

		this.grillePrincipale.addListener(this);

		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

	}

	/**
	 * Méthode qui est appelé lorsque l'utilisateur clique sur une case du jeu
	 * @param e Le clic de souris capté
	 */
	@Override
	public void mouseClicked(MouseEvent e){

    	//Si c'est la grille sur laquelle le joueur humain doit cliquer
    	if(this.grillePrincipale.getAttaquant() instanceof Humain){

    		Humain joueur=(Humain)this.grillePrincipale.getAttaquant();

    		//En divisant par le coefficient, on retombe sur des coordonnées entre 0 et 10
    		int x=e.getY()/VueGrille.COEFF;
    		int y=e.getX()/VueGrille.COEFF;

			List<Integer> coords=new ArrayList<>(Arrays.asList(x,y));
			if (joueur.coupValide(coords)) {

				//On appelle le moteur de jeu interne pour l'humain
				this.moteurJeuInterne(coords, joueur, this.grillePrincipale, e);

				Player IA = this.grilleAdversaire.getAttaquant();
				List<Integer> coupIA = IA.jouerCoup();

				//On appelle le moteur de jeu interne pour l'IA
				this.moteurJeuInterne(coupIA, IA, this.grilleAdversaire, e);

			}
		}

	}

	/**
	 * Moteur de jeu interne, car on ne sait pas appeler la
	 * méthode utilisée par bataille navale. Nous sommes obligé
	 * de récupérer le coup hors de ce moteur de jeu car l'IA ne
	 * doit jouer après l'humain que si le coup de l'humain est
	 * valide
	 * @param coup Le coup du joueur
	 * @param joueur Le joueur
	 * @param grille La grille sur laquelle le joueur attaque
	 * @param e L'événement souris, pour récupérer si besoin une
	 * référence vers la fenêtre
	 */
	private void moteurJeuInterne(List<Integer> coup, Player joueur, Mer grille,MouseEvent e){

		//Très similaire au moteur de jeu de bataille navale
    	joueur.getCoupsJouer().add(coup);

    	Player adversaire=grille.getProprietaire();

    	if(grille.envoyerCoup(coup)){

    		Bateau b=grille.getBateau(coup.get(0),coup.get(1));
    		b.estTouche();

    		if(b.estCoule()){

    			JOptionPane.showMessageDialog(null,"Un bateau de "+adversaire.getName()+" a coulé!");

    			adversaire.getBateauxActuels().remove(b);
    			adversaire.getBateauxCoules().add(b);

    			//Si fin du jeu
    			if(adversaire.getBateauxActuels().isEmpty()){

    				JOptionPane.showMessageDialog(null,"Le gagnant est "+joueur.getName()+" !");
					JFrame master=(JFrame)SwingUtilities.getRoot(e.getComponent());
					master.dispose();
				}
			}

		}


	}

	@Override
	public void mousePressed(MouseEvent e){}

	@Override
	public void mouseReleased(MouseEvent e){}

	@Override
	public void mouseEntered(MouseEvent e){}

	@Override
	public void mouseExited(MouseEvent e){}
    

	/**
	 * Méthode appelé pour mettre à jour l'affichage
	 * @param source La grille à redessiner
	 */
    @Override
    public void modelUpdated(Object source){
    	this.repaint();
    }
    
	/**
	 * Méthode qui dessine les différents composants des grilles
	 * @param g Objet de Swing servant à dessiner sur la fenêtre
	 */
    @Override
    public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int i = COEFF; i < 500; i += COEFF) { //Dessine les lignes de la grille
			g.drawLine(i, 0, i, 500);
			g.drawLine(0, i, 500, i);
		}

		for (int j = 0; j < 10; j++) { //Dessine les tire dans la grille, Rouge pour un bateau touché, Vert pour un raté.
			for (int k = 0; k < 10; k++) {
				List<Integer> coords = new ArrayList<>(Arrays.asList(j, k));
				if (grillePrincipale.getAttaquant().getCoupsJouer().contains(coords)) {
					g.drawOval(k * COEFF + COEFF / 4, j * COEFF + COEFF / 4, COEFF / 2, COEFF / 2);
					if (grillePrincipale.estTouche(coords)) {
						g.setColor(Color.RED);
						g.fillOval(k * COEFF + COEFF / 4, j * COEFF + COEFF / 4, COEFF / 2, COEFF / 2);
					} else {
						g.setColor(Color.GREEN);
						g.fillOval(k * COEFF + COEFF / 4, j * COEFF + COEFF / 4, COEFF / 2, COEFF / 2);
					}

					g.setColor(Color.BLACK);
				}
			}
		}

		if (grillePrincipale.getProprietaire().getClass() == Humain.class) {
			for (int i = 0; i < grillePrincipale.getProprietaire().getBateauxActuels().size(); i++) {
				Bateau b = grillePrincipale.getProprietaire().getBateauxActuels().get(i);
				g.setColor(Color.BLACK);
				if (b.getDirection() == 1) {
					g.drawOval((b.getY()*COEFF) + (COEFF / 4), (b.getX()*COEFF) + (COEFF / 4), b.getTaille() * COEFF, COEFF / 2);
				} else {
					g.drawOval((b.getY()*COEFF) + (COEFF / 4), (b.getX()*COEFF) + (COEFF / 4), COEFF / 2, b.getTaille() * COEFF);
				}
		}
		} else {
			
			for (int j = 0; j < grillePrincipale.getProprietaire().getBateauxCoules().size(); j++) {
				Bateau b = grillePrincipale.getProprietaire().getBateauxCoules().get(j);
				g.setColor(Color.BLACK);
				if (b.getDirection() == 1) {
					g.drawOval((b.getY()*COEFF) + (COEFF / 4), (b.getX()*COEFF) + (COEFF / 4), b.getTaille() * COEFF, COEFF / 2);
				} else {
					g.drawOval((b.getY()*COEFF) + (COEFF / 4), (b.getX()*COEFF) + (COEFF / 4), COEFF / 2, b.getTaille() * COEFF);
				}
			}
		}
	}
}