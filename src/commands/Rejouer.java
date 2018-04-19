package commands;

import modele.Buffer;

public class Rejouer extends CommandBuffer {

	public Rejouer(Buffer buffer) {
		super(buffer);
	}

	@Override
	public void exec() {
		this.buffer.rejouer();
	}

	@Override
	public void unexec() {

	}
}
