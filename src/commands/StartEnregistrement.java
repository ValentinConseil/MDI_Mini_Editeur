package commands;

import controleur.Buffer;
import enregistreur.EnregistreurMacro;
import enregistreur.ListEnregistrementMacro;
import vue.IHM;

public class StartEnregistrement implements Command {

	private Buffer buffer;
	
	public StartEnregistrement(Buffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public void exec() {
		this.buffer.getListEnregistreurMacro().getMacro(buffer.getListEnregistreurMacro().getNbMacro()-1).startEnregistrement();
	}

	@Override
	public void unexec() {
		// TODO Auto-generated method stub
		
	}
}
