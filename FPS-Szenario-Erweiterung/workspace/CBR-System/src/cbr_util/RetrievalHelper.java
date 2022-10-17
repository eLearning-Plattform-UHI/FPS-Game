package cbr_util;

import java.util.HashMap;

import de.dfki.mycbr.core.model.AttributeDesc;
import de.dfki.mycbr.core.model.BooleanDesc;
import de.dfki.mycbr.core.model.IntegerDesc;
import de.dfki.mycbr.core.model.StringDesc;
import de.dfki.mycbr.core.model.SymbolDesc;
import main.CBRSystem;

/**
 * Hilfsklasse, die das einmalige Initialisieren der myCBR zugeh&ouml;rigen
 * Attribute vornimmt und diese f&uuml;r die anderen Klassen zur Verf&uuml;gung
 * stellt.
 * 
 * @author Jannis Hillmann
 *
 */
public class RetrievalHelper {
	/**
	 * Map, in welcher alle Attribute des verwendeten Konzepts gespeichert
	 * werden.
	 */
	private static HashMap<String, AttributeDesc> attributeDescriptor = CBRSystem.getEngine().getStatusConcept()
			.getAllAttributeDescs();
	/*
	 * private static HashMap<String, AttributeDesc> attributeDescriptor =
	 * CBREngine.cbrEngine.getStatusConcept() .getAllAttributeDescs();
	 */
	/**
	 * Darstellung des myCBR-Attributs, welches den aktuellen Munitionsstand
	 * darstellt.
	 */
	public static final SymbolDesc CURRENT_AMMUNITION_DESC = (SymbolDesc) attributeDescriptor.get("currentAmmunation");
	/**
	 * Darstellung des myCBR-Attributs, welches die aktuelle Munitionsreserve
	 * darstellt.
	 */
	public static final SymbolDesc CURRENT_OVERALL_AMMUNITION_DESC = (SymbolDesc) attributeDescriptor
			.get("currentOverallAmmunition");
	/**
	 * Darstellung des myCBR-Attributs, welches die Distanz zur Munition
	 * darstellt.
	 */
	public static final SymbolDesc DISTANCE_TO_AMMUNITION_DESC = (SymbolDesc) attributeDescriptor
			.get("distanceToAmmunition");
	/**
	 * Darstellung des myCBR-Attributs, welches die Distanz zur Deckung
	 * darstellt.
	 */
	public static final SymbolDesc DISTANCE_TO_COVER_DESC = (SymbolDesc) attributeDescriptor.get("distanceToCover");
	/**
	 * Darstellung des myCBR-Attributs, welches die Distanz zum Gegner
	 * darstellt.
	 */
	public static final SymbolDesc DISTANCE_TO_ENEMY_DESC = (SymbolDesc) attributeDescriptor.get("distanceToEnemy");
	/**
	 * Darstellung des myCBR-Attributs, welches die Distanz zur Gesundheit
	 * darstellt.
	 */
	public static final SymbolDesc DISTANCE_TO_HEALTH_DESC = (SymbolDesc) attributeDescriptor.get("distanceToHealth");
	/**
	 * Darstellung des myCBR-Attributs, welches die Distanz zur Waffe darstellt.
	 */
	public static final SymbolDesc DISTANCE_TO_WEAPON_DESC = (SymbolDesc) attributeDescriptor.get("distanceToWeapon");
	/**
	 * Darstellung des myCBR-Attributs, welches die aktuelle Waffe darstellt.
	 */
	public static final SymbolDesc EQUIPPED_WEAPON_DESC = (SymbolDesc) attributeDescriptor.get("equippedWeapon");
	/**
	 * Darstellung des myCBR-Attributs, welches die Distanz zur letzten
	 * bekannten Position des Gegners darstellt.
	 */
	public static final SymbolDesc LAST_POSITION_DESC = (SymbolDesc) attributeDescriptor.get("lastPosition");
	/**
	 * Darstellung des myCBR-Attributs, welches die eigene Gesundheit darstellt.
	 */
	public static final SymbolDesc OWN_HEALTH_DESC = (SymbolDesc) attributeDescriptor.get("ownHealth");
	/**
	 * Darstellung des myCBR-Attributs, welches angibt, ob Munition gebraucht
	 * wird.
	 */
	public static final BooleanDesc IS_AMMUNITION_NEEDED_DESC = (BooleanDesc) attributeDescriptor.get("isAmmunitionNeeded");
	/**
	 * Darstellung des myCBR-Attributs, welches angibt, ob Deckung gebraucht
	 * wird.
	 */
	public static final BooleanDesc IS_COVER_NEEDED_DESC = (BooleanDesc) attributeDescriptor.get("isCoverNeeded");
	/**
	 * Darstellung des myCBR-Attributs, welches angibt, ob sich der Spieler in
	 * Deckung befindet.
	 */
	public static final BooleanDesc IS_COVERED_DESC = (BooleanDesc) attributeDescriptor.get("isCovered");
	/**
	 * Darstellung des myCBR-Attributs, welches angibt, ob der Gegner am Leben
	 * ist.
	 */
	public static final BooleanDesc IS_ENEMY_ALIVE_DESC = (BooleanDesc) attributeDescriptor.get("isEnemyAlive");
	/**
	 * Darstellung des myCBR-Attributs, welches angibt, ob der Gegner sichtbar
	 * ist.
	 */
	public static final BooleanDesc IS_ENEMY_VISIBLE_DESC = (BooleanDesc) attributeDescriptor.get("isEnemyVisible");
	/**
	 * Darstellung des myCBR-Attributs, welches angibt, ob Gesundheit gebraucht
	 * wird.
	 */
	public static final BooleanDesc IS_HEALTH_NEEDED_DESC = (BooleanDesc) attributeDescriptor.get("isHealthNeeded");
	/**
	 * Darstellung des myCBR-Attributs, welches die Entfernung zur bewegenden Wand angibt
	 * wird.
	 */
	public static final SymbolDesc MOVING_COVER_DISTANCE_DESC = (SymbolDesc) attributeDescriptor.get("movingCoverDistance");
	
	/**
	 * Darstellung des myCBR-Attributs, welches die Entfernung zur bewegenden Wand angibt
	 * wird.
	 */
	public static final SymbolDesc AMMUNITION_LARGE_DISTANCE_DESC = (SymbolDesc) attributeDescriptor.get("ammunitionLargeDistance");
	
	/**
	 * Darstellung des myCBR-Attributs, ob das Gadget gebraucht wird
	 * wird.
	 */
	public static final BooleanDesc IS_GADGET_NEEDED_DESC = (BooleanDesc) attributeDescriptor.get("isGadgetNeeded");
	
	/**
	 * Darstellung des myCBR-Attributs, welches angibt, ob Gesundheit gebraucht
	 * wird.
	 */
	public static final BooleanDesc IS_WEAPON_NEEDED_DESC = (BooleanDesc) attributeDescriptor.get("isWeaponNeeded");

	public static final SymbolDesc WINCHANCE_DESC = (SymbolDesc) attributeDescriptor.get("winChance");
	
	public static final SymbolDesc KILLDEATHRATIO_DESC = (SymbolDesc) attributeDescriptor.get("killDeathRatio");
	
	public static final SymbolDesc UPTIME_DESC = (SymbolDesc) attributeDescriptor.get("upTime");
	/**
	 * Darstellung des myCBR-Attributs, welches den Plan als String darstellt.
	 */
	public static final StringDesc PLAN_DESC = (StringDesc) attributeDescriptor.get("plan");

	public static final IntegerDesc QUALITY_DESC = (IntegerDesc) attributeDescriptor.get("quality");
}
