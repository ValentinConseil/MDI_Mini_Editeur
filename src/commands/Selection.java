package commands;

import modele.Buffer;

public class Selection extends CommandBuffer {

	public Selection(Buffer buffer) {
		super(buffer);
	}

	@Override
	public void exec() {
		buffer.selection();
	}

	@Override
	public void unexec() {

	}

}
