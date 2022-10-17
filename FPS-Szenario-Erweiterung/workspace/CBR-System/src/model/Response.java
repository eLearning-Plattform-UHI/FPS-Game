package model;

import model.plan.Plan;

/**
 * 
 * Diese Klasse stellt eine Datenstruktur zur Verf&uuml;gung, die eine
 * ausgehende Antwort des Java Projektes zum C# Projektes darstellt.
 * 
 * @author Jannis Hillmann
 *
 */
public class Response {

	/**
	 * Die Situation, in der sich der Spieler befindet.
	 */
	private Situation situation;
	/**
	 * Der Plan, der vom Spieler ausgef&uuml;hrt werden soll.
	 */
	private Plan plan;

	/**
	 * Default-Konstruktor, der f&uuml;r die JSON Serialisierung und
	 * Deserialisierung ben&ouml;tigt wird.
	 */
	public Response() {
		this(new Situation(), new Plan());
	}

	/**
	 * Konstruktor zur Erzeugung eines Response-Objekts mit einer Situation.
	 * 
	 * @param situation
	 *            Die Situation, in der sich der Spieler befindet.
	 */
	public Response(Situation situation) {
		this(situation, new Plan());
	}

	/**
	 * Konstruktor zur Erzeugung eines Response-Objekts mit einer Situation.
	 * 
	 * @param situation
	 *            Die Situation, in der sich der Spieler befindet.
	 * @param plan
	 *            Der Plan, der vom Spieler ausgef&uuml;hrt werden soll.
	 */
	public Response(Situation situation, Plan plan) {
		this.situation = situation;
		this.plan = plan;
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
	 * Simpler Getter f&uuml;r die Situation.
	 * 
	 * @return Die aktuelle Situation der Anfrage.
	 */
	public Situation getSituation() {
		return situation;
	}

	/**
	 * Simpler Getter f&uuml;r den Plan.
	 * 
	 * @return Der aktuelle Plan.
	 */
	public Plan getPlan() {
		return plan;
	}

	/**
	 * Simpler Setter f&uuml;r den Plan.
	 * 
	 * @param plan
	 *            Der neue Plan.
	 */
	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	@Override
	public String toString() {
		return "Response [situation=" + situation.toString() + "]";
	}

}
