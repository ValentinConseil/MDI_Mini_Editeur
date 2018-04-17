package vue;

import controleur.Buffer;
import enregistreur.EnregistreurMacro;

public interface IHM {

	
	public int getDebutSelection();
	public int getFinSelection();
	public void update();
	public String getText();
	public void setBuffer(Buffer buffer);
	public String getCaractereInsere();
	public void setCursorPosition(int position);
}
