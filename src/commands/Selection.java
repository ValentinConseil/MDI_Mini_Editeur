package commands;

import controleur.Buffer;

public class Selection implements Command {
	private Buffer buffer;

	public Selection(Buffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public void exec() {
		buffer.selection();
	}
}
