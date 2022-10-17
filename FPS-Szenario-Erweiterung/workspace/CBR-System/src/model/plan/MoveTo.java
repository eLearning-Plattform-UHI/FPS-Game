package model.plan;

/**
 * Klasse zur Darstellung der konkreten Aktion, dass der Spieler sich bewegen
 * soll. Diese Klasse erbt somit von der abstrakten Klasse {@link Action}.
 * 
 * @author Jannis Hillmann
 *
 */
public class MoveTo extends Action {

	/**
	 * Default-Konstruktor.
	 */
	public MoveTo() {
		this(false);
	}

	/**
	 * Konstruktor mit Angabe, ob die Aktion sequentiell oder parallel ist.
	 * 
	 * @param sequential
	 *            Angabe ob sequentiell oder parallel.
	 */
	public MoveTo(boolean sequential) {
		super(sequential);
	}

}
