package model.plan;

/**
 * Klasse zur Darstellung eines Plans, der von dem Spieler ausgef&uuml;hrt
 * werden soll.
 * 
 * @author Jannis Hillmann
 *
 */
public class Plan {

	/**
	 * Der Plan als String mit klarer Formatierung zur simplen und
	 * automatisierten Verarbeitung.
	 */
	private String actionsAsString;
	/**
	 * Attribut zur Speicherung des Fortschritts der Ausf&uuml;hrung des Plans.
	 */
	private int progress;

	/**
	 * 
	 * enum zur Darstellung des Fortschritts der Ausf&uuml;hrung des Plans.
	 *
	 */
	public enum Progress {
		NOT_STARTED(1 << 0), IN_PROGRESS(1 << 1), DONE(1 << 2);

		private int value;

		Progress(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}
	}

	/**
	 * Default-Konstruktor.
	 */
	public Plan() {
		actionsAsString = "";
		progress = Progress.NOT_STARTED.getValue();
	}

	/**
	 * Methode, die ein gegebenes Array von Aktionen zur Membervariable
	 * hinzuf&uuml;gt, die den Plan als String speichert.
	 * 
	 * @param actions
	 *            Ein Array von Aktionen.
	 */
	public void addActions(Action[] actions) {
		for (Action action : actions) {
			addAction(action);
		}
	}

	/**
	 * Methode, die eine gegebene Aktion zum String 'actionsAsString'
	 * hinzuf&uuml;gt. Daf&uuml;r wird der Klassenname der Aktion verwendet.
	 * Anschlie&szlig;end wird ein Semikolon angeh&auml;gt, um so die
	 * automatisierte Verarbeitung des Strings möglich zu machen.
	 * 
	 * @param action
	 *            Die Aktion, die dem String hinzugef&uuml;gt werden soll.
	 */
	public void addAction(Action action) {
		actionsAsString += action.getClass().getSimpleName() + ";";
	}

	/**
	 * Methode, um den String f&uuml;r JSON zu formatieren. Das letzte Semikolon
	 * im String wird entfernt und anschlie&szlig;end werden m&ouml;gliche
	 * Leerzeichen am Anfang oder am Ende des Strings entfernt, sodass das
	 * automatisierte Verarbeiten des Strings nicht erschwert wird.
	 */
	public void trimStringForJson() {
		actionsAsString = actionsAsString.substring(0, actionsAsString.length() - 1).trim();
	}

	/**
	 * Es folgen simple Getter und Setter f&uuml;r die Membervariablen.
	 * 
	 */
	public int getProgress() {
		return progress;
	}

	public String getActionsAsString() {
		return actionsAsString;
	}

	public void setActionsAsString(String actionsAsString) {
		this.actionsAsString = actionsAsString;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

}
