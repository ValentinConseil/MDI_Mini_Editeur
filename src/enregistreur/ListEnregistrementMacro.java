package enregistreur;

import java.util.ArrayList;
import java.util.List;

import commands.Command;

public class ListEnregistrementMacro {

	private List<EnregistreurMacro> listEnregistreur = new ArrayList<EnregistreurMacro>();

	
	

	public void addEnregistrement(MementoCommande enr) {
		if(listEnregistreur.size()>0) {
			if(listEnregistreur.get(listEnregistreur.size()-1).isEnregistrement_en_cours()) {
				listEnregistreur.get(listEnregistreur.size()-1).addEnregistrement(enr);
			}
		}
	}


	public EnregistreurMacro getMacro(int id) {
		return listEnregistreur.get(id);
	}
	
	/**
	 * Retourne le nombre de macro
	 * @return
	 */
	public int getNbMacro() {
		return listEnregistreur.size();
	}


	public EnregistreurMacro newMacro() {
		listEnregistreur.add(new EnregistreurMacro());
		return listEnregistreur.get(listEnregistreur.size()-1);
	}


	public boolean isRecording() {
		if(listEnregistreur.isEmpty()) {
			return false;
		}
		return listEnregistreur.get(listEnregistreur.size()-1).isEnregistrement_en_cours();
	}
}
