package commands;

/**
 * Commande chargée d'appeler une action sur le buffer
 *
 */
public interface Command {

	/**
	 * Exécute la commande
	 */
	public void exec();
}
