package commands;

import controleur.Buffer;
import enregistreur.Enregistreur;
import vue.IHM;

public class StartEnregistrement implements Command {

	Enregistreur enregistreur;
	private IHM ihm;
	
	public StartEnregistrement(IHM ihm) {
		this.ihm = ihm;
	}

	@Override
	public void exec() {
		this.enregistreur = ihm.getEnregistreur();
		this.enregistreur.startEnregistrement();
	}
}
