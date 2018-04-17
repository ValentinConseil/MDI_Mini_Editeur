package commands;

import controleur.Buffer;

public class StartEnregistrement implements Command {

	private Buffer buffer;

	public StartEnregistrement(Buffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public void exec() {
		this.buffer.getListEnregistreurMacro().getMacro(buffer.getListEnregistreurMacro().getNbMacro() - 1)
				.startEnregistrement();
	}

	@Override
	public void unexec() {
		// TODO Auto-generated method stub

	}
}
