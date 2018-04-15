package enregistreur;

import commands.Command;

public class Enregistrement {
	
	private Command commande;
	private String text = "";
	
	int debutSelection, finSelection, curseurPosition;

	public int getCurseurPosition() {
		return curseurPosition;
	}

	public void setCurseurPosition(int curseurPosition) {
		this.curseurPosition = curseurPosition;
	}

	public int getDebutSelection() {
		return debutSelection;
	}

	public void setDebutSelection(int debutSelection) {
		this.debutSelection = debutSelection;
	}
	
	public Command getCommande() {
		return commande;
	}

	public void setCommande(Command commande) {
		this.commande = commande;
	}

	public int getFinSelection() {
		return finSelection;
	}

	public void setFinSelection(int finSelection) {
		this.finSelection = finSelection;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
