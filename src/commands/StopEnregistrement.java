package commands;

import enregistreur.Enregistreur;
import vue.IHM;

public class StopEnregistrement implements Command {

	Enregistreur enregistreur;
	private IHM ihm;
	
	public StopEnregistrement(IHM ihm) {
		this.ihm = ihm;
	}

	@Override
	public void exec() {
		this.enregistreur = ihm.getEnregistreur();
		this.enregistreur.stopEnregistrement();
		
	}
}
