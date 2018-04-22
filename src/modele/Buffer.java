package modele;

public interface Buffer {
	
	// Fonction appelées par les commandes

	public void copier();

	public void couper();

	public void coller();

	public void inserer();

	public void selection();
	
	public void supprimer();

	//Inverse des fonction des commandes

	/**
	 * Retourne le texte du buffer
	 * @return
	 */
	public String getText();

}
