import java.util.Scanner;

import commands.Coller;
import commands.Command;
import commands.Copier;
import commands.Couper;
import commands.Inserer;
import commands.Selection;
import controleur.Buffer;
import vue.IHMImplGraphique;

public class Application {
	
	
	public static void main(String args[]){
		
		IHMImplGraphique ihm = new IHMImplGraphique();
		Buffer buffer= new Buffer(ihm);
		
		ihm.setBuffer(buffer);
		
		Command copier = new Copier(buffer);
		Command coller = new Coller(buffer);
		Command inserer = new Inserer(buffer);
		Command selecteur = new Selection(buffer);
		Command couper = new Couper(buffer);
		
		ihm.setCommandColler(coller);
		ihm.setCommandCopier(copier);
		ihm.setCommandCouper(couper);
		ihm.setCommandInserer(inserer);
		ihm.setCommandSelecteur(selecteur);
		
		
		ihm.show();
	}
	
}
