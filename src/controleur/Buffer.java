package controleur;

import java.util.ArrayList;
import java.util.List;

import enregistreur.Enregistreur;
import vue.IHM;

public class Buffer{
	
	
	private PressePapier pressePapier = new PressePapierImpl();
	private Selecteur selecteur = new SelecteurImpl(this);
	private List<Enregistreur> listEnregistreur = new ArrayList<Enregistreur>();
	private IHM ihm;
	
	private String content;
	
	public void copier(){
		System.out.println("conent:"+content);

		pressePapier.ecrire(selecteur.lire());
		System.out.println("copier :"+content+ " pp "+ pressePapier.lire()+ selecteur.lire());

	}
	public void couper(){
		pressePapier.ecrire(selecteur.lire());
		content = content.substring(0,selecteur.getDebut())+content.substring(selecteur.getFin(),content.length());
		System.out.println("conent:"+content);

		ihm.update();
	}
	public void coller(){
		System.out.println("coller :"+content+ " pp "+ pressePapier.lire());

		content = content.substring(0,selecteur.getDebut())+pressePapier.lire()+content.substring(selecteur.getDebut(),content.length());
		System.out.println("coller :"+content);

		ihm.update();
	}
	public void inserer(){
		System.out.println("conent:"+content);

		content = ihm.getText();
	}
	public void selection(){
		System.out.println("Selection ....................................;;;;;;");
		selecteur.setDebut(ihm.getDebutSelection());
		selecteur.setFin(ihm.getFinSelection());

	}
	public String getContent(){
		return content;
	}
	
	public Buffer(IHM ihm) {
		this.ihm=ihm;
	}
	
	public Enregistreur getEnregistreur(int id){
		return listEnregistreur.get(id);
	}
	
	public String getText() {
		System.out.println("conent:"+content);
		return content;
	}
	public Enregistreur addEnregistreur() {
		Enregistreur enregistreur = new Enregistreur();
		listEnregistreur.add(enregistreur);
		return enregistreur;
	}
	
	
	
}
