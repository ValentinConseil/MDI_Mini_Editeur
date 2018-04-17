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
import enregistreur.EnregistreurMacro;
import vue.IHMImplGraphique;

public class Application {
	
	
	public static void main(String args[]){
		
		IHMImplGraphique ihm = new IHMImplGraphique();
		Buffer buffer= new Buffer(ihm);
		
		ihm.setBuffer(buffer);		
		ihm.show();
	}
}
