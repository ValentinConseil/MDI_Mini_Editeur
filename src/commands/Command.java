package commands;

import controleur.Buffer;

public interface Command {
	
	public void exec();
	public void unexec();
}
