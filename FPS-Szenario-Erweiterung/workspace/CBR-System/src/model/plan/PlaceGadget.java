package model.plan;

/**
 * Klasse zur Darstellung der konkreten Aktion, dass der Spieler das Gedget benutzen soll.
 *  Diese Klasse erbt somit von der abstrakten Klasse {@link Action}.
 * 
 * @author Jannis
 *
 */
public class PlaceGadget extends Action {
	/**
	 * Default-Konstruktor.
	 */
	public PlaceGadget() {
		this(true);
	}

	/**
	 * Konstruktor mit Angabe, ob die Aktion sequentiell oder parallel ist.
	 * 
	 * @param sequential
	 *            Angabe ob sequentiell oder parallel.
	 */
	public PlaceGadget(boolean sequential) {
		super(sequential);
	}
}
