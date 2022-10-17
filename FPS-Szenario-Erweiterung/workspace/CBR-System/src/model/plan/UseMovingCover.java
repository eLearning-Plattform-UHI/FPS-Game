package model.plan;

/**
 * Klasse zur Darstellung der konkreten Aktion, dass der Spieler in bewegender Deckung
 * gehen soll. Diese Klasse erbt somit von der abstrakten Klasse {@link Action}.
 * 
 * @author Jannis 
 *
 */
public class UseMovingCover extends Action {
	/**
	 * Default-Konstruktor.
	 */
	public UseMovingCover() {
		this(true);
	}

	/**
	 * Konstruktor mit Angabe, ob die Aktion sequentiell oder parallel ist.
	 * 
	 * @param sequential
	 *            Angabe ob sequentiell oder parallel.
	 */
	public UseMovingCover(boolean sequential) {
		super(sequential);
	}
}
