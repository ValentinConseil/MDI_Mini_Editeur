import modele.Buffer;
import modele.BufferImpl;
import vue.IHM;
import vue.IHMImplGraphique;

public class Application {

	public static void main(String args[]) {

		IHM ihm = new IHMImplGraphique();
		Buffer buffer = new BufferImpl(ihm);

		ihm.setBufferReceiver(buffer);
	}
}
