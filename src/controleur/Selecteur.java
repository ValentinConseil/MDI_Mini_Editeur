package controleur;

public interface Selecteur {
	public void setDebut(int debut);

	public void setFin(int fin);

	public String lire();

	public void setBuffer(Buffer buffer);

	public int getDebut();

	public int getFin();
}
