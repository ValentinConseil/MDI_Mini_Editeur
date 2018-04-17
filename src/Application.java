import controleur.Buffer;
import controleur.BufferImpl;
import vue.IHM;
import vue.IHMImplGraphique;

public class Application {

	public static void main(String args[]) {

		IHM ihm = new IHMImplGraphique();
		Buffer buffer = new BufferImpl(ihm);

		ihm.setBuffer(buffer);
	}
}
