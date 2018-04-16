package enregistreur;

import java.util.ArrayList;
import java.util.List;

import commands.Command;

public class EnregistreurUndoRedo {

	List<MementoCommande> listUndo = new ArrayList<>();

	List<MementoCommande> listRedo = new ArrayList<>();



	public void addCommand(MementoCommande enr,boolean delete) {

		listUndo.add(enr);

		if(delete) {
			listRedo = new ArrayList<>();	
		}
	}

	public MementoCommande getUndo() {

		MementoCommande enr = listUndo.remove(listUndo.size()-1);

		listRedo.add(enr);

		return enr;
	}

	public MementoCommande getRedo() {

		MementoCommande enr = listRedo.remove(listRedo.size()-1);
		
		

		return enr;
	}


	public boolean UndoPossible()
	{
		return !listUndo.isEmpty();
	}

	public boolean RedoPossible() {
		return !listRedo.isEmpty();
	}
	

}
