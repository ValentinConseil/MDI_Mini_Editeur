package commands;

import enregistreur.Enregistreur;
import vue.IHM;

public class Rejouer implements Command {
	private Enregistreur enregistreur;
	private IHM ihm;
	
	public Rejouer(IHM ihm){
		this.ihm=ihm;
	}
	
	@Override
	public void exec() {
		this.enregistreur = ihm.getEnregistreur();
		this.enregistreur.rejouer();
	}
}
