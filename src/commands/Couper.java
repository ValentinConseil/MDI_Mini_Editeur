package commands;

import controleur.Buffer;

public class Couper extends CommandBuffer {

	public Couper(Buffer buffer) {
		super(buffer);
	}

	@Override
	public void exec() {
		buffer.couper();
	}

	@Override
	public void unexec() {
		this.buffer.decouper();
	}

}
