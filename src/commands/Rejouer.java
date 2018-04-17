package commands;

import controleur.Buffer;

public class Rejouer implements Command {

	private Buffer buffer;

	public Rejouer(Buffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public void exec() {
		this.buffer.rejouer();
	}

	@Override
	public void unexec() {

	}
}
