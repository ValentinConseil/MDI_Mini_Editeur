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

	public void setBuffer(Buffer buffer) {
		this.buffer=buffer;
	}

}
