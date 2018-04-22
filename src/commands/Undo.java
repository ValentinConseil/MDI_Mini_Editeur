package commands;

import modele.Buffer;

public class Undo extends CommandBuffer {

	public Undo(Buffer buffer) {
		super(buffer);
	}

	@Override
	public void exec() {
		buffer.undo();
	}

	@Override
	public void unexec() {
		
	}

}
