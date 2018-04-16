package commands;

import controleur.Buffer;

public class Inserer implements Command{
	private Buffer buffer;

	public Inserer(Buffer buffer) {
		this.buffer=buffer;
	}
	@Override
	public void exec() {
		buffer.inserer();
	}

	@Override
	public void unexec() {
		this.buffer.deinserer();
	}

	
}
