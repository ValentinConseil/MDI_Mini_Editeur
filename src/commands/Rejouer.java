package commands;

import controleur.Buffer;
import enregistreur.EnregistreurMacro;
import vue.IHM;

public class Rejouer implements Command {

	private Buffer buffer;
	private int idMacro;
	
	
	public Rejouer(Buffer buffer){
		this.buffer=buffer;
	}
	
	@Override
	public void exec() {
		this.buffer.rejouer();
	}

	@Override
	public void unexec() {
		
	}
}
