package model.plan;

/**
 * Klasse zur Darstellung der konkreten Aktion, dass ein Gegenstand eingesammelt
 * werden soll. Diese Klasse erbt somit von der abstrakten Klasse
 * {@link Action}.
 * 
 * @author Jannis Hillmann
 *
 */
public class CollectItem extends Action {
	/**
	 * Attribut zur Speicherung, welches Pick-Up aufgesammelt werden soll.
	 */
	private String destination;

	/**
	 * Default Konstruktor.
	 */
	public CollectItem() {
		this(true);
	}

	/**
	 * Konstruktor mit Angabe, ob die Aktion sequentiell oder parallel ist.
	 * 
	 * @param sequential
	 *            Angabe ob sequentiell oder parallel.
	 */
	public CollectItem(boolean sequential) {
		super(sequential);
	}

	/**
	 * Es folgen simple Getter und Setter f&uuml;r die Membervariablen.
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDestination() {
		return destination;
	}

}
