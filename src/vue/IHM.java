package vue;

import controleur.Buffer;
import enregistreur.Enregistreur;

public interface IHM {

	
	public int getDebutSelection();
	public int getFinSelection();
	public void update();
	public String getText();
	public void addMacros(int id);
	public void setBuffer(Buffer buffer);
	public String getCaractereInsere();
	public Enregistreur getEnregistreur();
	public int getPositionCurseur();
}
