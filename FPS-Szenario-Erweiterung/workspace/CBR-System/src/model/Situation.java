package model;

/**
 * 
 * Diese Klasse stellt eine Datenstruktur zur Verf&uuml;gung, die eine
 * Situation, in der sich der Spieler befindet, darstellt.
 * 
 * @author Jannis Hillmann
 *
 */
public class Situation {

	/**
	 * Der Name des aktuellen Spielers.
	 */
	private String player;
	/**
	 * Der aktuelle Status des Spielers.
	 */
	private Status playerStatus;

	/**
	 * Default-Konstruktor, der f&uuml;r die JSON Serialisierung und
	 * Deserialisierung ben&ouml;tigt wird.
	 */
	public Situation() {

	}

	/**
	 * Konstruktor, der den Namen und den Status des aktuellen Spielers
	 * ben&ouml;tigt.
	 * 
	 * @param player
	 *            Der Name des Spielers.
	 * @param playerStatus
	 *            Der Status des Spielers.
	 */
	public Situation(String player, Status playerStatus) {
		this.player = player;
		this.playerStatus = playerStatus;
	}

	/**
	 * Simpler Setter f&uuml;r den Namen des Spielers.
	 * 
	 * @return Der Name des aktuellen Spielers.
	 */
	public String getPlayer() {
		return player;
	}

	/**
	 * Simpler Getter f&uuml;r den Status des Spielers.
	 * 
	 * @return Der Status des aktuellen Spielers.
	 */
	public Status getPlayerStatus() {
		return playerStatus;
	}

	/**
	 * Simpler Setter f&uuml;r den Namen des Spielers.
	 * 
	 * @param player
	 *            Der Name des Spielers.
	 */
	public void setPlayer(String player) {
		this.player = player;
	}

	/**
	 * Simpler Setter f&uuml;r den Status des Spielers.
	 * 
	 * @param playerStatus
	 *            Der Status des Spielers.
	 */
	public void setPlayerStatus(Status playerStatus) {
		this.playerStatus = playerStatus;
	}

	@Override
	public String toString() {
		return "Situation [player=" + player + ", playerStatus=" + playerStatus.toString() + "]";
	}

}
