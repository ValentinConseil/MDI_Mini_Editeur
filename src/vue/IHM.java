package vue;

import modele.Buffer;

public interface IHM {



	/**
	 * Met à jour l'ihm en fonction du buffer
	 */
	public void update();


	/**
	 * Configure le buffer et les commandes de l'ihm
	 * @param Buffer buffer
	 */
	public void setBuffer(Buffer buffer);

	
	/**
	 * Retourne l'index dans le texte du début du sélecteur
	 * @return int index
	 */
	public int getDebutSelection();

	/**
	 * Retourne l'index dans le texte de la fin du sélecteur
	 * @return int index
	 */
	public int getFinSelection();
	
	/**
	 * Retourne le dernier caractère inséré
	 * @return String un caractère
	 */
	public String getCaractereInsere();

	/**
	 * Permet de modifier la position du curseur sur l'ihm
	 * @param position
	 */
	public void setCursorPosition(int position);

}
