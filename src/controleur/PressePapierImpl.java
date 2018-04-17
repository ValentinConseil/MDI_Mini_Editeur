package controleur;

public class PressePapierImpl implements PressePapier {

	private Buffer buffer;
	private String content;

	@Override
	public String lire() {
		return content;
	}

	@Override
	public void ecrire(String e) {
		this.content = e;
	}

	@Override
	public void setBuffer(Buffer buffer) {
		this.buffer = buffer;

	}

}
