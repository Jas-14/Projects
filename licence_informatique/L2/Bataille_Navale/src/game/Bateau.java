package game;

public class Bateau 
{
	/**
	 * Attribut statique pour la direction horizontale
	 */
    public final static int DIRH=1;
	/**
	 * Attribut statique pour la direction verticale
	 */
	public final static int DIRV=0;
	/**
	 * la taille d'un bateau (nombre de cases qu'il occupera)
	 */
	private int taille;

	/**
	 * Attribut qui représente les points de vie restant du bateau
	 */
	private int pv;

	private int x;
	private int y;

	/**
	 * Attribut qui represente la direction d'un bateau (horizontale ou verticale)
	 * Convention: 1=horizontale,  0=vertical
	 */
	private int direction;

	/**
	 * Un constructeur pour initialiser les attributs
	 * @param x 	abscisse
	 * @param y		ordonnee
	 * @param taille La taille du bateau (en case)
	 * @param direction La direction du bateau
	 */
	public Bateau(int x,int y,int taille, int direction)
	{
		this.x=x;
		this.y=y;
		this.taille=taille;
		this.direction=direction;

		this.pv=taille;
	}
/**
 * methode qui retourne l'abscisse d'un bateau
 * @return l'abscisse du bateau
 */
	public int getX() {
		return x;
	}
/**
 * methode qui retourne l'ordonnee d'un bateau
 * @return l'ordonnée du bateau
 */
	public int getY() {
		return y;
	}
/**
 * methode qui diminue le point de vie d'un bateau quand il est touche
 */
	public void estTouche()
	{
		this.pv-=1;
	}
	/**
	 * methode qui teste si un bateau est coule (quand le bateau
	 * n'a plus de point de vie)
	 * @return Un booléen qui indique si le bateau est coulé ou non
	 */
	public boolean estCoule()
	{
		return this.pv==0;
	}

/**
 * methode qui retourne la direction d'un bateau
 * @return La direction du bateau
 */
	public int getDirection() {
		return direction;

	}
/**
 * methode qui retourne la taille d'un bateau
 * @return La taille du bateau
 */
	public int getTaille()
	{
		return this.taille;
	}
}