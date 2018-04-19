package commands;

import modele.Buffer;

public class Coller implements Command {
	private Buffer buffer;

	public Coller(Buffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public void exec() {
		buffer.coller();
	}

}
