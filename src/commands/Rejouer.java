package commands;

import controleur.Buffer;
import enregistreur.Enregistreur;
import vue.IHM;

public class Rejouer implements Command {
	
	private Enregistreur enregistreur;
	private IHM ihm;
	private Buffer buffer;

	
	
	public Rejouer(Buffer buffer){
		this.buffer=buffer;
	}
	
	@Override
	public void exec() {
		this.buffer.rejouer();
	}
}
