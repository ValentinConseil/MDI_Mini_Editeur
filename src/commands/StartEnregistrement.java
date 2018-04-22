package commands;

import modele.Buffer;

public class StartEnregistrement extends CommandBuffer {

	public StartEnregistrement(Buffer buffer) {
		super(buffer);
	}

	@Override
	public void exec() {
		this.buffer.newMacro();
		this.buffer.getMacro(buffer.getNbMacro() - 1)
				.startEnregistrement();
	}

	@Override
	public void unexec() {
		// TODO Auto-generated method stub

	}
}
