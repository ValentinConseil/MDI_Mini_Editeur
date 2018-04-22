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

	@Override
	public void stopEnregistrement() {
		enregistrement_en_cours = false;
	}

	@Override
	public void startEnregistrement() {
		enregistrement_en_cours = true;
	}

	/**
	 * Rejouer les commandes contenue dans la macro
	 * @param Buffer
	 */
	@Override
	public void rejouer(Buffer buffer) {
		for (MementoCommande enregistrement : listEnregistrement) {
			buffer.setMemento(enregistrement);
		}
	}

	/**
	 * Indique si une macro est en cours d'enregistrement
	 * @return boolean
	 */
	@Override
	public boolean isEnregistrement_en_cours() {
		return enregistrement_en_cours;
	}

	/**
	 * active / désactive l'enregistrement
	 * @param boolean activer
	 */
	@Override
	public void setEnregistrement_en_cours(boolean enregistrement_en_cours) {
		this.enregistrement_en_cours = enregistrement_en_cours;
	}

	@Override
	public void addEnregistrement(MementoCommande e) {
		this.listEnregistrement.add(e);
	}

}
