package commands;

import controleur.Buffer;

public class Coller extends CommandBuffer {

	public Coller(Buffer buffer) {
		super(buffer);
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
