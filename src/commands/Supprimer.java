package commands;

import controleur.Buffer;

public class Supprimer implements Command{

	
	private Buffer buffer;
	
	public Supprimer(Buffer buffer) {
		this.buffer = buffer;
	}
	
	@Override
	public void exec() {
		this.buffer.supprimer();
	}

	@Override
	public void unexec() {
		this.buffer.desupprimer();
	}

}
