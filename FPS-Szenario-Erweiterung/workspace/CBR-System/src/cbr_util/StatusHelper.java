package cbr_util;

import model.Status;

/**
 * Hilfsklasse, die dabei hilft, zu einem String den zugehoerigen enum-Wert
 * zu erhalten.
 * 
 * @author Jannis Hillmann
 *
 */
public class StatusHelper {

	/**
	 * Methode die zu einem gegebenen String den zugehoerigen enum-Wert
	 * ermittelt.
	 * 
	 * @param val
	 *            Der gegebene String.
	 * @return Der zu dem gegebenen String zugehoerige enum-Wert.
	 */
	public static int getCurrentAmmuByString(String val) {

		int currentAmmu = Status.CurrentAmmunition.empty.getValue();

		if (val.equals(Status.CurrentAmmunition.few.name())) {
			currentAmmu = Status.CurrentAmmunition.few.getValue();
		} else if (val.equals(Status.CurrentAmmunition.middle.name())) {
			currentAmmu = Status.CurrentAmmunition.middle.getValue();
		} else if (val.equals(Status.CurrentAmmunition.much.name())) {
			currentAmmu = Status.CurrentAmmunition.much.getValue();

		} else if (val.equals(Status.CurrentAmmunition.full.name())) {
			currentAmmu = Status.CurrentAmmunition.full.getValue();
		}

		return currentAmmu;
	}

	/**
	 * Methode die zu einem gegebenen String den zugehoerigen enum-Wert
	 * ermittelt.
	 * 
	 * @param val
	 *            Der gegebene String.
	 * @return Der zu dem gegebenen String zugehoerige enum-Wert.
	 */
	public static int getCurrentOverallAmmuByString(String val) {
		int currentOverallAmmu = Status.CurrentOverallAmmunition.empty.getValue();

		if (val.equals(Status.CurrentOverallAmmunition.few.name())) {
			currentOverallAmmu = Status.CurrentOverallAmmunition.few.getValue();
		} else if (val.equals(Status.CurrentOverallAmmunition.middle.name())) {
			currentOverallAmmu = Status.CurrentOverallAmmunition.middle.getValue();
		} else if (val.equals(Status.CurrentOverallAmmunition.much.name())) {
			currentOverallAmmu = Status.CurrentOverallAmmunition.much.getValue();
		} else if (val.equals(Status.CurrentOverallAmmunition.full.name())) {
			currentOverallAmmu = Status.CurrentOverallAmmunition.full.getValue();
		}

		return currentOverallAmmu;
	}

	/**
	 * Methode die zu einem gegebenen String den zugehoerigen enum-Wert
	 * ermittelt.
	 * 
	 * @param val
	 *            Der gegebene String.
	 * @return Der zu dem gegebenen String zugehoerige enum-Wert.
	 */
	public static int getDistanceToAmmunitionByString(String val) {
		int distanceToAmmu = Status.AmmunitionDistance.unknown.getValue();

		if (val.equals(Status.AmmunitionDistance.near.name())) {
			distanceToAmmu = Status.AmmunitionDistance.near.getValue();
		} else if (val.equals(Status.AmmunitionDistance.middle.name())) {
			distanceToAmmu = Status.AmmunitionDistance.middle.getValue();
		} else if (val.equals(Status.AmmunitionDistance.far.name())) {
			distanceToAmmu = Status.AmmunitionDistance.far.getValue();
		}

		return distanceToAmmu;
	}

	
	/**
	 * Methode die zu einem gegebenen String den zugehoerigen enum-Wert
	 * ermittelt.
	 * 
	 * @param val
	 *            Der gegebene String.
	 * @return Der zu dem gegebenen String zugehoerige enum-Wert.
	 */
	public static int getDistanceToAmmunitionLargeByString(String val) {
		int distanceToAmmuLarge = Status.AmmunitionLargeDistance.unknown.getValue();

		if (val.equals(Status.AmmunitionLargeDistance.near.name())) {
			distanceToAmmuLarge = Status.AmmunitionLargeDistance.near.getValue();
		} else if (val.equals(Status.AmmunitionLargeDistance.middle.name())) {
			distanceToAmmuLarge = Status.AmmunitionLargeDistance.middle.getValue();
		} else if (val.equals(Status.AmmunitionLargeDistance.far.name())) {
			distanceToAmmuLarge = Status.AmmunitionLargeDistance.far.getValue();
		}

		return distanceToAmmuLarge;
	}
	
	
	/**
	 * Methode die zu einem gegebenen String den zugehoerigen enum-Wert
	 * ermittelt.
	 * 
	 * @param val
	 *            Der gegebene String.
	 * @return Der zu dem gegebenen String zugehoerige enum-Wert.
	 */
	public static int getDistanceToCoverByString(String val) {
		int distanceToCover = Status.CoverDistance.unknown.getValue();

		if (val.equals(Status.CoverDistance.near.name())) {
			distanceToCover = Status.CoverDistance.near.getValue();
		} else if (val.equals(Status.CoverDistance.middle.name())) {
			distanceToCover = Status.CoverDistance.middle.getValue();
		} else if (val.equals(Status.CoverDistance.far.name())) {
			distanceToCover = Status.CoverDistance.far.getValue();
		}

		return distanceToCover;
	}

