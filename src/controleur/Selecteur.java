package controleur;

import modele.Buffer;

/**
 * Selecteur de l'interface
 *
 */
public interface Selecteur {
	
	/**
	 * Défini le début de la sélection
	 * @param debut
	 */
	public void setDebut(int debut);


	/**
	 * Définis la fin de la sélection
	 * @param fin
	 */
	public void setFin(int fin);

	/**
	 * Retourne le contenu du buffer situé dans la sélection
	 * @return String
	 */
	public String lire();

	/**
	 * Défini le buffer associé
	 * @param buffer
	 */
	public void setBuffer(Buffer buffer);

	public int getDebut();

	public int getFin();
}
