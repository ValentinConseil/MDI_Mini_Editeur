import java.util.Scanner;

import commands.Coller;
import commands.Command;
import commands.Copier;
import commands.Couper;
import commands.Inserer;
import commands.Rejouer;
import commands.Selection;
import commands.StartEnregistrement;
import commands.StopEnregistrement;
import controleur.Buffer;
import enregistreur.Enregistreur;
import vue.IHMImplGraphique;

public class Application {
	
	
	public static void main(String args[]){
		
		IHMImplGraphique ihm = new IHMImplGraphique();
		Buffer buffer= new Buffer(ihm);
		Enregistreur enregistreur = new Enregistreur(); 
		
		ihm.setBuffer(buffer);
		
		Command copier = new Copier(buffer);
		Command coller = new Coller(buffer);
		Command inserer = new Inserer(buffer);
		Command selecteur = new Selection(buffer);
		Command couper = new Couper(buffer);
		
		Command rejouer = new Rejouer(ihm);
		Command enregistrement = new StartEnregistrement(ihm);
		Command stopenregistrement = new StopEnregistrement(ihm);

		
		ihm.setCommandColler(coller);
		ihm.setCommandCopier(copier);
		ihm.setCommandCouper(couper);
		ihm.setCommandInserer(inserer);
		ihm.setCommandSelecteur(selecteur);
		
		ihm.setCommandRejouer(rejouer);
		ihm.setCommandStartEnregistrement(enregistrement);
		ihm.setCommandStopEnregistrement(stopenregistrement);

		
		
		ihm.show();
	}
	
}
