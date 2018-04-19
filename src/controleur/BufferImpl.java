package controleur;

import vue.IHM;

public class BufferImpl implements Buffer {

	private PressePapier pressePapier = new PressePapierImpl();
	private Selecteur selecteur = new SelecteurImpl(this);
	private IHM ihm;

	// Texte du buffer
	private String content = "";

	public BufferImpl(IHM ihm) {
		this.ihm = ihm;
	}

	// Fonction appelées par les commandes

	public void copier() {

		pressePapier.ecrire(selecteur.lire());

	}

	public void couper() {

		pressePapier.ecrire(selecteur.lire());
		content = content.substring(0, selecteur.getDebut()) + content.substring(selecteur.getFin(), content.length());

		int newCursorPosition = selecteur.getDebut();
		ihm.update();
		ihm.setCursorPosition(newCursorPosition);
	}

	public void coller() {

		int newCursorPosition;

		newCursorPosition = selecteur.getFin() + pressePapier.lire().length();
		content = content.substring(0, selecteur.getDebut()) + pressePapier.lire()
					+ content.substring(selecteur.getDebut(), content.length());
		
		ihm.update();
		ihm.setCursorPosition(newCursorPosition);

	}

	public void inserer() {

		int position = selecteur.getDebut();
		String character = ihm.getCaractereInsere();

		content = new StringBuilder(content).insert(position, character).toString();

		int newCursorPosition = selecteur.getFin() + 1;
		ihm.update();
		ihm.setCursorPosition(newCursorPosition);

	}

	public void selection() {
		selecteur.setDebut(ihm.getDebutSelection());
		selecteur.setFin(ihm.getFinSelection());
	}

	/**
	 * Retourne le texte du buffer
	 * 
	 * @return
	 */
	public String getText() {
		return content;
	}



	public void supprimer() {

		int startPosition = selecteur.getDebut();
		int endPosition = selecteur.getFin();

		if (startPosition > 0) {
			
				startPosition = selecteur.getDebut();
				endPosition = selecteur.getFin();
			

			if (startPosition == endPosition) {
				startPosition--;
			}
			
			content = content.substring(0,startPosition) + content.substring(endPosition, content.length());
			
			ihm.update();
			ihm.setCursorPosition(startPosition);
		}
	}

}
