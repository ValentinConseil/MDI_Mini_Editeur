package controleur;

import commands.Command;
import enregistreur.EnregistreurMacro;
import enregistreur.ListEnregistrementMacro;
import enregistreur.MementoCommande;

public interface Buffer {
	// Fonction appelées par les commandes

	public void copier();

	public void couper();

	public void coller();

	public void inserer();

	public void selection();

	public void rejouer();

	public String getText();

	public MementoCommande createMemento(Command commande, String text);

	public void setMemento(MementoCommande enr);

	public void undo();

	public void redo();

	public void decoller();

	public void decouper();

	public void deinserer();

	public void decopier();

	public EnregistreurMacro newMacro();

	public ListEnregistrementMacro getListEnregistreurMacro();

	public void supprimer();

	public void desupprimer();

	public void setSelectedMacro(int selectedIndex);

}
