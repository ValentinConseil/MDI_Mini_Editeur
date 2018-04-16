package commands;

import controleur.Buffer;
import enregistreur.EnregistreurMacro;
import enregistreur.ListEnregistrementMacro;
import vue.IHM;

public class StopEnregistrement implements Command {


	private Buffer buffer;
	
	public StopEnregistrement(Buffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public void exec() {
		this.buffer.getListEnregistreurMacro().getMacro(buffer.getListEnregistreurMacro().getNbMacro()-1).stopEnregistrement();
	}

	@Override
	public void unexec() {
		// TODO Auto-generated method stub
		
	}
}
