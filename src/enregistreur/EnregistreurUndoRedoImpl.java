package enregistreur;

import java.util.ArrayList;
import java.util.List;

/**
 * Caretaker des memento undo/redo
 *
 */
public class EnregistreurUndoRedoImpl implements EnregistreurUndoRedo {

	//Liste des commandes exécutées
	private List<MementoCommande> listUndo = new ArrayList<>();

	//Liste des commandes "deexécutées"
	private List<MementoCommande> listRedo = new ArrayList<>();

	/**
	 * Ajoute une commande dans l'historique undo
	 * @param MementoCommande
	 * @param boolean Supprimer les redo 
	 */
	public void addCommand(MementoCommande enr, boolean delete) {

		listUndo.add(enr);

		//Si ajout d'une nouvelle commande --> effacement des redo
		if (delete) {
			listRedo = new ArrayList<>();
		}
	}

	/**
	 * Retourne le memento le plus récent des undo
 	 * @return MementoCommande
	 */
	public MementoCommande getUndo() {

		MementoCommande enr = listUndo.remove(listUndo.size() - 1);

		listRedo.add(enr);

		return enr;
	}

	/**
	 * Retourne le memento 
	 * @return MementoCommande
	 */
	public MementoCommande getRedo() {

		MementoCommande enr = listRedo.remove(listRedo.size() - 1);

		return enr;
	}

	/**
	 * Indique si la liste contient une commande dans l'historique
	 * @return boolean 
	 */
	public boolean UndoPossible() {
		return !listUndo.isEmpty();
	}
	/**
	 * Indique si la liste contient une commande dans l'historique
	 * @return boolean 
	 */
	public boolean RedoPossible() {
		return !listRedo.isEmpty();
	}

}
