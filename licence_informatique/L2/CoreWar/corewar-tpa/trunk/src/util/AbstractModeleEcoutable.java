package util;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstraite AbstractModeleEcoutable, qui permet de définir un modèle pour l'interface
 * graphique. Classe issue des cours de Complément POO.
 */
public abstract class AbstractModeleEcoutable {

	/**
	 * Liste des objets qui écoutent le modèle
	 */
	private List<EcouteurModele> ecouteurs;

	/**
	 * Constructeur standard
	 */
	public AbstractModeleEcoutable() {
		
		this.ecouteurs=new ArrayList<>();
		
	}

	/**
	 * Ajout d'un écouteur
	 * @param e L'objet qui veut écouter ce modèle
	 */
	public void ajoutEcouteur(EcouteurModele e) {
		
		this.ecouteurs.add(e);
		e.modeleMisAJour(this);
	}

	/**
	 * Retrait d'un écouteur
	 * @param e L'écouteur à retirer
	 */
	public void retraitEcouteur(EcouteurModele e) {
		
		this.ecouteurs.remove(e);
		
	}

	/**
	 * Signifier d'un changement aux écouteurs en leur demandant de se mettre à jour
	 */
	protected void fireChangement() {

		for(EcouteurModele e: ecouteurs) {
			
			e.modeleMisAJour(this);
		}
		
	}

}
