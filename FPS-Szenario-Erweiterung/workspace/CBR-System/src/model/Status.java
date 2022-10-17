package model;

import main.CBRSystem;

//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;

/**
 * 
 * Diese Klasse stellt eine Datenstruktur zur Verf&uuml;gung, die einen Status,
 * &uuml;ber den ein Spieler verf&uuml;gt, darstellt.
 * 
 * @author Jannis Hillmann
 * 
 */
public class Status {

	/**
	 * Attribut das speichert, ob der Gegner sichtbar ist.
	 */
	private boolean isEnemyVisible;
	/**
	 * Attribut, das die Distanz zum Gegner speichert.
	 */
	private int distanceToEnemy;
	/**
	 * Attribut, das die Entfernung zur letzten bekannten Position des Gegners
	 * darstellt.
	 */
	private int lastPosition;
	/**
	 * Attribut das speichert, ob der Gegner am Leben ist.
	 */
	private boolean isEnemyAlive;
	/**
	 * Attribut, das die eigene Gesundheit speichert.
	 */
	private int ownHealth;
	/**
	 * Attribut, das die aktuell ausger&uuml;stete Waffe speichert.
	 */
	private String equippedWeapon;
	/**
	 * Attribut, das den Munitionsstand des aktuellen Magazins speichert.
	 */
	private int currentAmmu;
	/**
	 * Attribut, das die Reservemunition speichert.
	 */
	private int currentOverallAmmu;
	/**
	 * Attribut das speichert, ob eine weitere Waffe ben&ouml;tigt wird.
	 */
	private boolean isWeaponNeeded;
	/**
	 * Attribut das speichert, ob Munition ben&ouml;tigt wird.
	 */
	private boolean isAmmunitionNeeded;
	/**
	 * Attribut das speichert, ob Gesundheit ben&ouml;tigt wird.
	 */
	private boolean isHealthNeeded;

	/**
	 * Attribut das speichert, ob Deckung ben&ouml;tigt wird.
	 */
	private boolean isCoverNeeded;
	/**
	 * Attribut das speichert, ob sich der Spieler in Deckung befindet.
	 */
	private boolean isCovered;
	/**
	 * Attribut das die Distanz zur aufnehmbaren Waffe speichert.
	 */
	private int weaponDistance;
	/**
	 * Attribut das die Distanz zur aufnehmbaren Munition speichert.
	 */
	private int ammunitionDistance;
	/**
	 * Attribut das die Distanz zur aufnehmbaren Gesundheit speichert.
	 */
	private int healthDistance;
	/**
	 * Attribut das die Distanz zur n&auml;chsten Deckung speichert.
	 */
	private int coverDistance;
	
	/**
	 * Attribut das die Distanz zur beweglichen Deckung speichert.
	 */
	private int movingCoverDistance;
	
	/**
	 * Attribut das die Distanz zum groﬂen Munitionsnachschub speichert.
	 */
	private int ammunitionLargeDistance;
	
	/**
	 * Attribut zum speichern, ob das Gadget gebraucht wird.
	 */
	private boolean isGadgetNeeded;
	
	
	private double winChance;
	
	private double killDeathRatio;
	
	private double upTime;
	
	
	/**
	 * enum zur Darstellung der Waffendistanz.
	 *
	 */
	public enum WeaponDistance {
		near(1 << 0), middle(1 << 1), far(1 << 2), unknown(1 << 3);
		private final int value;

