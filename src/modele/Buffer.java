package modele;

import commands.Command;
import enregistreur.EnregistreurMacro;
import enregistreur.ListEnregistrementMacro;
import enregistreur.MementoCommande;

public interface Buffer {
	
	// Fonction appelées par les commandes

	public void copier();

	public void couper();

	public void coller();

	public void inserer();

	public void selection();

	public void rejouer();
	
	public void supprimer();

	//Inverse des fonction des commandes
	

	public void decoller();

	public void decouper();

	public void deinserer();

	public void decopier();
	
	public void desupprimer();

	/**
	 * Retourne le texte du buffer
	 * @return
	 */
	public String getText();

	/**
	 * Créer un memento d'une commande
	 * @param commande
	 * @param text
	 * @return
	 */
	public MementoCommande createMemento(Command commande, String text);

	/**
	 * Récupère l'état contenu dans le mémento et l'affecte au buffer
	 * @param enr
	 */
	public void setMemento(MementoCommande enr);

	/**
	 * Undo la dernière commande
	 */
	public void undo();

	/**
	 * Redo la dernière commande Undo
	 */
	public void redo();

	/**
	 * Ajoute une nouvelle macro dans la liste
	 * @return
	 */
	public EnregistreurMacro newMacro();

	/**
	 *  
	 * @return Retourne le nombre de macro dans le buffer
	 */
	public int getNbMacro();
	
	/**
	 * Permet à l'ihm de définir l'interface sélectionnée
	 * @param int selectedIndex
	 */
	public void setSelectedMacro(int selectedIndex);

	/**
	 * Retourne la macro désignée par l'index i
	 * @param i
	 * @return
	 */
	public EnregistreurMacro getMacro(int i);

}
