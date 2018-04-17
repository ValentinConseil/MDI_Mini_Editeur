package enregistreur;

import controleur.Buffer;

public interface EnregistreurMacro {

	public void stopEnregistrement();

	public void startEnregistrement();

	public void rejouer(Buffer buffer);

	public boolean isEnregistrement_en_cours();

	public void setEnregistrement_en_cours(boolean enregistrement_en_cours);

	public void addEnregistrement(MementoCommande e);

}