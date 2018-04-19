package commands;

import modele.Buffer;

public class Inserer implements Command {
	private Buffer buffer;

	public Inserer(Buffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public void exec() {
		buffer.inserer();
	}
}
