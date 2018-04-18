package enregistreur;

import java.util.ArrayList;
import java.util.List;


/**
 * Caretaker du pattern Memento pour les macros
 * 
 *
 */

public class ListEnregistrementMacroImpl implements ListEnregistrementMacro {

	private List<EnregistreurMacro> listEnregistreur = new ArrayList<EnregistreurMacro>();

	
	/**
	 * Ajoute un memento à la dernière macro
	 * @param MementoCommande enregistrement
	 */
	public void addEnregistrement(MementoCommande enr) {
		if (listEnregistreur.size() > 0) {
			if (listEnregistreur.get(listEnregistreur.size() - 1).isEnregistrement_en_cours()) {
				listEnregistreur.get(listEnregistreur.size() - 1).addEnregistrement(enr);
			}
		}
	}

	
	/**
	 * Retourne la macro 
	 * @param int idMacro
	 */
	public EnregistreurMacro getMacro(int idMacro) {
		return listEnregistreur.get(idMacro);
	}

	/**
	 * Retourne le nombre de macro
	 * 
	 * @return int nombre de macro
	 */
	public int getNbMacro() {
		return listEnregistreur.size();
	}

	
	/**
	 * Créer une nouvelle macro
	 * 
	 */
	public EnregistreurMacro newMacro() {
		listEnregistreur.add(new EnregistreurMacroImpl());
		return listEnregistreur.get(listEnregistreur.size() - 1);
	}

	/**
	 * Indique si la dernière macro est en cours d'enregistrement
	 * @boolean recording?
	 */
	public boolean isRecording() {
		if (listEnregistreur.isEmpty()) {
			return false;
		}
		return listEnregistreur.get(listEnregistreur.size() - 1).isEnregistrement_en_cours();
	}
}
