package controleur;

public class PressePapierImpl implements PressePapier {

	private String content;

	@Override
	public String lire() {
		return content;
	}

	@Override
	public void ecrire(String e) {
		this.content = e;
	}

}