	/**
	 * Methode die zu einem gegebenen String den zugehoerigen enum-Wert
	 * ermittelt.
	 * 
	 * @param val
	 *            Der gegebene String.
	 * @return Der zu dem gegebenen String zugehoerige enum-Wert.
	 */
	public static int getDistanceToMovingCoverByString(String val) {
		int distanceToMovingCover = Status.MovingCoverDistance.unknown.getValue();

		if (val.equals(Status.MovingCoverDistance.near.name())) {
			distanceToMovingCover = Status.MovingCoverDistance.near.getValue();
		} else if (val.equals(Status.MovingCoverDistance.middle.name())) {
			distanceToMovingCover = Status.MovingCoverDistance.middle.getValue();
		} else if (val.equals(Status.MovingCoverDistance.far.name())) {
			distanceToMovingCover = Status.MovingCoverDistance.far.getValue();
		}

		return distanceToMovingCover;
	}
	
	/**
	 * Methode die zu einem gegebenen String den zugehoerigen enum-Wert
	 * ermittelt.
	 * 
	 * @param val
	 *            Der gegebene String.
	 * @return Der zu dem gegebenen String zugehoerige enum-Wert.
	 */
	public static int getDistanceToGadgetByString(String val) {
		int distanceToGadget = Status.AmmunitionLargeDistance.unknown.getValue();

		if (val.equals(Status.AmmunitionLargeDistance.near.name())) {
			distanceToGadget = Status.AmmunitionLargeDistance.near.getValue();
		} else if (val.equals(Status.AmmunitionLargeDistance.middle.name())) {
			distanceToGadget = Status.AmmunitionLargeDistance.middle.getValue();
		} else if (val.equals(Status.AmmunitionLargeDistance.far.name())) {
			distanceToGadget = Status.AmmunitionLargeDistance.far.getValue();
		}

		return distanceToGadget;
	}
	
	/**
	 * Methode die zu einem gegebenen String den zugehoerigen enum-Wert
	 * ermittelt.
	 * 
	 * @param val
	 *            Der gegebene String.
	 * @return Der zu dem gegebenen String zugehoerige enum-Wert.
	 */
	public static int getDistanceToEnemyByString(String val) {
		int distanceToEnemy = Status.Distance.unknown.getValue();

		if (val.equals(Status.Distance.near.name())) {
			distanceToEnemy = Status.Distance.near.getValue();
		} else if (val.equals(Status.Distance.middle.name())) {
			distanceToEnemy = Status.Distance.middle.getValue();
		} else if (val.equals(Status.Distance.far.name())) {
			distanceToEnemy = Status.Distance.far.getValue();
		}

		return distanceToEnemy;
	}

	/**
	 * Methode die zu einem gegebenen String den zugehoerigen enum-Wert
	 * ermittelt.
	 * 
	 * @param val
	 *            Der gegebene String.
	 * @return Der zu dem gegebenen String zugehoerige enum-Wert.
	 */
	public static int getDistanceToHealthByString(String val) {
		int distanceToHealth = Status.HealthDistance.unknown.getValue();

		if (val.equals(Status.HealthDistance.near.name())) {
			distanceToHealth = Status.HealthDistance.near.getValue();
		} else if (val.equals(Status.HealthDistance.middle.name())) {
			distanceToHealth = Status.HealthDistance.middle.getValue();
		} else if (val.equals(Status.HealthDistance.far.name())) {
			distanceToHealth = Status.HealthDistance.far.getValue();
		}

		return distanceToHealth;
	}

