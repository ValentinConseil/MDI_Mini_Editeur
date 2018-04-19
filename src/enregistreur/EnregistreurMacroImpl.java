package enregistreur;

import java.util.ArrayList;
import java.util.List;

import modele.Buffer;

/**
 * 
 * Caretaker des memento d'une macro
 *
 */
public class EnregistreurMacroImpl implements EnregistreurMacro {

	private List<MementoCommande> listEnregistrement = new ArrayList<>();
	private boolean enregistrement_en_cours = false;

	public void stopEnregistrement() {
		enregistrement_en_cours = false;
	}

	public void startEnregistrement() {
		enregistrement_en_cours = true;
	}

	/**
	 * Rejouer les commandes contenue dans la macro
	 * @param Buffer
	 */
	public void rejouer(Buffer buffer) {
		for (MementoCommande enregistrement : listEnregistrement) {
			buffer.setMemento(enregistrement);
		}
	}

	/**
	 * Indique si une macro est en cours d'enregistrement
	 * @return boolean
	 */
	public boolean isEnregistrement_en_cours() {
		return enregistrement_en_cours;
	}

	/**
	 * active / désactive l'enregistrement
	 * @param boolean activer
	 */
	public void setEnregistrement_en_cours(boolean enregistrement_en_cours) {
		this.enregistrement_en_cours = enregistrement_en_cours;
	}

	public void addEnregistrement(MementoCommande e) {
		this.listEnregistrement.add(e);
	}

}
