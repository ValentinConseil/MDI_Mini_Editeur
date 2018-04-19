package commands;

import modele.Buffer;

public class Copier extends CommandBuffer {

	public Copier(Buffer buffer) {
		super(buffer);
	}

	@Override
	public void exec() {
		buffer.copier();
	}

	@Override
	public void unexec() {
		buffer.decopier();
	}

}
