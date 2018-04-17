package commands;

import controleur.Buffer;

public class Coller implements Command {
	private Buffer buffer;

	public Coller(Buffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public void exec() {
		buffer.coller();
	}

	@Override
	public void unexec() {
		this.buffer.decoller();
	}

}
