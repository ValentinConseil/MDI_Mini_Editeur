package commands;

import controleur.Buffer;

public class Copier implements Command {
	private Buffer buffer;

	public Copier(Buffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public void exec() {
		buffer.copier();
	}

	@Override
	public void unexec() {
		this.buffer.decopier();
	}

}
