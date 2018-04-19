package commands;

import modele.Buffer;

public class Supprimer extends CommandBuffer {

	public Supprimer(Buffer buffer) {
		super(buffer);
	}

	@Override
	public void exec() {
		this.buffer.supprimer();
	}

	@Override
	public void unexec() {
		this.buffer.desupprimer();
	}

}
