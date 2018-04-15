package controleur;

import java.util.ArrayList;
import java.util.List;

import commands.Coller;
import commands.Command;
import commands.Copier;
import commands.Couper;
import commands.Inserer;
import commands.Selection;
import enregistreur.Enregistrement;
import enregistreur.Enregistreur;
import vue.IHM;

public class Buffer{


	private PressePapier pressePapier = new PressePapierImpl();
	private Selecteur selecteur = new SelecteurImpl(this);
	private List<Enregistreur> listEnregistreur = new ArrayList<Enregistreur>();
	private int selectedMacro = -1;
	private IHM ihm;

	private String content = "";

	//
	private boolean rejoue = false;
	private String textEnregistrement = "";
	private int positionCurseur = -1;

	
	public void copier(){

		System.out.println("copier "+ selecteur.lire());

		pressePapier.ecrire(selecteur.lire());
		if(!rejoue) {
			addEnregistrement(new Copier(this),"");
		}
	}
	public void couper(){

		System.out.println("couper "+ selecteur.lire());


		pressePapier.ecrire(selecteur.lire());
		content = content.substring(0,selecteur.getDebut())+content.substring(selecteur.getFin(),content.length());

		if(!rejoue) {
			addEnregistrement(new Couper(this),"");
		}
		System.out.println("content "+content);

		ihm.update();
	}
	public void coller(){

		System.out.println("coller "+ pressePapier.lire());


		content = content.substring(0,selecteur.getDebut())+pressePapier.lire()+content.substring(selecteur.getDebut(),content.length());

		//ihm doit le faire
		if(!rejoue) {
			addEnregistrement(new Coller(this),"");
		}
		ihm.update();
	}
	public void inserer(){

		String character = "";
		int position = 0;
		System.out.println("inserer"+ textEnregistrement+ " "+positionCurseur);

		if(rejoue) {
			position = positionCurseur;
			character = textEnregistrement;
		}
		else {
			position = ihm.getPositionCurseur()-1;
			character = ihm.getCaractereInsere();
		}


		int code = (int) character.charAt(0);

		if(code == 8) {
				content = removeCharacter(content,position+1);
		}
		else {
			content = insertString(character,content,position);
		}


		if(!rejoue) {
			addEnregistrement(new Inserer(this),character);
		}
		ihm.update();
		
	}

	private String removeCharacter(String content, int i) {
		StringBuilder sb = new StringBuilder(content).deleteCharAt(i);
		return sb.toString();
	}
	public String insertString(String i,String content,int index)
	{
		StringBuilder sb = new StringBuilder(content).insert(index, i);
		return sb.toString();
	}
	public void selection(){
		selecteur.setDebut(ihm.getDebutSelection());
		selecteur.setFin(ihm.getFinSelection());

		addEnregistrement(new Selection(this),"");

	}

	public void rejouer() {
		listEnregistreur.get(selectedMacro).rejouer(this);
	}

	public void setSelectedMacro(int selectedMacro) {
		this.selectedMacro = selectedMacro;
	}

	public Buffer(IHM ihm) {
		this.ihm=ihm;
	}

	public Enregistreur getEnregistreur(int id){
		return listEnregistreur.get(id);
	}

	public String getText() {
		return content;
	}
	public Enregistreur addEnregistreur() {
		Enregistreur enregistreur = new Enregistreur();
		listEnregistreur.add(enregistreur);
		return enregistreur;
	}

	public Enregistrement createMemento(Command commande, String text) {
		Enregistrement enr = new Enregistrement();
		enr.setDebutSelection(selecteur.getDebut());
		enr.setFinSelection(selecteur.getFin());
		enr.setCommande(commande);
		enr.setText(text);
		enr.setCurseurPosition(ihm.getPositionCurseur()-1);

		return enr;

	}

	private void addEnregistrement(Command commande, String text) {
		if(listEnregistreur.size()>0) {
			if(listEnregistreur.get(listEnregistreur.size()-1).isEnregistrement_en_cours()) {
				listEnregistreur.get(listEnregistreur.size()-1).addEnregistrement(createMemento(commande,text));
			}
		}
	}


	public void setMemento(Enregistrement enr) {

		rejoue = true;

		this.positionCurseur = enr.getCurseurPosition();
		this.textEnregistrement = enr.getText();
		selecteur.setDebut(enr.getDebutSelection());
		selecteur.setFin(enr.getFinSelection());
		enr.getCommande().exec();

		rejoue = false;
	}
}