		WeaponDistance(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	/**
	 * enum zur Darstellung der Munitionsdistanz.
	 */
	public enum AmmunitionDistance {
		near(1 << 0), middle(1 << 1), far(1 << 2), unknown(1 << 3);
		private final int value;

		AmmunitionDistance(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	/**
	 * enum zur Darstellung der Munitionsdistanz zum groﬂen Munitionsnachschub.
	 */
	public enum AmmunitionLargeDistance {
		near(1 << 0), middle(1 << 1), far(1 << 2), unknown(1 << 3);
		private final int value;

		AmmunitionLargeDistance(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}
	
	/**
	 * enum zur Darstellung der Gesundheitsdistanz.
	 */
	public enum HealthDistance {
		near(1 << 0), middle(1 << 1), far(1 << 2), unknown(1 << 3);
		private final int value;

		HealthDistance(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	/**
	 * enum zur Darstellung der Distanz zur n&auml;chsten Deckung.
	 *
	 */
	public enum CoverDistance {
		near(1 << 0), middle(1 << 1), far(1 << 2), unknown(1 << 3);
		private final int value;

		CoverDistance(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	/**
	 * enum zur Darstellung der Distanz zur beweglichen Deckung.
	 *
	 */
	public enum MovingCoverDistance {
		near(1 << 0), middle(1 << 1), far(1 << 2), unknown(1 << 3);
		private final int value;

		MovingCoverDistance(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}
	
	/**
	 * enum zur Darstellung der aktuellen Munition.
	 *
	 */
	public enum CurrentAmmunition {
		full(1 << 0), much(1 << 1), middle(1 << 2), few(1 << 3), empty(1 << 4);

		private final int value;

		CurrentAmmunition(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

	}

	/**
	 * enum zur Darstellung der Reservemunition.
	 *
	 */
	public enum CurrentOverallAmmunition {
		full(1 << 0), much(1 << 1), middle(1 << 2), few(1 << 3), empty(1 << 4);

		private final int value;

		CurrentOverallAmmunition(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	/**
	 * enum zur Darstellung der Distanz zum Gegner.
	 */
	public enum Distance {
		near(1 << 0), middle(1 << 1), far(1 << 2), unknown(1 << 3);

		private final int value;

		Distance(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	/**
	 * enum zur Darstellung der eigenen Gesundheit.
	 *
	 */
	public enum OwnHealth {
		full(1 << 0), much(1 << 1), middle(1 << 2), few(1 << 3), critical(1 << 4);

		private final int value;

		OwnHealth(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	/**
	 * enum zur Darstellung der Distanz zur letzten bekannten Position des
	 * Gegners.
	 */
	public enum LastPosition {
		near(1 << 0), middle(1 << 1), far(1 << 2), unknown(1 << 3);

		private final int value;

		LastPosition(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}
	
	/**
	 * enum zur Darstellung der mˆglichen Gewinnchance der aktuellen 
	 * Situation der CBR-Ki
	 */
	public enum WinChance {
		winning(1 << 0), equal(1 << 1), loosing(1 << 2);
		private final double value;
		
		WinChance(double value) {
			this.value = value;
		}
		
		public double getValue() {
			return value;
		}
	}
	
	/**
	 * enum zur Darstellung des Verhaeltnisses von Abschuessen zu Toden der 
	 * CBR Ki. 
	 */
	public enum KillDeathRatio {
		positive(1 << 0), equal(1 << 1), negative(1 << 2);
		private final double value;
		
		KillDeathRatio(double value) {
			this.value = value;
		}
		
		public double getValue() {
			return value;
		}
	}
	
	/**
	 * enum zur Darstellung der Rundenlaufzeit der CBR Ki. 
	 * Dieser Enum wird nach dem Tod der CBR Ki verwendet um zu pruefen
	 * ob sich durch das Verhalten der Ki die ueberlebenszeit verlaengert hat.
	 */
	public enum UpTime {
		longer(1 << 0), equal(1 << 1), shorter(1 << 2);
		private final double value;
		
		UpTime(double value) {
			this.value = value;
		}
		
		public double getValue() {
			
			return value;	
		}
	}
	
	


	/**
	 * Default-Konstruktor f&uuml;r den Status.
	 */
	public Status() {
		this(false, Distance.unknown.getValue(), LastPosition.unknown.getValue(), true, 100, "Pistol", 12, 48, true,
				false, false, false, false, WeaponDistance.unknown.getValue(), AmmunitionDistance.unknown.getValue(),
				HealthDistance.unknown.getValue(), CoverDistance.unknown.getValue(), WinChance.equal.getValue(),
				KillDeathRatio.negative.getValue(), UpTime.longer.getValue(), MovingCoverDistance.unknown.getValue(), AmmunitionLargeDistance.unknown.getValue(), true);
	}

	/**
	 * Konstruktor mit allen Parametern zur Erzeugung eines Status-Objekts, alle
	 * Parameter vor allem f&uuml;r die (De-)Serialisierung durch JSON.
	 * 
	 * @param isEnemyVisible
	 *            Ist der Gegner sichtbar.
	 * @param distanceToEnemy
	 *            Distanz zum Gegner.
	 * @param lastPosition
	 *            Distanz zur letzten bekannten Position des Gegners.
	 * @param isEnemyAlive
	 *            Ist der Gegner am Leben.
	 * @param ownHealth
	 *            Eigene Gesundheit.
	 * @param equippedWeapon
	 *            Ausger&uuml;stete Waffe-
	 * @param currentAmmu
	 *            Aktuelle Munition im Magazin.
	 * @param currentOverallAmmu
	 *            Aktuelle Reservemunition.
	 * @param isWeaponNeeded
	 *            Wird eine weitere Waffe ben&ouml;tigt.
	 * @param isAmmunitionNeeded
	 *            Wird Munition gebraucht.
	 * @param isHealthNeeded
	 *            Wird Gesundheit gebraucht.
	 * @param isCoverNeeded
	 *            Wird Deckung gebraucht.
	 * @param isCovered
	 *            Befindet sich der Spieler aktuell in Deckung.
	 * @param weaponDistance
	 *            Distanz zur Waffe.
	 * @param ammunitionDistance
	 *            Distanz zur Munition.
	 * @param healthDistance
	 *            Distanz zur Gesundheit.
	 * @param coverDistance
	 *            Distanz zur Deckung.
	 */
	public Status(boolean isEnemyVisible, int distanceToEnemy, int lastPosition, boolean isEnemyAlive, int ownHealth,
			String equippedWeapon, int currentAmmu, int currentOverallAmmu, boolean isWeaponNeeded,
			boolean isAmmunitionNeeded, boolean isHealthNeeded, boolean isCoverNeeded, boolean isCovered,
			int weaponDistance, int ammunitionDistance, int healthDistance, int coverDistance, double winChance, double killDeathRatio,
			double upTime, int movingCoverDistance, int ammunitionLargeDistance, boolean isGadgetNeeded) {
		this.isEnemyVisible = isEnemyVisible;
		this.distanceToEnemy = distanceToEnemy;
		this.lastPosition = lastPosition;
		this.isEnemyAlive = isEnemyAlive;
		this.ownHealth = ownHealth;
		this.equippedWeapon = equippedWeapon;
		this.currentAmmu = currentAmmu;
		this.currentOverallAmmu = currentOverallAmmu;
		this.isWeaponNeeded = isWeaponNeeded;
		this.isAmmunitionNeeded = isAmmunitionNeeded;
		this.isHealthNeeded = isHealthNeeded;
		this.isCoverNeeded = isCoverNeeded;
		this.isCovered = isCovered;
		this.weaponDistance = weaponDistance;
		this.ammunitionDistance = ammunitionDistance;
		this.healthDistance = healthDistance;
		this.coverDistance = coverDistance;
		this.winChance = winChance;
		this.killDeathRatio = killDeathRatio;
		this.upTime = upTime;
		
		this.movingCoverDistance = movingCoverDistance;
		this.ammunitionLargeDistance = ammunitionLargeDistance;
		this.isGadgetNeeded = isGadgetNeeded;
	}

	/**
	 * Wandelt den Integer-Wert in den zugeh&ouml;rigen enum-Wert um.
	 * 
	 * @return Die Distanz zum Gegner als enum-Wert.
	 */
	public Distance getDistanceToEnemyAsEnum() {
		Distance distance = Distance.unknown;
		if (distanceToEnemy == Distance.near.getValue()) {
			distance = Distance.near;
		} else if (distanceToEnemy == Distance.middle.getValue()) {
			distance = Distance.middle;
		} else if (distanceToEnemy == Distance.far.getValue()) {
			distance = Distance.far;
		}

		return distance;
	}

	/**
	 * Wandelt den Integer-Wert in den zugeh&ouml;rigen enum-Wert um.
	 * 
	 * @return Die Distanz zur letzten bekannten Position des Gegners als
	 *         enum-Wert.
	 */
	public LastPosition getLastPositionAsEnum() {
		LastPosition lastPosition = LastPosition.unknown;
		if (this.lastPosition == LastPosition.near.getValue()) {
			lastPosition = LastPosition.near;
		} else if (this.lastPosition == LastPosition.middle.getValue()) {
			lastPosition = LastPosition.middle;
		} else if (this.lastPosition == LastPosition.far.getValue()) {
			lastPosition = LastPosition.far;
		}

		return lastPosition;
	}

	/**
	 * Wandelt den Integer-Wert in den zugeh&ouml;rigen enum-Wert um.
	 * 
	 * @return Die Distanz zur Waffe als enum-Wert.
	 */
	public WeaponDistance getWeaponDistanceAsEnum() {
		WeaponDistance weaponDistance = WeaponDistance.unknown;
		if (this.weaponDistance == WeaponDistance.near.getValue()) {
			weaponDistance = WeaponDistance.near;
		} else if (this.weaponDistance == WeaponDistance.middle.getValue()) {
			weaponDistance = WeaponDistance.middle;
		} else if (this.weaponDistance == WeaponDistance.far.getValue()) {
			weaponDistance = WeaponDistance.far;
		}

		return weaponDistance;
	}

	/**
	 * Wandelt den Integer-Wert in den zugeh&ouml;rigen enum-Wert um.
	 * 
	 * @return Die Distanz zur Munition als enum-Wert.
	 */
	public AmmunitionDistance getAmmunitionDistanceAsEnum() {
		AmmunitionDistance ammuDistance = AmmunitionDistance.unknown;
		if (ammunitionDistance == AmmunitionDistance.near.getValue()) {
			ammuDistance = AmmunitionDistance.near;
		} else if (ammunitionDistance == AmmunitionDistance.middle.getValue()) {
			ammuDistance = AmmunitionDistance.middle;
		} else if (ammunitionDistance == AmmunitionDistance.far.getValue()) {
			ammuDistance = AmmunitionDistance.far;
		}

		return ammuDistance;
	}

	/**
	 * Wandelt den Integer-Wert in den zugeh&ouml;rigen enum-Wert um.
	 * 
	 * @return Die Distanz zum groﬂen Munitionsnachschub als enum-Wert.
	 */
	public AmmunitionLargeDistance getAmmunitionLargeDistanceAsEnum() {
		AmmunitionLargeDistance ammuLargeDistance = AmmunitionLargeDistance.unknown;
		if (ammunitionLargeDistance == AmmunitionLargeDistance.near.getValue()) {
			ammuLargeDistance = AmmunitionLargeDistance.near;
		} else if (ammunitionLargeDistance == AmmunitionLargeDistance.middle.getValue()) {
			ammuLargeDistance = AmmunitionLargeDistance.middle;
		} else if (ammunitionLargeDistance == AmmunitionLargeDistance.far.getValue()) {
			ammuLargeDistance = AmmunitionLargeDistance.far;
		}
	
		return ammuLargeDistance;
	}
	
	/**
	 * Wandelt den Integer-Wert in den zugeh&ouml;rigen enum-Wert um.
	 * 
	 * @return Die Distanz zur Gesundheit als enum-Wert.
	 */
	public HealthDistance getHealthDistanceAsEnum() {
		HealthDistance healthDistance = HealthDistance.unknown;
		if (this.healthDistance == HealthDistance.near.getValue()) {
			healthDistance = HealthDistance.near;
		} else if (this.healthDistance == HealthDistance.middle.getValue()) {
			healthDistance = HealthDistance.middle;
		} else if (this.healthDistance == HealthDistance.far.getValue()) {
			healthDistance = HealthDistance.far;
		}

		return healthDistance;
	}

	/**
	 * Wandelt den Integer-Wert in den zugeh&ouml;rigen enum-Wert um.
	 * 
	 * @return Die Distanz zur Gesundheit als enum-Wert.
	 */
	public CoverDistance getCoverDistanceAsEnum() {
		CoverDistance coverDistance = CoverDistance.unknown;
		if (this.coverDistance == CoverDistance.near.getValue()) {
			coverDistance = CoverDistance.near;
		} else if (this.coverDistance == CoverDistance.middle.getValue()) {
			coverDistance = CoverDistance.middle;
		} else if (this.coverDistance == CoverDistance.far.getValue()) {
			coverDistance = CoverDistance.far;
		}

		return coverDistance;
	}

	/**
	 * Wandelt den Integer-Wert in den zugeh&ouml;rigen enum-Wert um.
	 * 
	 * @return Die Distanz zur beweglichen Deckung als enum-Wert.
	 */
	public MovingCoverDistance getMovingCoverDistanceAsEnum() {
		MovingCoverDistance movingCoverDistance = MovingCoverDistance.unknown;
		if (this.movingCoverDistance == MovingCoverDistance.near.getValue()) {
			movingCoverDistance = MovingCoverDistance.near;
		} else if (this.movingCoverDistance == MovingCoverDistance.middle.getValue()) {
			movingCoverDistance = MovingCoverDistance.middle;
		} else if (this.movingCoverDistance == MovingCoverDistance.far.getValue()) {
			movingCoverDistance = MovingCoverDistance.far;
		}

		return movingCoverDistance;
	}
	
	/**
	 * Wandelt den Integer-Wert in den zugeh&ouml;rigen enum-Wert um.
	 * 
	 * @return Den aktuellen Integer-Wert als enum-Wert.
	 */
	public CurrentAmmunition getCurrentAmmunitionAsEnum() {
		CurrentAmmunition currentAmmu = CurrentAmmunition.empty;
		if (this.currentAmmu == CurrentAmmunition.full.getValue()) {
			currentAmmu = CurrentAmmunition.full;
		} else if (this.currentAmmu == CurrentAmmunition.middle.getValue()) {
			currentAmmu = CurrentAmmunition.middle;
		} else if (this.currentAmmu == CurrentAmmunition.much.getValue()) {
			currentAmmu = CurrentAmmunition.much;
		} else if (this.currentAmmu == CurrentAmmunition.few.getValue()) {
			currentAmmu = CurrentAmmunition.few;
		}
		return currentAmmu;
	}

	/**
	 * Wandelt den Integer-Wert in den zugeh&ouml;rigen enum-Wert um.
	 * 
	 * @return Den aktuellen Integer-Wert als enum-Wert.
	 */
	public CurrentOverallAmmunition getCurrentOverallAmmunitionAsEnum() {
		CurrentOverallAmmunition currentOverallAmmu = CurrentOverallAmmunition.empty;
		if (this.currentOverallAmmu == CurrentOverallAmmunition.full.getValue()) {
			currentOverallAmmu = CurrentOverallAmmunition.full;
		} else if (this.currentOverallAmmu == CurrentOverallAmmunition.middle.getValue()) {
			currentOverallAmmu = CurrentOverallAmmunition.middle;
		} else if (this.currentOverallAmmu == CurrentOverallAmmunition.much.getValue()) {
			currentOverallAmmu = CurrentOverallAmmunition.much;
		} else if (this.currentOverallAmmu == CurrentOverallAmmunition.few.getValue()) {
			currentOverallAmmu = CurrentOverallAmmunition.few;
		}

		return currentOverallAmmu;
	}

	/**
	 * Wandelt den Integer-Wert in den zugeh&ouml;rigen enum-Wert um.
	 * 
	 * @return Den aktuellen Integer-Wert als enum-Wert.
	 */
	public OwnHealth getOwnHealthAsEnum() {
		OwnHealth ownHealth = OwnHealth.critical;
		if (this.ownHealth == OwnHealth.full.getValue()) {
			ownHealth = OwnHealth.full;
		} else if (this.ownHealth == OwnHealth.middle.getValue()) {
			ownHealth = OwnHealth.middle;
		} else if (this.ownHealth == OwnHealth.much.getValue()) {
			ownHealth = OwnHealth.much;
		} else if (this.ownHealth == OwnHealth.few.getValue()) {
			ownHealth = OwnHealth.few;
		}

		return ownHealth;
	}
	
	/**
	 * Wandelt den Integer-Wert in den zugehoerigen enum-Wert um.
	 * 
	 * @return Die aktuelle Chance die Situation abzuschliessen.
	 */
	public WinChance getWinChanceAsEnum() {
		WinChance winChance = WinChance.equal;
		
		if(this.winChance == WinChance.winning.getValue()) {
			winChance = WinChance.winning;
		} else if(this.winChance == WinChance.equal.getValue()) {
			winChance = WinChance.equal;
		} else {
			winChance = WinChance.loosing;
		}
		
		return winChance;
	}
	
	
	/**
	 * Wandelt den Integer-Wert in den zugeh&ouml;rigen enum-Wert um.
	 * 
	 * @return Das Verh‰ltnis von Abschuessen zu Toden.
	 */
	public KillDeathRatio getKillDeathRatioAsEnum() {
		
		KillDeathRatio kdr = KillDeathRatio.negative;
		
		if(this.killDeathRatio == KillDeathRatio.positive.getValue()) {
			kdr = KillDeathRatio.positive;
		} else if(this.killDeathRatio == KillDeathRatio.equal.getValue()) {
			kdr = KillDeathRatio.equal;
		} else {
			kdr = KillDeathRatio.negative;
		}
		
		return kdr;
	}
	
	
	/**
	 * Wandelt den Integer-Wert in den zugeh&ouml;rigen enum-Wert um.
	 * 
	 * @return Die Laufzeit der CBR Ki in der aktuellen Runde.
	 */
	public UpTime getUpTimeAsEnum() {
		
		UpTime ut = UpTime.longer;
		
		if(this.upTime == UpTime.longer.getValue()) {
			ut = UpTime.longer;
		} else if(this.upTime == UpTime.equal.getValue()) {
			ut = UpTime.equal;
		} else {
			ut = UpTime.shorter;
		}
		
		return ut;
	}

	/**
	 * 
	 * Es folgen die simplen Getter und Setter f&uuml;r die einzelnen
	 * Membervariablen.
	 */

	public boolean isEnemyVisible() {
		return isEnemyVisible;
	}

	public void setEnemyVisible(boolean isEnemyVisible) {
		this.isEnemyVisible = isEnemyVisible;
	}

	public int getDistanceToEnemy() {
		return distanceToEnemy;
	}

	public void setDistanceToEnemy(int distanceToEnemy) {
		this.distanceToEnemy = distanceToEnemy;
	}

	public int getLastPosition() {
		return lastPosition;
	}

	public void setLastPosition(int lastPosition) {
		this.lastPosition = lastPosition;
	}

	public boolean isEnemyAlive() {
		return isEnemyAlive;
	}

	public void setEnemyAlive(boolean isEnemyAlive) {
		this.isEnemyAlive = isEnemyAlive;
	}

	public int getOwnHealth() {
		return ownHealth;
	}

	public void setOwnHealth(int ownHealth) {
		this.ownHealth = ownHealth;
	}

	public String getEquippedWeapon() {
		return equippedWeapon;
	}

	public void setEquippedWeapon(String equippedWeapon) {
		this.equippedWeapon = equippedWeapon;
	}

	public int getCurrentAmmu() {
		return currentAmmu;
	}

	public void setCurrentAmmu(int currentAmmu) {
		this.currentAmmu = currentAmmu;
	}

	public int getCurrentOverallAmmu() {
		return currentOverallAmmu;
	}

	public void setCurrentOverallAmmu(int currentOverallAmmu) {
		this.currentOverallAmmu = currentOverallAmmu;
	}

	public boolean isWeaponNeeded() {
		return isWeaponNeeded;
	}

	public void setWeaponNeeded(boolean isWeaponNeeded) {
		this.isWeaponNeeded = isWeaponNeeded;
	}

	public boolean isAmmunitionNeeded() {
		return isAmmunitionNeeded;
	}
	
	public void setAmmunitionNeeded(boolean isAmmunitionNeeded) {
		this.isAmmunitionNeeded = isAmmunitionNeeded;
	}

	public boolean isGadgetNeeded() {
		return isGadgetNeeded;
	}

	public void setIsGadgetNeeded(boolean isGadgetNeeded) {
		this.isGadgetNeeded = isGadgetNeeded;
	}

	public boolean isHealthNeeded() {
		return isHealthNeeded;
	}

	public void setHealthNeeded(boolean isHealthNeeded) {
		this.isHealthNeeded = isHealthNeeded;
	}

	public boolean isCoverNeeded() {
		return isCoverNeeded;
	}

	public void setCoverNeeded(boolean isCoverNeeded) {
		this.isCoverNeeded = isCoverNeeded;
	}

	public boolean isCovered() {
		return isCovered;
	}

	public void setCovered(boolean isCovered) {
		this.isCovered = isCovered;
	}

	public int getWeaponDistance() {
		return weaponDistance;
	}

	public void setWeaponDistance(int weaponDistance) {
		this.weaponDistance = weaponDistance;
	}

	public int getAmmunitionDistance() {
		return ammunitionDistance;
	}
	public void setAmmunitionDistance(int ammunitionDistance) {
		this.ammunitionDistance = ammunitionDistance;
	}

	public void setAmmunitionLargeDistance(int ammunitionLargeDistance) {
		this.ammunitionLargeDistance = ammunitionLargeDistance;
	}
	public int getAmmunitionLargeDistance() {
		return ammunitionLargeDistance;
	}

	public int getHealthDistance() {
		return healthDistance;
	}

	public void setHealthDistance(int healthDistance) {
		this.healthDistance = healthDistance;
	}

	public int getCoverDistance() {
		return coverDistance;
	}

	public void setCoverDistance(int coverDistance) {
		this.coverDistance = coverDistance;
	}
	
	public void setMovingCoverDistance(int movingCoverDistance) {
		this.movingCoverDistance = movingCoverDistance;
	}
	
	public int getMovingCoverDistance() {
		return movingCoverDistance;
	}

	public double getWinChance() {
		return winChance;
	}

	public void setWinChance(double winChance) {
		this.winChance = winChance;
	}
	
	public double getKillDeathRatio() {
		return killDeathRatio;
	}
	
	public void setKillDeathRatio(double killDeathRatio) {
		this.killDeathRatio = killDeathRatio;
	}
	
	public double getUpTime() {
		return upTime;
	}
	
	public void setUpTime(double upTime) {
		this.upTime = upTime;
	}
	
	
	
	
	@Override
	public String toString() {
		 
		return "Status [isEnemyVisible=" + isEnemyVisible + ", distanceToEnemy=" + distanceToEnemy + ", lastPosition="
				+ lastPosition + ", isEnemyAlive=" + isEnemyAlive + ", ownHealth=" + ownHealth + ", equippedWeapon="
				+ equippedWeapon + ", currentAmmu=" + currentAmmu + ", currentOverallAmmu=" + currentOverallAmmu
				+ ", isWeaponNeeded=" + isWeaponNeeded + ", isAmmunitionNeeded=" + isAmmunitionNeeded
				+ ", isHealthNeeded=" + isHealthNeeded + ", isCoverNeeded=" + isCoverNeeded + ", isCovered=" + isCovered
				+ ", weaponDistance=" + weaponDistance + ", ammunitionDistance=" + ammunitionDistance
				+ ", healthDistance=" + healthDistance + ", coverDistance=" + coverDistance + ", winChance="+ winChance 
				+ ", killDeathRatio=" + killDeathRatio + ", upTime=" + upTime
				+ ", movingCoverDistance=" + movingCoverDistance + ", ammunitionLargeDistance="
				+ ammunitionLargeDistance + ", isGadgetNeeded=" + isGadgetNeeded + "]";
		
	}


}