package commands;

import modele.Buffer;

public class Redo extends CommandBuffer {

	public Redo(Buffer buffer) {
		super(buffer);
	}

	@Override
	public void exec() {
		buffer.redo();
	}

	@Override
	public void unexec() {
		// TODO Auto-generated method stub
		
	}


}
