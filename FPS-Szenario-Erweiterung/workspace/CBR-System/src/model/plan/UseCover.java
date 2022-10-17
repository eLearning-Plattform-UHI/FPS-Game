package model.plan;

/**
 * Klasse zur Darstellung der konkreten Aktion, dass der Spieler in Deckung
 * gehen soll. Diese Klasse erbt somit von der abstrakten Klasse {@link Action}.
 * 
 * @author Jannis Hillmann
 *
 */
public class UseCover extends Action {
	/**
	 * Default-Konstruktor.
	 */
	public UseCover() {
		this(true);
	}

	/**
	 * Konstruktor mit Angabe, ob die Aktion sequentiell oder parallel ist.
	 * 
	 * @param sequential
	 *            Angabe ob sequentiell oder parallel.
	 */
	public UseCover(boolean sequential) {
		super(sequential);
	}
}
