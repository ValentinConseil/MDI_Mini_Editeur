package commands;

import controleur.Buffer;
import enregistreur.Enregistrable;

public interface Command extends Enregistrable{
	
	public void exec();
}
