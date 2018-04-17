package enregistreur;

public interface EnregistreurUndoRedo {

	public void addCommand(MementoCommande enr, boolean delete);

	public MementoCommande getUndo();

	public MementoCommande getRedo();

	public boolean UndoPossible();

	public boolean RedoPossible();

}
