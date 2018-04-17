package enregistreur;

public interface ListEnregistrementMacro {

	public void addEnregistrement(MementoCommande enr);

	public EnregistreurMacro getMacro(int id);

	public int getNbMacro();

	public EnregistreurMacro newMacro();

	public boolean isRecording();
}
