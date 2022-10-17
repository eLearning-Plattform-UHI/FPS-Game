package model;

/**
 * 
 * Diese Klasse stellt eine Datenstruktur zur Verf&uuml;gung, die eine
 * eingehende Anfrage des C# Projektes darstellt.
 * 
 * @author Jannis Hillmann
 *
 */
public class Request {

	/**
	 * Die Situation, in der sich der Spieler befindet.
	 */
	private Situation situation;

	/**
	 * Default-Konstruktor, der f&uuml;r die JSON Serialisierung und
	 * Deserialisierung ben&ouml;tigt wird.
	 */
	public Request() {
		this(new Situation());
	}

	/**
	 * Konstruktor zur Erzeugung eines Reuquest-Objekts.
	 * 
	 * @param situation
	 *            Die Situation, in der sich der anfragende Spieler befindet.
	 */
	public Request(Situation situation) {
		this.situation = situation;
	}

	/**
	 * Simpler Setter f&uuml;r die Situation.
	 * 
	 * @param situation
	 *            Die neue Situation.
	 */
	public void setSituation(Situation situation) {
		this.situation = situation;
	}

	/**
	 * Simpler Getter fuer die Situation.
	 * 
	 * @return Die aktuelle Situation der Anfrage.
	 */
	public Situation getSituation() {
		return situation;
	}

	@Override
	public String toString() {
		return "Request [situation=" + situation.toString() + "]";
	}

}
