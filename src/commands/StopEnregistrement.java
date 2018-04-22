package commands;

import modele.Buffer;

public class StopEnregistrement extends CommandBuffer {

	public StopEnregistrement(Buffer buffer) {
		super(buffer);
	}
	
	@Override
	public void exec() {
		this.buffer.getMacro(buffer.getNbMacro() - 1)
				.stopEnregistrement();
	}

	@Override
	public void unexec() {
		// TODO Auto-generated method stub

	}
}