	/**
	 * Methode die zu einem gegebenen String den zugehoerigen enum-Wert
	 * ermittelt.
	 * 
	 * @param val
	 *            Der gegebene String.
	 * @return Der zu dem gegebenen String zugehoerige enum-Wert.
	 */
	public static int getDistanceToWeaponByString(String val) {
		int distanceToWeapon = Status.WeaponDistance.unknown.getValue();

		if (val.equals(Status.WeaponDistance.near.name())) {
			distanceToWeapon = Status.WeaponDistance.near.getValue();
		} else if (val.equals(Status.WeaponDistance.middle.name())) {
			distanceToWeapon = Status.WeaponDistance.middle.getValue();
		} else if (val.equals(Status.WeaponDistance.far.name())) {
			distanceToWeapon = Status.WeaponDistance.far.getValue();
		}

		return distanceToWeapon;
	}

	/**
	 * Methode die zu einem gegebenen String den zugehoerigen enum-Wert
	 * ermittelt.
	 * 
	 * @param val
	 *            Der gegebene String.
	 * @return Der zu dem gegebenen String zugehoerige enum-Wert.
	 */
	public static int getLastPositionByString(String val) {
		int lastPosition = Status.LastPosition.unknown.getValue();

		if (val.equals(Status.LastPosition.near.name())) {
			lastPosition = Status.LastPosition.near.getValue();
		} else if (val.equals(Status.LastPosition.middle.name())) {
			lastPosition = Status.LastPosition.middle.getValue();
		} else if (val.equals(Status.LastPosition.far.name())) {
			lastPosition = Status.LastPosition.far.getValue();
		}

		return lastPosition;
	}

	/**
	 * Methode die zu einem gegebenen String den zugehoerigen enum-Wert
	 * ermittelt.
	 * 
	 * @param val
	 *            Der gegebene String.
	 * @return Der zu dem gegebenen String zugehoerige enum-Wert.
	 */
	public static int getOwnHealthByString(String val) {
		int ownHealth = Status.OwnHealth.critical.getValue();

		if (val.equals(Status.OwnHealth.full.name())) {
			ownHealth = Status.OwnHealth.full.getValue();
		} else if (val.equals(Status.OwnHealth.much.name())) {
			ownHealth = Status.OwnHealth.much.getValue();
		} else if (val.equals(Status.OwnHealth.middle.name())) {
			ownHealth = Status.OwnHealth.middle.getValue();
		} else if (val.equals(Status.OwnHealth.few.name())) {
			ownHealth = Status.OwnHealth.few.getValue();
		}

		return ownHealth;
	}
	
	
	/**
	 * Methode die zu einem gegebenen String den zugehoerigen enum-Wert
	 * ermittelt.
	 * 
	 * @param val
	 *            Der gegebene String.
	 * @return Der zu dem gegebenen String zugehoerige enum-Wert.
	 */
	public static double getWinChanceAsString(String val) {
		double winChance = Status.WinChance.equal.getValue();
		
		if(val.equals(Status.WinChance.winning.name())) {
			winChance = Status.WinChance.winning.getValue();
		} else if(val.equals(Status.WinChance.equal.name())) {
			winChance = Status.WinChance.equal.getValue();
		} else if(val.equals(Status.WinChance.loosing.name())) {
			winChance = Status.WinChance.loosing.getValue();
		}
		
		return winChance;
	}
	
	/**
	 * Methode die zu einem gegebenen String den zugehoerigen enum-Wert
	 * ermittelt.
	 * 
	 * @param val
	 *            Der gegebene String.
	 * @return Der zu dem gegebenen String zugehoerige enum-Wert.
	 */
	public static double getKillDeathRatioAsString(String val) {
		double killDeathRatio = Status.KillDeathRatio.equal.getValue();
		
		if(val.equals(Status.KillDeathRatio.positive.name())) {
			killDeathRatio = Status.KillDeathRatio.positive.getValue();
		} else if (val.equals(Status.KillDeathRatio.equal.name())) {
			killDeathRatio = Status.KillDeathRatio.equal.getValue();
		} else if(val.equals(Status.KillDeathRatio.negative.name())){
			killDeathRatio = Status.KillDeathRatio.negative.getValue();
		}
		
		return killDeathRatio;
	}
	
	/**
	 * Methode die zu einem gegebenen String den zugehoerigen enum-Wert
	 * ermittelt.
	 * 
	 * @param val
	 *            Der gegebene String.
	 * @return Der zu dem gegebenen String zugehoerige enum-Wert.
	 */
	public static double getUpTimeAsString(String val) {
		double upTime = Status.UpTime.shorter.getValue();
		
		if(val.equals(Status.UpTime.longer.name())) {
			upTime = Status.UpTime.longer.getValue();
		} else if (val.equals(Status.UpTime.equal.name())) {
			upTime = Status.UpTime.equal.getValue();
		} else if (val.equals(Status.UpTime.shorter.name())){
			upTime = Status.UpTime.shorter.getValue();
		}
		
		return upTime;
	}

}
