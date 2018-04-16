package controleur;

import java.util.ArrayList;
import java.util.List;

import commands.Coller;
import commands.Command;
import commands.Copier;
import commands.Couper;
import commands.Inserer;
import commands.Selection;
import commands.Supprimer;
import enregistreur.MementoCommande;
import enregistreur.EnregistreurMacro;
import enregistreur.EnregistreurUndoRedo;
import enregistreur.ListEnregistrementMacro;
import vue.IHM;

public class Buffer{


	private PressePapier pressePapier = new PressePapierImpl();
	private Selecteur selecteur = new SelecteurImpl(this);
	private IHM ihm;

	//Texte du buffer
	private String content = "";


	//Sauvegarde des macros
	ListEnregistrementMacro listMacro = new ListEnregistrementMacro();

	//Sauvegarde des Undo/Redo
	private EnregistreurUndoRedo enregistreurUndoRedo = new EnregistreurUndoRedo();

	//Memento en cours d'exécution
	private MementoCommande enregistrementCourant;

	//Flag indiquant si la la commande en cours est rejoué
	private boolean rejoue = false;	


	public Buffer(IHM ihm) {
		this.ihm=ihm;
	}


	//Fonction appelées par les commandes

	public void copier(){

		pressePapier.ecrire(selecteur.lire());

		if(listMacro.isRecording()) {
			listMacro.addEnregistrement(createMemento(new Copier(this),""));
		}

		enregistreurUndoRedo.addCommand(createMemento(new Copier(this),""),!rejoue);

	}
	public void couper(){

		System.out.println("couper "+ selecteur.lire());


		pressePapier.ecrire(selecteur.lire());
		content = content.substring(0,selecteur.getDebut())+content.substring(selecteur.getFin(),content.length());

		if(listMacro.isRecording()) {
			listMacro.addEnregistrement(createMemento(new Couper(this),""));
		}

		enregistreurUndoRedo.addCommand(createMemento(new Couper(this),""),!rejoue);

		ihm.update();
	}
	public void coller(){

		System.out.println("coller "+ pressePapier.lire());


		content = content.substring(0,selecteur.getDebut())+pressePapier.lire()+content.substring(selecteur.getDebut(),content.length());

		//ihm doit le faire
		if(listMacro.isRecording()) {
			listMacro.addEnregistrement(createMemento(new Coller(this),""));
		}

		enregistreurUndoRedo.addCommand(createMemento(new Coller(this),""),!rejoue);

		ihm.update();
	}
	public void inserer(){

		int position = ihm.getPositionCurseur();
		String character = ihm.getCaractereInsere();

		if(rejoue) {
			position = enregistrementCourant.getCurseurPosition();
			character = enregistrementCourant.getText();
		}

		System.out.println("Insert "+character+" at "+position+" content = "+content);
		content = new StringBuilder(content).insert(position, character).toString();

		//Si on enregistre une macro -> on l'ajoute à l'enregistrement
		if(listMacro.isRecording()) {
			listMacro.addEnregistrement(createMemento(new Inserer(this),character));
		}

		//On l'ajoute à l'historique des commandes 
		enregistreurUndoRedo.addCommand(createMemento(new Inserer(this),character),!rejoue);

		ihm.update();

	}


	public void selection(){
		selecteur.setDebut(ihm.getDebutSelection());
		selecteur.setFin(ihm.getFinSelection());
	}


	/**
	 * Rejoue la macro selectionnée
	 */
	public void rejouer(int idMacro) {
		listMacro.getMacro(idMacro).rejouer(this);
	}

	/**
	 * Retourne le texte du buffer
	 * @return
	 */
	public String getText() {
		return content;
	}

	//Fonction utiles pour les macros

	/**
	 * Créer un memento de la commande 
	 */
	public MementoCommande createMemento(Command commande, String text) {
		MementoCommande enr = new MementoCommande();
		enr.setDebutSelection(selecteur.getDebut());
		enr.setFinSelection(selecteur.getFin());
		enr.setCommande(commande);
		enr.setText(text);
		enr.setCurseurPosition(ihm.getPositionCurseur());

		return enr;

	}


	/**
	 * Exécute la commande contenue dans le memento
	 * @param enr
	 */
	public void setMemento(MementoCommande enr) {

		rejoue = true;

		this.enregistrementCourant = enr;
		selecteur.setDebut(enregistrementCourant.getDebutSelection());
		selecteur.setFin(enregistrementCourant.getFinSelection());
		enregistrementCourant.getCommande().exec();

		rejoue = false;
	}


	//Fonctions utiles pour les Undo/Redo

	/**
	 * Annule la dernière action
	 */
	public void undo() {

		if(enregistreurUndoRedo.UndoPossible()) {

			enregistrementCourant = enregistreurUndoRedo.getUndo();

			selecteur.setDebut(enregistrementCourant.getDebutSelection());
			selecteur.setFin(enregistrementCourant.getFinSelection());
			enregistrementCourant.getCommande().unexec();
		}
	}

	/**
	 * Rejoue la dernière action
	 */
	public void redo() {

		if(enregistreurUndoRedo.RedoPossible()) {
			enregistrementCourant = enregistreurUndoRedo.getRedo();

			selecteur.setDebut(enregistrementCourant.getDebutSelection());
			selecteur.setFin(enregistrementCourant.getFinSelection());
			rejoue = true;
			enregistrementCourant.getCommande().exec();
			rejoue = false;
		}
	}


	public void decoller() {

		content = content.substring(0,selecteur.getDebut())+content.substring(selecteur.getFin(),content.length());
		ihm.update();
	}
	public void decouper() {
		content = content.substring(0,selecteur.getDebut())+content.substring(selecteur.getFin(),content.length());
		ihm.update();
	}
	public void deinserer() {

		content = content.substring(0,enregistrementCourant.getCurseurPosition())+content.substring(enregistrementCourant.getCurseurPosition()+1,content.length());
		ihm.update();
	}
	public void decopier() {

	}



	//Fonctions utilitaire

	private String removeCharacter(int i) {

		String deletedCharacter = ""+content.charAt(i);

		StringBuilder sb = new StringBuilder(content).deleteCharAt(i);
		content = sb.toString();

		return deletedCharacter;
	}


	public EnregistreurMacro newMacro() {
		return listMacro.newMacro();
	}


	public ListEnregistrementMacro getListEnregistreurMacro() {
		return this.listMacro;
	}


	public void supprimer() {

		int position = ihm.getPositionCurseur()-1;

		if(rejoue) {
			position = enregistrementCourant.getCurseurPosition();
		}

		System.out.println("Suppression at "+position);
		String deletedCharacter = removeCharacter(position);

		//Si on enregistre une macro -> on l'ajoute à l'enregistrement
		if(listMacro.isRecording()) {
			listMacro.addEnregistrement(createMemento(new Supprimer(this),deletedCharacter));
		}

		//On l'ajoute à l'historique des commandes 
		enregistreurUndoRedo.addCommand(createMemento(new Supprimer(this),deletedCharacter),!rejoue);

		ihm.update();
	}


	public void desupprimer() {

		int position = enregistrementCourant.getCurseurPosition();
		String character = enregistrementCourant.getText();


		System.out.println("DeSup "+character+" at "+position+" content = "+content);
		content = new StringBuilder(content).insert(position, character).toString();


		ihm.update();
	}



}
