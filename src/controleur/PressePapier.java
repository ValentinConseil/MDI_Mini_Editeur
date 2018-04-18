package controleur;

/**
 * Presse papier utilisé pour les copier coller
 *
 */
public interface PressePapier {

	/**
	 * Retourne le contenu du presse papier
	 * 
	 * @return String
	 */
	public String lire();

	/**
	 * Défini le contenu du presse papier
	 * @param String
	 */
	public void ecrire(String e);

}
