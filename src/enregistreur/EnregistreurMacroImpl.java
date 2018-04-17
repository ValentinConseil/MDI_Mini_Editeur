package enregistreur;

import java.util.ArrayList;
import java.util.List;

import controleur.Buffer;

public class EnregistreurMacroImpl implements EnregistreurMacro {

	private List<MementoCommande> listEnregistrement = new ArrayList<>();
	private boolean enregistrement_en_cours = false;

	public void stopEnregistrement() {
		enregistrement_en_cours = false;
	}

	public void startEnregistrement() {
		enregistrement_en_cours = true;
	}

	public void rejouer(Buffer buffer) {
		for (MementoCommande enregistrement : listEnregistrement) {
			// ihm doit le faire
			buffer.setMemento(enregistrement);

		}
	}

	public boolean isEnregistrement_en_cours() {
		return enregistrement_en_cours;
	}

	public void setEnregistrement_en_cours(boolean enregistrement_en_cours) {
		this.enregistrement_en_cours = enregistrement_en_cours;
	}

	public void addEnregistrement(MementoCommande e) {
		this.listEnregistrement.add(e);
	}

}
