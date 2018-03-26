package vue;

import controleur.Buffer;

public interface IHM {

	
	public int getDebutSelection();
	public int getFinSelection();
	public void update();
	public String getText();
	
	public void setBuffer(Buffer buffer);
}
