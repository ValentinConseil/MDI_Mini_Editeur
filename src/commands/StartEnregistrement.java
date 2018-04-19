package commands;

import controleur.Buffer;

public class StartEnregistrement extends CommandBuffer {

	public StartEnregistrement(Buffer buffer) {
		super(buffer);
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
