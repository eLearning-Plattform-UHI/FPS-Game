package model.plan;

/**
 * Klasse zur Darstellung der konkreten Aktion, dass der Spieler die Waffe
 * wechseln soll. Diese Klasse erbt somit von der abstrakten Klasse
 * {@link Action}.
 * 
 * @author Jannis Hillmann
 *
 */
public class SwitchWeapon extends Action {

	/**
	 * Default-Konstruktor.
	 */
	public SwitchWeapon() {
		this(true);
	}

	/**
	 * Konstruktor mit Angabe, ob die Aktion sequentiell oder parallel ist.
	 * 
	 * @param sequential
	 *            Angabe ob sequentiell oder parallel.
	 */
	public SwitchWeapon(boolean sequential) {
		super(sequential);
	}

}
