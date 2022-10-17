package model.plan;

/**
 * Abstrakte Klasse zur Darstellung einer Aktion. Erbende Klassen stellen
 * jeweils eine konkrete Aktion dar.
 * 
 * @author Jannis Hillmann
 *
 */
public abstract class Action {
	/**
	 * Attribut das beschreibt, ob die Aktion sequentiell oder parallel
	 * ausgef&uuml;hrt werden kann.
	 */
	public boolean sequentiel;
	/**
	 * Attribut das beschreibt, ob die Ausf&uuml;hrung der Aktion abgeschlossen
	 * ist.
	 */
	public boolean finished;

	/**
	 * Default Konstruktor.
	 */
	public Action() {
		this(true);
	}

	/**
	 * Konstruktor mit Angabe, ob die Aktion parallel oder sequentiell ist.
	 * 
	 * @param sequentiel
	 */
	public Action(boolean sequentiel) {
		this.sequentiel = sequentiel;
		finished = false;
	}

	/**
	 * Simple Getter und Setter f&uuml;r die Membervariablen.
	 */
	public boolean isSequentiel() {
		return sequentiel;
	}

	public void setSequentiel(boolean sequentiel) {
		this.sequentiel = sequentiel;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

}
