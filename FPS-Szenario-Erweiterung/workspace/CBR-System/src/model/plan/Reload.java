package model.plan;

/**
 * Klasse zur Darstellung der konkreten Aktion, dass der Spieler nachladen soll.
 * Diese Klasse erbt somit von der abstrakten Klasse {@link Action}.
 * 
 * @author Jannis Hillmann
 *
 */
public class Reload extends Action {

	/**
	 * Default-Konstruktor
	 */
	public Reload() {

	}

	/**
	 * Konstruktor mit Angabe, ob die Aktion sequentiell oder parallel ist.
	 * 
	 * @param sequential
	 *            Angabe ob sequentiell oder parallel.
	 */
	public Reload(boolean sequential) {
		super(sequential);
	}

}
