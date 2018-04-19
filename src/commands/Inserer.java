package commands;

import controleur.Buffer;

public class Inserer extends CommandBuffer {

	public Inserer(Buffer buffer) {
		super(buffer);
	}

	@Override
	public void exec() {
		buffer.inserer();
	}

	@Override
	public void unexec() {
		this.buffer.deinserer();
	}

}
