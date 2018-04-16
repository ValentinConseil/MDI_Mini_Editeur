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
	
	/**
	 * Permet de définir l'id de la macro à rejouer
	 * @param id
	 */
	public void setMacro(int id) {
		this.idMacro = id;
	}
	
	@Override
	public void exec() {
		this.buffer.rejouer(idMacro);
	}

	@Override
	public void unexec() {
		
	}
}
