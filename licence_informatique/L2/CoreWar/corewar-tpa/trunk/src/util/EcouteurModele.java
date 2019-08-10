package util;

/**
 * Interface EcouteurModele, pout spécifier quelle classe peut écouter un modèle
 */
public interface EcouteurModele {

	/**
	 * Méthode pour se mettre à jour
	 * @param src La source, si besoin
	 */
	void modeleMisAJour(Object src);

}
