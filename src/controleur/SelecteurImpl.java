package controleur;

import vue.IHM;

public class SelecteurImpl implements Selecteur{

	private Buffer buffer;
	private int debut;
	private int fin;
	
	public SelecteurImpl(Buffer buffer) {
		this.buffer=buffer;
	}
	
	@Override
	public void setDebut(int debut) {
		this.debut=debut;
	}

	@Override
	public void setFin(int fin) {
		this.fin=fin;
	}

	@Override
	public String lire() {
		System.out.println("Lire slecteur : " + buffer.getContent().substring(debut, fin) + " "+ debut + " " + fin );
		return buffer.getContent().substring(debut, fin);
	}

	@Override
	public void setBuffer(Buffer buffer) {
		this.buffer=buffer;
	}
	
	public int getDebut(){
		return debut;
	}

	@Override
	public int getFin() {
		return fin;
	}
	
}
