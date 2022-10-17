package model.plan;

/**
 * Klasse zur Darstellung der konkreten Aktion, dass der Spieler schie&szlig;en
 * soll. Diese Klasse erbt somit von der abstrakten Klasse {@link Action}.
 * 
 * @author Jannis Hillmann
 *
 */
public class Shoot extends Action {

	/**
	 * Default-Konstruktor.
	 */
	public Shoot() {
		this(false);
	}

	/**
	 * Konstruktor mit Angabe, ob die Aktion sequentiell oder parallel ist.
	 * 
	 * @param sequential
	 *            Angabe ob sequentiell oder parallel.
	 */
	public Shoot(boolean sequential) {
		super(sequential);
	}

}
