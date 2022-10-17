package cbr_util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.dfki.mycbr.core.DefaultCaseBase;
import de.dfki.mycbr.core.ICaseBase;
import de.dfki.mycbr.core.Project;
import de.dfki.mycbr.core.casebase.Instance;
import de.dfki.mycbr.core.model.AttributeDesc;
import de.dfki.mycbr.core.model.Concept;
import de.dfki.mycbr.core.retrieval.Retrieval;
import de.dfki.mycbr.core.retrieval.Retrieval.RetrievalMethod;
import de.dfki.mycbr.core.similarity.Similarity;
import de.dfki.mycbr.util.Pair;
import main.CBRSystem;
import model.Request;
import model.Response;
import model.Status;
import model.plan.Action;
import model.plan.CollectItem;
import model.plan.MoveTo;
import model.plan.PlaceGadget;
import model.plan.Plan;
import model.plan.Reload;
import model.plan.Shoot;
import model.plan.SwitchWeapon;
import model.plan.UseCover;
import model.plan.UseMovingCover;
import cbr_util.DefaultCases;

/**
 * Diese Klasse stellt die konkrete Verbindung zum myCBR-Projekt her und bietet
 * verschiedene Methoden, um den Retrieve- und Reviseprozess des CBR-Zyklus
 * abzudecken.
 * 
 * @author Jannis Hillmann & Jobst-Julius Bartels & Marcel Kolbe
 *
 */
public class CBREngine {

	/**
	 * Singleton Design Pattern, um die CBREngine überall einsetzen zu können
	 * und um so überall ein Retrieval durchfuehren zu k&ouml;nnen.
	 */
	private static CBREngine cbrEngine;

	/**
	 * Attribut, um das myCBR-Projekt zu speichern.
	 */
	private static Project cbrProject = null;
	
	/**
	 * Liste, um alle CaseBases speichern zu koennen.
	 */
	private ArrayList<DefaultCaseBase> caseBases;

	/**
	 * Liste, um die entstehenden Instanzen zur Laufzeit bearbeiten zu koennen
	 */
	private ArrayList<Instance> caseSequence;
	
	/**
	 * Liste, um die zu einer Instanz gehoerigen Stati temporaer speichern
	 * zu koennen. 
	 */
	private ArrayList<Status> tempStati = new ArrayList<>();

	/**
	 * Attribut, um das Status-Konzept zu speichern.
	 */
	private Concept statusConcept;
	/**
	 * Konstanter String, um den Applikationsnamen zu speichern.
	 */
	private static final String APPLICATION_NAME = "CBRS";
	/**
	 * Konstanter String, um den Projektnamen zu speichern.
	 */
	private static final String PROJECT_NAME = "CBRS.prj";
	/**
	 * Konstanter String, um den Konzeptnamen zu speichern.
	 */
	private static final String CONCEPT_NAME = "Status";
	
	/**
	 * Globales Attribut "Boolean Gegner sichtbar" zur dynamischen Gewichtung der Situation zur Laufzeit
	 */
	private static int isEnemyVisibleAttrW = 100;
	
	/**
	 * Globales Attribut "Letzte Position" zur dynamischen Gewichtung der Situation zur Laufzeit
	 */
	private static int lastPositionAttrW = 100;
	
	/**
	 * Globales Attribut "Boolean ob Gegner lebt" zur dynamischen Gewichtung der Situation zur Laufzeit
	 */
	private static int isEnemyAliveAttrW = 100;
	
	/**
	 * Globales Attribut "Zustand Eigenes Leben" zur dynamischen Gewichtung der Situation zur Laufzeit
	 */
	private static int ownHealthAttrW = 100;
	
	/**
	 * Globales Attribut "Zustand Ausgerüstete Waffe" zur dynamischen Gewichtung der Situation zur Laufzeit
	 */
	private static int equippedWeaponAttrW = 100;
	
	/**
	 * Globales Attribut "Zustand der aktuellen Ammunation" zur dynamischen Gewichtung der Situation zur Laufzeit
	 */
	private static int currentAmmuAttrW = 100;
	
	/**
	 * Globales Attribut "Zustand der grundsätzlichen aktuellen Ammunation" zur dynamischen Gewichtung der Situation zur Laufzeit
	 */
	private static int currentOVAmmuAttrW = 100;
	
	/**
	 * Globales Attribut "Bolean der Notwendigkeit nach einer Waffe" zur dynamischen Gewichtung der Situation zur Laufzeit
	 */
	private static int isWeaponNeedAttrW = 100;
	
	/**
	 * Globales Attribut "Boolean der Notwendigkeit nach Ammunation" zur dynamischen Gewichtung der Situation zur Laufzeit
	 */
	private static int isAmmuNeedAttrW = 100;
	
	/**
	 * Globales Attribut "Boolean der Notwendigkeit nach Leben" zur dynamischen Gewichtung der Situation zur Laufzeit
	 */
	private static int isHealthNeedAttrW = 100;
	
	/**
	 * Globales Attribut "Boolean der Notwendigkeit der Deckung" zur dynamischen Gewichtung der Situation zur Laufzeit
	 */
	private static int isCoverNeedAttrW = 100;
	
	/**
	 * Globales Attribut "Boolean der Deckung" zur dynamischen Gewichtung der Situation zur Laufzeit
	 */
	private static int isCoveredAttrW = 0;
	
	/**
	 * Globales Attribut "Distanz zur Waffe" zur dynamischen Gewichtung der Situation zur Laufzeit
	 */
	private static int distToWeaponAttrW = 100;
	
	/**
	 * Globales Attribut "Distanz zur Ammunation" zur dynamischen Gewichtung der Situation zur Laufzeit
	 */
	private static int distToAmmuAttrW = 100;
	
	/**
	 * Globales Attribut "Distanz zum Leben" zur dynamischen Gewichtung der Situation zur Laufzeit
	 */
	private static int distToHealthAttrW = 100;
	
	/**
	 * Globales Attribut "Distanz zur Deckung" zur dynamischen Gewichtung der Situation zur Laufzeit
	 */
	private static int distToCoverAttrW = 100;
	
	/**
	 * Globales Attribut "Distanz zum Gegner" zur dynamischen Gewichtung der Situation zur Laufzeit
	 */
	private static int distToEnemyAttrW = 100;
	
	/**
	 * Globales Attribut "Distanz zur bewegenden Deckung" zur dynamischen Gewichtung der Situation zur Laufzeit
	 */
	private static int distToMovingCoverAttrW = 100;
	
	/**
	 * Globales Attribut "Distanz zum großen Munitionsnachschub" zur dynamischen Gewichtung der Situation zur Laufzeit
	 */
	private static int distToAmmuLargeAttrW = 100;
	
	/**
	 * Globales Attribut "Boolean des Gadget Claymore" zur dynamischen Gewichtung der Situation zur Laufzeit
	 */
	private static int isGadgetNeededAttrW = 100;
	
	/**
	 * Globales Attribut "winChance" zur dynamischen Gewichtung der Situation zur Laufzeit
	 */
	private static int winChanceAttrW = 100;
	
	/**
	 * Globales Attribut "killDeathRatio" zur dynamischen Gewichtung der Situation zur Laufzeit
	 */
	private static int killDeathAttrW = 100;
	
	/**
	 * Globales Attribut "upTime" zur dynamischen Gewichtung der Situation zur Laufzeit
	 */
	private static int upTimeAttrW = 100;
	
	/**
	 * Globales Attribut "Plan" zur dynamischen Gewichtung der Situation zur Laufzeit
	 */
	private static int planAttrW = 0;
	
	/**
	 * Globales Attribut "Qualitaet des Falles" zur dynamischen Gewichtung der Situation zur Laufzeit
	 */
	private static int qualityAttrW = 0;
	
	
	/**
	 * Ein Zaehler der zur Laufzeit des System angibt welches die Aktuelle Sequenz ist
	 */
	private static int sequencecounter = 0;
	
	/**
	 * Ein Zaehler der zur Laufzeit des System die Fälle in der jeweiligen Sequenz angibt
	 */
	private static int casecounter = 0;
	
	
	/**
	 * Parameter die mittels unterschiedlicher Zeitpunkte für die weiterverarbeitung der Daten benoetigt werden
	 * 
	 * oldUpTime - Wird benoetigt um direkten Tot der CBR Ki zu bestimmen
	 * newUpTime - Wird benoetigt um moegliche Verbesserungen im Laufzeitverhalten festzustellen
	 * sequenceUpTime - Gibt an, ob die aktuelle Sequenz laenger und somit theoretisch besser ist
	 * 
	 */
	private static double oldUpTime = 0.0;
	private static double newUpTime = 0.0; 
	private static double sequenceUpTime = 0.0;
	
	/**
	 * Ein für Testreihen anpassbarer Wert.
	 * Dieser ist indirekt abhaengig von dem in der Loeschlogik festgelegten
	 * Grenzwert den die Fallbasis nicht unterschreiten sollte. 
	 */
	private static int minCBSize = 50;

	/**
	 * Einziger Konstruktor fuer die Klasse, der fuer das
	 * Singleton-Designpattern angepasst wurde und daher die Sichtbarkeit
	 * <tt>private</tt> besitzt. Innerhalb des Konstruktors wird die
	 * myCBR-Projektdatei und das sich darin befindene Statuskonzept eingelesen.
	 */
	private CBREngine() {

		try {

			caseBases = new ArrayList<>();

			File f1 = new File(System.getProperty("user.dir")).getParentFile();
			File f2 = new File(f1.getAbsolutePath());
			File f3 = new File(f2.getPath(), APPLICATION_NAME);
			String projektPfad = new File(f3.getPath(), PROJECT_NAME).getAbsolutePath();

			cbrProject = new Project(projektPfad).getProject();
			
			while (cbrProject.isImporting()) {
				Thread.sleep(200);
			}

		} catch (Exception e) {
			System.out.println("error: " + e.getMessage());
		}

		setStatusConcept(cbrProject.getConceptByID(CONCEPT_NAME));
		

	}

	/**
	 * Statischer Getter fuer das Singleton-Designpattern. Falls die einzige
	 * Instanz der CBREngine <tt>null</tt> ist, wird der <tt>private</tt>
	 * Konstruktor aufgerufen und das so erzeuge Objekt anschlie&szlig;end
	 * mittels <tt>return</tt> zurueckgegeben..
	 * 
	 * @return Die einzige Instanz der Klasse <tt>{@link CBREngine}</tt>.
	 */
	public static CBREngine getInstance() {
		if (cbrEngine == null) {
			cbrEngine = new CBREngine();
		}
		return cbrEngine;
	}

	/**
	 * Es folgen simple Getter und Setter fuer die Membervariablen.
	 */
	public Project getCbrProject() {
		return cbrProject;
	}

//	public void setCbrProject(Project cbrProject) {
//		this.cbrProject = cbrProject;
//	}

	public ArrayList<DefaultCaseBase> getCaseBases() {
		return caseBases;
	}

	public Concept getStatusConcept() {
		return statusConcept;
	}

	public void setStatusConcept(Concept statusConcept) {
		this.statusConcept = statusConcept;
	}

	/**
	 * Methode um zu ueberpruefen, ob fuer einen bestimmten Spieler
	 * bereits eine Fallbasis existiert.
	 * 
	 * @param name
	 *            Der Name des Spielers.
	 * @return <tt>True</tt>, falls bereits eine Fallbasis existiert,
	 *         <tt>false</tt>, falls nicht.
	 */
	public boolean caseBaseForPlayerAlreadyExists(String name) {
		return cbrProject.hasCB(name);
	}

	/**
	 * Diese Methode erstellt eine Fallbasis fuer einen gegebenen Namen (der
	 * Name des Spielers).
	 * 
	 * @param name
	 *            Der Name des Spielers, fuer den eine Fallbasis erstellt
	 *            werden soll.
	 * @throws Exception
	 *             Die Exception kann geworfen werden, falls keine Fallbasis
	 *             erstellt werden kann (falls das Projekt nicht korrekt geladen
	 *             wurde).
	 */
	public void createCaseBaseForPlayer(String name) throws Exception {
		caseBases.add(cbrProject.createDefaultCB(name));
	}

	/**
	 * Diese Methode ermittelt alle Faelle fuer einen Spieler und gibt
	 * diese als <tt>Collection</tt> zurueck.
	 * 
	 * @param name
	 *            Der Name des Spielers.
	 * @return Die Liste mit allen Faellen fuer den gegebenen Spieler.
	 */
	public Collection<Instance> getCasesForPlayer(String name) {
		return cbrProject.getCB(name).getCases();
	}

	/**
	 * Diese Methode ermittelt, ob die Fallbasis des gegebenen Spielers leer
	 * ist.
	 * 
	 * @param name
	 *            Der Name des Spielers.
	 * @return <tt>True</tt>, falls die Fallbasis leer ist, <tt>false</tt>,
	 *         falls nicht.
	 */
	public boolean isCaseBaseEmpty(String name) {
		return cbrProject.getCB(name).getCases().isEmpty();
	}

	/**
	 * Diese Methode erstellt aus einem uebergebenen Status und einem Plan
	 * einen neuen Fall und gibt diesen dann zurueck.
	 * 
	 * @param status
	 *            Der Status, in dem sich der Spieler befindet.
	 * @param plan
	 *            Der vorgeschlagene Plan.
	 * @param name
	 *            Der Name der Instanz.
	 * @return Die aus den Parametern erstellte Instanz.
	 * @throws Exception
	 *             Falls auf das Konzept nicht zugegriffen werden kann.
	 */
	private Instance createInstance(Status status, String plan, String name) throws Exception {
		Instance instance = statusConcept.addInstance(name);

		instance.addAttribute(RetrievalHelper.IS_ENEMY_VISIBLE_DESC, status.isEnemyVisible());
		instance.addAttribute(RetrievalHelper.DISTANCE_TO_ENEMY_DESC, status.getDistanceToEnemyAsEnum().name());
		instance.addAttribute(RetrievalHelper.LAST_POSITION_DESC, status.getLastPositionAsEnum().name());
		instance.addAttribute(RetrievalHelper.IS_ENEMY_ALIVE_DESC, status.isEnemyAlive());
		instance.addAttribute(RetrievalHelper.OWN_HEALTH_DESC, status.getOwnHealthAsEnum().name());
		instance.addAttribute(RetrievalHelper.EQUIPPED_WEAPON_DESC, status.getEquippedWeapon());
		instance.addAttribute(RetrievalHelper.CURRENT_AMMUNITION_DESC, status.getCurrentAmmunitionAsEnum().name());
		instance.addAttribute(RetrievalHelper.CURRENT_OVERALL_AMMUNITION_DESC, status.getCurrentOverallAmmunitionAsEnum().name());
		instance.addAttribute(RetrievalHelper.IS_WEAPON_NEEDED_DESC, status.isWeaponNeeded());
		instance.addAttribute(RetrievalHelper.IS_AMMUNITION_NEEDED_DESC, status.isAmmunitionNeeded());
		instance.addAttribute(RetrievalHelper.IS_HEALTH_NEEDED_DESC, status.isHealthNeeded());
		instance.addAttribute(RetrievalHelper.IS_COVER_NEEDED_DESC, status.isCoverNeeded());
		instance.addAttribute(RetrievalHelper.IS_COVERED_DESC, status.isCovered());
		instance.addAttribute(RetrievalHelper.DISTANCE_TO_WEAPON_DESC, status.getWeaponDistanceAsEnum().name());
		instance.addAttribute(RetrievalHelper.DISTANCE_TO_AMMUNITION_DESC, status.getAmmunitionDistanceAsEnum().name());
		instance.addAttribute(RetrievalHelper.DISTANCE_TO_HEALTH_DESC, status.getHealthDistanceAsEnum().name());
		instance.addAttribute(RetrievalHelper.DISTANCE_TO_COVER_DESC, status.getCoverDistanceAsEnum().name());
		instance.addAttribute(RetrievalHelper.WINCHANCE_DESC, status.getWinChanceAsEnum().name());
		instance.addAttribute(RetrievalHelper.KILLDEATHRATIO_DESC, status.getKillDeathRatioAsEnum().name());
		instance.addAttribute(RetrievalHelper.UPTIME_DESC, status.getUpTimeAsEnum().name());

		instance.addAttribute(RetrievalHelper.AMMUNITION_LARGE_DISTANCE_DESC, status.getAmmunitionLargeDistanceAsEnum().name());
		instance.addAttribute(RetrievalHelper.MOVING_COVER_DISTANCE_DESC, status.getMovingCoverDistanceAsEnum().name());
		instance.addAttribute(RetrievalHelper.IS_GADGET_NEEDED_DESC, status.isGadgetNeeded());
		
		instance.addAttribute(RetrievalHelper.PLAN_DESC, plan);
		instance.addAttribute(RetrievalHelper.QUALITY_DESC, 0);


		return instance;
	}
	
	/**
	 * Diese Methode fuegt die Start(Default)faelle der Fallbasis des
	 * Spielers hinzu.
	 * 
	 * @param name
	 *            Der Name des Spielers.
	 * @throws Exception
	 *             Falls auf das Projekt nicht zugegriffen werden kann.
	 */
	public void addDefaultCases(String name) throws Exception {
		Collection<Instance> instances = cbrProject.getCB(name).getCases();
		Instance instance;
		Status status; 
		
		String plan;
		int counter = instances.size();

		HashMap<Status, String> defaultCases = DefaultCases.getDefaultCases();

		for (Map.Entry<Status, String> entry : defaultCases.entrySet()) {
			status = entry.getKey();
			
			plan = entry.getValue();

			instance = createInstance(status, plan, "s" + counter++);

			cbrProject.getCB(name).addCase(instance);

		}
	}
	
	
	
	
	
// BIS HIER HIN HAENGT ALLES MIT DEM EINLESEN UND SPEICHERN VON FAELLEN ZUSAMMEN AB HIER GEHT ES UM DIE FALL/PLANMANIPULATION	
	
	
	
	
	/**
	 * Diese Methode befuellt eine gegebene Instanz mit den konkreten
	 * Werten, die aus einem gegebenen Status gezogen werden k&ouml;nnen.
	 * 
	 * @param instance
	 *            Die Instanz, die befuellt werden soll.
	 * @param status
	 *            Der Status, aus dem die konkreten Werte gezogen werden.
	 * @return Die nun befuellte Instanz.
	 */
	private Instance fillInstance(Instance instance, Status status) {

		instance.addAttribute(RetrievalHelper.CURRENT_AMMUNITION_DESC.getName(),
				RetrievalHelper.CURRENT_AMMUNITION_DESC.getAttribute(status.getCurrentAmmunitionAsEnum().name()));
		instance.addAttribute(RetrievalHelper.CURRENT_OVERALL_AMMUNITION_DESC.getName(),
				RetrievalHelper.CURRENT_OVERALL_AMMUNITION_DESC
						.getAttribute(status.getCurrentOverallAmmunitionAsEnum().name()));
		instance.addAttribute(RetrievalHelper.DISTANCE_TO_AMMUNITION_DESC.getName(),
				RetrievalHelper.DISTANCE_TO_AMMUNITION_DESC.getAttribute(status.getAmmunitionDistanceAsEnum().name()));
		instance.addAttribute(RetrievalHelper.DISTANCE_TO_COVER_DESC.getName(),
				RetrievalHelper.DISTANCE_TO_COVER_DESC.getAttribute(status.getCoverDistanceAsEnum().name()));
		instance.addAttribute(RetrievalHelper.DISTANCE_TO_ENEMY_DESC.getName(),
				RetrievalHelper.DISTANCE_TO_ENEMY_DESC.getAttribute(status.getDistanceToEnemyAsEnum().name()));
		instance.addAttribute(RetrievalHelper.DISTANCE_TO_HEALTH_DESC.getName(),
				RetrievalHelper.DISTANCE_TO_HEALTH_DESC.getAttribute(status.getHealthDistanceAsEnum().name()));
		instance.addAttribute(RetrievalHelper.DISTANCE_TO_WEAPON_DESC.getName(),
				RetrievalHelper.DISTANCE_TO_WEAPON_DESC.getAttribute(status.getWeaponDistanceAsEnum().name()));
		instance.addAttribute(RetrievalHelper.EQUIPPED_WEAPON_DESC.getName(),
				RetrievalHelper.EQUIPPED_WEAPON_DESC.getAttribute(status.getEquippedWeapon()));
		instance.addAttribute(RetrievalHelper.LAST_POSITION_DESC.getName(),
				RetrievalHelper.LAST_POSITION_DESC.getAttribute(status.getLastPositionAsEnum().name()));
		instance.addAttribute(RetrievalHelper.OWN_HEALTH_DESC.getName(),
				RetrievalHelper.OWN_HEALTH_DESC.getAttribute(status.getOwnHealthAsEnum().name()));
		instance.addAttribute(RetrievalHelper.IS_AMMUNITION_NEEDED_DESC.getName(),
				RetrievalHelper.IS_AMMUNITION_NEEDED_DESC.getAttribute(status.isAmmunitionNeeded()));
		instance.addAttribute(RetrievalHelper.IS_COVER_NEEDED_DESC.getName(),
				RetrievalHelper.IS_COVER_NEEDED_DESC.getAttribute(status.isCoverNeeded()));
		instance.addAttribute(RetrievalHelper.IS_COVERED_DESC.getName(),
				RetrievalHelper.IS_COVERED_DESC.getAttribute(status.isCovered()));
		instance.addAttribute(RetrievalHelper.IS_ENEMY_ALIVE_DESC.getName(),
				RetrievalHelper.IS_ENEMY_ALIVE_DESC.getAttribute(status.isEnemyAlive()));
		instance.addAttribute(RetrievalHelper.IS_ENEMY_VISIBLE_DESC.getName(),
				RetrievalHelper.IS_ENEMY_VISIBLE_DESC.getAttribute(status.isEnemyVisible()));
		instance.addAttribute(RetrievalHelper.IS_HEALTH_NEEDED_DESC.getName(),
				RetrievalHelper.IS_HEALTH_NEEDED_DESC.getAttribute(status.isHealthNeeded()));
		instance.addAttribute(RetrievalHelper.IS_WEAPON_NEEDED_DESC.getName(),
				RetrievalHelper.IS_WEAPON_NEEDED_DESC.getAttribute(status.isWeaponNeeded()));
		instance.addAttribute(RetrievalHelper.WINCHANCE_DESC.getName(),
				RetrievalHelper.WINCHANCE_DESC.getAttribute(status.getWinChanceAsEnum().name()));
		instance.addAttribute(RetrievalHelper.KILLDEATHRATIO_DESC.getName(), 
				RetrievalHelper.KILLDEATHRATIO_DESC.getAttribute(status.getKillDeathRatioAsEnum().name()));
		instance.addAttribute(RetrievalHelper.UPTIME_DESC.getName(),
				RetrievalHelper.UPTIME_DESC.getAttribute(status.getUpTimeAsEnum().name()));

			instance.addAttribute(RetrievalHelper.AMMUNITION_LARGE_DISTANCE_DESC.getName(),
					RetrievalHelper.AMMUNITION_LARGE_DISTANCE_DESC.getAttribute(status.getAmmunitionLargeDistanceAsEnum().name()));
			instance.addAttribute(RetrievalHelper.MOVING_COVER_DISTANCE_DESC.getName(),
					RetrievalHelper.MOVING_COVER_DISTANCE_DESC.getAttribute(status.getMovingCoverDistanceAsEnum().name()));	
			instance.addAttribute(RetrievalHelper.IS_GADGET_NEEDED_DESC.getName(),
					RetrievalHelper.IS_GADGET_NEEDED_DESC.getAttribute(status.isGadgetNeeded()));		

		updateAttributeWeight(status);
		//tempStati.add(status);
		//temporary disabled
		setWeightForAttr(instance, RetrievalHelper.IS_COVERED_DESC, isCoveredAttrW);
		setWeightForAttr(instance, RetrievalHelper.PLAN_DESC, planAttrW);
		setWeightForAttr(instance, RetrievalHelper.QUALITY_DESC, qualityAttrW);
		
		//Alle Werte werden hier initial gesetzt.
		setWeightForAttr(instance, RetrievalHelper.CURRENT_AMMUNITION_DESC, currentAmmuAttrW); 
		setWeightForAttr(instance, RetrievalHelper.CURRENT_OVERALL_AMMUNITION_DESC, currentOVAmmuAttrW);
		setWeightForAttr(instance, RetrievalHelper.DISTANCE_TO_AMMUNITION_DESC, distToAmmuAttrW);
		setWeightForAttr(instance, RetrievalHelper.DISTANCE_TO_COVER_DESC, distToCoverAttrW);
		setWeightForAttr(instance, RetrievalHelper.DISTANCE_TO_ENEMY_DESC, distToEnemyAttrW);
		setWeightForAttr(instance, RetrievalHelper.DISTANCE_TO_HEALTH_DESC, distToHealthAttrW);
		setWeightForAttr(instance, RetrievalHelper.DISTANCE_TO_WEAPON_DESC, distToWeaponAttrW);
		setWeightForAttr(instance, RetrievalHelper.EQUIPPED_WEAPON_DESC, equippedWeaponAttrW);
		setWeightForAttr(instance, RetrievalHelper.IS_AMMUNITION_NEEDED_DESC, isAmmuNeedAttrW);
		setWeightForAttr(instance, RetrievalHelper.IS_COVER_NEEDED_DESC, isCoverNeedAttrW);
		//Hier wäre iscovered bool, mal gucken ob das geht
		setWeightForAttr(instance, RetrievalHelper.IS_ENEMY_ALIVE_DESC, isEnemyAliveAttrW);
		setWeightForAttr(instance, RetrievalHelper.IS_HEALTH_NEEDED_DESC, isHealthNeedAttrW);
		setWeightForAttr(instance, RetrievalHelper.IS_ENEMY_VISIBLE_DESC, isEnemyVisibleAttrW);
		setWeightForAttr(instance, RetrievalHelper.IS_WEAPON_NEEDED_DESC, isWeaponNeedAttrW);
		setWeightForAttr(instance, RetrievalHelper.KILLDEATHRATIO_DESC, killDeathAttrW);
		setWeightForAttr(instance, RetrievalHelper.LAST_POSITION_DESC, lastPositionAttrW);
		setWeightForAttr(instance, RetrievalHelper.OWN_HEALTH_DESC, ownHealthAttrW);
		//hier wären plan -
		//und quality Attributsetter		
		setWeightForAttr(instance, RetrievalHelper.UPTIME_DESC, upTimeAttrW);
		setWeightForAttr(instance, RetrievalHelper.WINCHANCE_DESC, winChanceAttrW);
		
		setWeightForAttr(instance, RetrievalHelper.AMMUNITION_LARGE_DISTANCE_DESC, distToAmmuLargeAttrW);
		setWeightForAttr(instance, RetrievalHelper.MOVING_COVER_DISTANCE_DESC, distToMovingCoverAttrW);
		setWeightForAttr(instance, RetrievalHelper.IS_GADGET_NEEDED_DESC, isGadgetNeededAttrW);
		flush();
		return instance;
		
	}
	
	
	
	/**
	 * Diese Methode ermittelt mit dem Übergebenen Status die notwendige Gewichtugn der Attribute.
	 * @param status
	 */
	public void updateAttributeWeight(Status status) {
		
		
		
		if(!(status.isEnemyVisible()) ) {//&& !(status.isEnemyAlivee())) {
			
			isEnemyAliveAttrW = 900;
			isEnemyVisibleAttrW = 700;
			
			if(status.isWeaponNeeded()) {
				
				isWeaponNeedAttrW = 1000;
				distToWeaponAttrW = 500;
				equippedWeaponAttrW = 750;
				
			}
				
			if(status.isHealthNeeded()) {
					
				isHealthNeedAttrW = 900;
				distToHealthAttrW = 500;
					
			}
			
			// Wenn Munition benötigt wird - Möglichkeit zwischen collect<ammunition> und collect<ammunitionLarge>
			if(status.isAmmunitionNeeded()) {
				
		
				isAmmuNeedAttrW = 250;
				//equippedWeaponAttrW = 200;				
				
			}
			// Wenn das Gadget benötigt wird und die Entfernung zur großen Munitionskiste nah/mittel ist, soll er diese priorisieren
			if(status.isGadgetNeeded() && ((status.getAmmunitionLargeDistanceAsEnum().name() == "middle") || (status.getAmmunitionLargeDistanceAsEnum().name() == "near"))) {
				
				isGadgetNeededAttrW = 500;
				// Munition sollte dem Gadget vorgezogen werden
				distToAmmuLargeAttrW = 700;
			}
			
			// Wenn sich der Spieler in Deckung befindet soll der Einsatz des Gadgets ermöglicht werden
			if(status.isCovered()) {
				
				isCoveredAttrW = 800;
				isGadgetNeededAttrW = 800;					
			}
			
		} else if( status.isEnemyVisible() ) { // && status.isEnemyAlive()) {
			
			isEnemyAliveAttrW = 600;
			isEnemyVisibleAttrW = 500;
			
			if(status.getWeaponDistanceAsEnum().name() == "near") {
				
				isEnemyAliveAttrW = 100;
				isEnemyVisibleAttrW = 100;
				distToWeaponAttrW = 300; 
				isWeaponNeedAttrW = 300;
				distToHealthAttrW = 100; 
				isHealthNeedAttrW = 100;
				
			} else if(status.getHealthDistanceAsEnum().name() == "near") {
				
				isEnemyAliveAttrW = 100;
				isEnemyVisibleAttrW = 100;
				distToHealthAttrW = 750; 
				isHealthNeedAttrW = 1000;
				distToWeaponAttrW = 100; 
				isWeaponNeedAttrW = 100;
			}
			
			// Defensiv - Wenn Deckung gebraucht wird, werden die Gewichte für die Covers erhöht.
			// Möglichkeit zwischen UseDefaultCover und UseMovingCover
		} else if(status.isCoverNeeded()) {
			
			distToMovingCoverAttrW = 700;
			distToCoverAttrW = 700;
			isCoverNeedAttrW = 750;
		}
		
		
//			} else if(status.getWinChanceAsEnum().name() == "loosing") {
//				
//				winChanceAttrW = 400; 
//				isCoverNeedAttrW = 700; 
//				isHealthNeedAttrW = 700;
//				isWeaponNeedAttrW = 100;
//				
//				
//			}
		}
	

	
	public void flush() {
		
		 isEnemyVisibleAttrW = 100;
		 lastPositionAttrW = 100;
		 isEnemyAliveAttrW = 100;
		 ownHealthAttrW = 100;
		 equippedWeaponAttrW = 100;
		 currentAmmuAttrW = 100;
		 currentOVAmmuAttrW = 100;
		 isWeaponNeedAttrW = 100;
		 isAmmuNeedAttrW = 100;
		 isHealthNeedAttrW = 100;
		 isCoverNeedAttrW = 100;
		 isCoveredAttrW = 0;
		 distToWeaponAttrW = 100;
		 distToAmmuAttrW = 100;
		 distToHealthAttrW = 100;
		 distToCoverAttrW = 100;
		 distToEnemyAttrW = 100;
		 winChanceAttrW = 100;
		 killDeathAttrW = 100;
		 upTimeAttrW = 100;
		 planAttrW = 0;
		 qualityAttrW = 0;
		 distToMovingCoverAttrW = 100;
		 distToAmmuLargeAttrW = 100;
		 isGadgetNeededAttrW = 100;
	}
	

	/**
	 * Diese Methode veraendert das Gewicht eines Attributs einer Instanz um
	 * den uebergebenen Wert.
	 * 
	 * @param instance
	 *            Die Instanz, dessen Attribut veraendert werden soll.
	 * @param attr
	 *            Das Attribut, was veraendert werden soll.
	 * @param weight
	 *            Die neue Gewichtung des Attributs.
	 */
	private void setWeightForAttr(Instance instance, AttributeDesc attr, int weight) {

		for (AttributeDesc desc : instance.getAttributes().keySet()) {
			if (desc.getName().contains(attr.getName())) {
				System.out.println("sett attr weight for: " + desc.getName() + " to: " + weight);
				statusConcept.getActiveAmalgamFct().setWeight(desc, weight);
				if (weight == 0) {
					statusConcept.getActiveAmalgamFct().setActive(desc, false);
				}
				break;
			}
		}
	}

	/**
	 * Diese Methode fuehrt anhand einer gegebenen Anfrage ein Retrieval
	 * aus, fuehrt den Reviseprozess aus und gibt den vorgeschlagenen Plan
	 * in Form eines <tt>{@link Response}</tt> Objekts zurueck.
	 * 
	 * @param request
	 *            Die gegebene Anfrage.
	 * @return Der vorgeschlagene Plan in Form eines Response-Objekts.
	 */
	public Response executeRetrieval(Request request) {

		Response response = new Response();
		response.getSituation().setPlayer(request.getSituation().getPlayer());
		response.getSituation().setPlayerStatus(new Status());

		String cbName = request.getSituation().getPlayer();

		Retrieval retrieval = new Retrieval(statusConcept, cbrProject.getCB(cbName));
		retrieval.setRetrievalMethod(RetrievalMethod.RETRIEVE_SORTED);

		Instance query = retrieval.getQueryInstance();

		query = fillInstance(query, request.getSituation().getPlayerStatus());
		
		tempStati.add(request.getSituation().getPlayerStatus());

		retrieval.start();
		
		List<Pair<Instance, Similarity>> resultate = retrieval.getResult();

		int counter = 0;
		boolean found = false;
		Instance currentInstance;
		Plan plan = new Plan();
		
		Instance handleInstance = null;
		

		while (counter < resultate.size() && !found) {
		
			System.out.println("counter: " + counter);
			
			currentInstance = resultate.get(counter).getFirst();
			

			response.getSituation().getPlayerStatus().setCurrentAmmu(StatusHelper.getCurrentAmmuByString(
					currentInstance.getAttForDesc(RetrievalHelper.CURRENT_AMMUNITION_DESC).getValueAsString()));

			response.getSituation().getPlayerStatus().setCurrentOverallAmmu(StatusHelper.getCurrentOverallAmmuByString(
					currentInstance.getAttForDesc(RetrievalHelper.CURRENT_OVERALL_AMMUNITION_DESC).getValueAsString()));

			response.getSituation().getPlayerStatus()
					.setAmmunitionDistance(StatusHelper.getDistanceToAmmunitionByString(currentInstance
							.getAttForDesc(RetrievalHelper.DISTANCE_TO_AMMUNITION_DESC).getValueAsString()));

			response.getSituation().getPlayerStatus().setCoverDistance(StatusHelper.getDistanceToCoverByString(
					currentInstance.getAttForDesc(RetrievalHelper.DISTANCE_TO_COVER_DESC).getValueAsString()));

			response.getSituation().getPlayerStatus().setDistanceToEnemy(StatusHelper.getDistanceToEnemyByString(
					currentInstance.getAttForDesc(RetrievalHelper.DISTANCE_TO_ENEMY_DESC).getValueAsString()));

			response.getSituation().getPlayerStatus().setHealthDistance(StatusHelper.getDistanceToHealthByString(
					currentInstance.getAttForDesc(RetrievalHelper.DISTANCE_TO_HEALTH_DESC).getValueAsString()));

			response.getSituation().getPlayerStatus().setWeaponDistance(StatusHelper.getDistanceToWeaponByString(
					currentInstance.getAttForDesc(RetrievalHelper.DISTANCE_TO_WEAPON_DESC).getValueAsString()));

			response.getSituation().getPlayerStatus().setLastPosition(StatusHelper.getLastPositionByString(
					currentInstance.getAttForDesc(RetrievalHelper.LAST_POSITION_DESC).getValueAsString()));

			response.getSituation().getPlayerStatus().setOwnHealth(StatusHelper.getOwnHealthByString(
					currentInstance.getAttForDesc(RetrievalHelper.OWN_HEALTH_DESC).getValueAsString()));

			response.getSituation().getPlayerStatus().setEquippedWeapon(
					currentInstance.getAttForDesc(RetrievalHelper.EQUIPPED_WEAPON_DESC).getValueAsString());

			response.getSituation().getPlayerStatus().setAmmunitionNeeded(Boolean.parseBoolean(
					currentInstance.getAttForDesc(RetrievalHelper.IS_WEAPON_NEEDED_DESC).getValueAsString()));

			response.getSituation().getPlayerStatus().setCoverNeeded(Boolean.parseBoolean(
					currentInstance.getAttForDesc(RetrievalHelper.IS_COVER_NEEDED_DESC).getValueAsString()));

			response.getSituation().getPlayerStatus().setCovered(Boolean
					.parseBoolean(currentInstance.getAttForDesc(RetrievalHelper.IS_COVERED_DESC).getValueAsString()));

			response.getSituation().getPlayerStatus().setEnemyAlive(Boolean.parseBoolean(
					currentInstance.getAttForDesc(RetrievalHelper.IS_ENEMY_ALIVE_DESC).getValueAsString()));

			response.getSituation().getPlayerStatus().setEnemyVisible(Boolean.parseBoolean(
					currentInstance.getAttForDesc(RetrievalHelper.IS_ENEMY_VISIBLE_DESC).getValueAsString()));

			response.getSituation().getPlayerStatus().setHealthNeeded(Boolean.parseBoolean(
					currentInstance.getAttForDesc(RetrievalHelper.IS_HEALTH_NEEDED_DESC).getValueAsString()));

			response.getSituation().getPlayerStatus().setWeaponNeeded(Boolean.parseBoolean(
					currentInstance.getAttForDesc(RetrievalHelper.IS_WEAPON_NEEDED_DESC).getValueAsString()));
			
			response.getSituation().getPlayerStatus().setWinChance(StatusHelper.getWinChanceAsString(
					currentInstance.getAttForDesc(RetrievalHelper.WINCHANCE_DESC).getValueAsString()));
			
			response.getSituation().getPlayerStatus().setKillDeathRatio(StatusHelper.getKillDeathRatioAsString(
					currentInstance.getAttForDesc(RetrievalHelper.KILLDEATHRATIO_DESC).getValueAsString()));
			
			response.getSituation().getPlayerStatus().setUpTime(StatusHelper.getUpTimeAsString(
					currentInstance.getAttForDesc(RetrievalHelper.UPTIME_DESC).getValueAsString()));
			
			response.getSituation().getPlayerStatus().setIsGadgetNeeded(Boolean.parseBoolean(
					currentInstance.getAttForDesc(RetrievalHelper.IS_GADGET_NEEDED_DESC).getValueAsString()));
			
			response.getSituation().getPlayerStatus().setMovingCoverDistance(StatusHelper.getDistanceToMovingCoverByString(
					currentInstance.getAttForDesc(RetrievalHelper.MOVING_COVER_DISTANCE_DESC).getValueAsString()));
			
			response.getSituation().getPlayerStatus().setAmmunitionLargeDistance(StatusHelper.getDistanceToGadgetByString(
					currentInstance.getAttForDesc(RetrievalHelper.AMMUNITION_LARGE_DISTANCE_DESC).getValueAsString()));

			plan.setActionsAsString(currentInstance.getAttForDesc(RetrievalHelper.PLAN_DESC).getValueAsString());

			if (planExecutable(plan, request.getSituation().getPlayerStatus())) {
				
				response.setPlan(plan);

				System.out.println(
						"Due to the situation, I would do the following: " + response.getPlan().getActionsAsString());
				System.out.println("Sim is: " + resultate.get(counter).getSecond().getValue() * 100);
				found = true;
				
			} else {
				System.out.println("Following plan was not permitted: " + plan.getActionsAsString());
			}
			counter++;
			handleInstance = currentInstance;	
			
		}
		if (!found) {
			plan.setActionsAsString("MoveTo;Shoot");
			response.setPlan(plan);
			System.out.println("Could not find a good case... just move and shoot!");
		}

		updateCasebase(handleInstance, request);
		
//		/*
//		 * Dieser Abschnitt ist für das Hinzufügen des verwendeten Falls 
//		 * in die Fallbasis verantwortlich. Da momentan keine Retain-Phase
//		 * implementiert ist, wird auf diesen Schritt voerst verzeichtet.	
//		 * 
//		 * ram zu cbr Aender, dann wie vorher	
//		 */
//		try {
//			Instance newCase = createInstance(request.getSituation().getPlayerStatus(), plan.getActionsAsString(),
//					"s" + ramProject.getCB(request.getSituation().getPlayer()).getCases().size());
//			
//			ramProject.getCB(request.getSituation().getPlayer()).addCase(newCase);
//			
//		} catch (Exception exc) {
//			exc.printStackTrace();
//		}		

		return response;
	}
	
	/**
	 * Diese Methode gibt die Fallbasis eines Spielers zurueck.
	 * 
	 * @param player
	 *            Der Name des Spielers und der gesuchten Fallbasis.
	 * @return Die gefundene Fallbasis oder <tt>null</tt>, falls fuer den
	 *         Spieler keine Fallbasis existiert.
	 */
	public ICaseBase getCaseBaseForPlayer(String player) {
		ICaseBase cb = null;

		if (cbrProject.hasCB(player)) {
			cb = cbrProject.getCB(player);
		}

		return cb;
	}

	/**
	 * Diese Methode ermittelt, ob ein vorgeschlagener Plan mit dem gegebenen
	 * Status vereinbar und durchfuehrbar ist.
	 * 
	 * @param plan
	 *            Der durch den Retrieval-Prozess vorgeschlagene Plan.
	 * @param status
	 *            Der Status, in dem sich der Spieler befindet.
	 * @return <tt>True</tt>, falls der Plan durchfuehrbar ist,
	 *         <tt>false</tt>, falls nicht.
	 */
	private boolean planExecutable(Plan plan, Status status) {

		List<Action> actions = extractActionsFromStatus(plan.getActionsAsString());
		String dest = "";
		CollectItem cItem = null;

		for (Action action : actions) {
			
			//Collect Health Ablehnung, nur wenn distanz unbekannt, ergo nicht gespawned
			if (action instanceof CollectItem) {
				cItem = (CollectItem) action;
				dest = cItem.getDestination();
				if (dest.contains("health")) {
					if (status.getHealthDistanceAsEnum() == Status.HealthDistance.unknown) {
						return false;
					} else if (!(status.isHealthNeeded())) {
						return false;
					}
					
			//Collect Ammu nicht ,wenn nicht gespawned, ammu full
				} else if (dest.contains("ammu")) {
					if (status.getAmmunitionDistanceAsEnum() == Status.AmmunitionDistance.unknown || status.getCurrentOverallAmmunitionAsEnum() == Status.CurrentOverallAmmunition.full)
					{
						return false;
					}
					
			//Collect weapon nicht, wenn nicht gespawned
				} else if (dest.contains("weapon")) {
					if (status.getWeaponDistanceAsEnum() == Status.WeaponDistance.unknown) {
						return false;
					}
				
			
				//Collect ammuLarge wenn nicht gespawnt oder voll ist
				} else if (dest.contains("ammuLarge")) {
				if (status.getAmmunitionLargeDistanceAsEnum() == Status.AmmunitionLargeDistance.unknown || status.getCurrentOverallAmmunitionAsEnum() == Status.CurrentOverallAmmunition.full
						&& !(status.isGadgetNeeded())) {
					return false;
				}
			}
		
			//Collect Weapon nicht, wenn keine wechselwaffe besessen wird
			} else if (action instanceof SwitchWeapon) {
				if (cItem != null) {
					if (!dest.contains("weapon")) {
						return false;
					}
				} else {
					if (status.isWeaponNeeded()) {
						return false;
					}
				}

			/*Reload Methodik ablehnen, wenn:
			 * alles leer, magazin voll, oder noch viel vorhanden 
			 */
			} else if (action instanceof Reload) {

				boolean reload = status.getCurrentAmmunitionAsEnum() == Status.CurrentAmmunition.full
						|| status.getCurrentOverallAmmunitionAsEnum() == Status.CurrentOverallAmmunition.empty ||status.getCurrentAmmunitionAsEnum() == Status.CurrentAmmunition.much;

				System.out.println("cA: " + status.getCurrentAmmunitionAsEnum());
				System.out.println("coA: " + status.getCurrentOverallAmmunitionAsEnum());

				if (reload) {

					System.out.println("Reload abgelehnt");
					return false;
				}

			} else if (action instanceof UseCover) {
				if (status.isCovered()) {
					return false;
				}
				 //Nur solange der Gegner am Leben ist darf geschossen werden
			} else if (action instanceof Shoot) {
				if (!status.isEnemyAlive() || !status.isEnemyVisible()) {
					System.out.println("Shoot abgelehnt");
					return false;
				}
			}
//			} else if (action instanceof MoveTo && action instanceof Shoot) {
//				if (!status.isEnemyAlive()) {
//					System.out.println("MoveTo;Shoot abgelehnt");
//					return false;
//			}
		}

		return true;
	}



	/**
	 * Diese Methode extrahiert aus einem vorgeschlagenen Plan in der Form
	 * <tt>Action1;Action2;...;ActionN</tt> und gibt die extrahierten Aktionen
	 * in eine Liste und gibt diese anschliessend zurueck.
	 * 
	 * @param str
	 *            Der Plan in der beschriebenen Form.
	 * @return Eine ArrayList mit den extrahierten Aktionen.
	 */
	private ArrayList<Action> extractActionsFromStatus(String str) {

		String[] strings = str.split(";");

		ArrayList<Action> actions = new ArrayList<>();

		for (String s : strings) {
			if (s != "") {

				if (s.contains("Shoot")) {
					actions.add(new Shoot());
				} else if (s.contains("MoveTo")) {
					actions.add(new MoveTo());
				} else if (s.contains("CollectItem")) {
					CollectItem cItem = new CollectItem();

					int posOne = s.indexOf('<');
					int posTwo = s.indexOf('>');
					cItem.setDestination(s.substring(posOne + 1, posTwo));
					actions.add(cItem);
				} else if (s.contains("Reload")) {
					actions.add(new Reload());
				} else if (s.contains("SwitchWeapon")) {
					actions.add(new SwitchWeapon());
				} else if (s.contains("UseCover")) {
					actions.add(new UseCover());
				} else if (s.contains("UseMovingCover")) {
					actions.add(new UseMovingCover());
				} else if (s.contains("PlaceGadget")) {
					actions.add(new PlaceGadget());
				}
			}
		}
		return actions;
	}	
	
	/**
	 * Diese Methode wird im Retrieval nach fertigstellung der Instanz aufgerufen. 
	 * Sie ueberprueft anhand der aktuellen Situationen die Instanzen die sich in der 
	 * Sequenz befinden und gibt sie entweder zum loeschen oder speichern weiter. 
	 * 
	 * 
	 * @param handleInstance - Erzeugte instanz, diese wird so lange beschrieben, 
	 * 			bis die CBR KI einen kill macht, oder eliminiert wird.
	 * 
	 * @param request - Die uebergebene request aus Unity, dieser Parameter gewaehrleistet den 
	 * Zugriff auf Variablen zur Laufzeit. 
	 */
	public void updateCasebase(Instance handleInstance, Request request) {
		
		if(handleInstance != null) {	
			double deathcheck = 0.0; 
			double currentUpTime = request.getSituation().getPlayerStatus().getUpTime();
			double winChance = request.getSituation().getPlayerStatus().getWinChance();
			
			if(caseSequence == null) {
				caseSequence = new ArrayList<Instance>();	
			}

			if(currentUpTime != 0.0) {
				
				deathcheck = currentUpTime;  
				newUpTime = currentUpTime;
				caseSequence.add(handleInstance);
				
				
				caseBaseLog("Sequencenumber: " + sequencecounter + " : "  + caseSequence.toString());
				caseBaseLog("Fall " + casecounter + " :" + handleInstance.getAttributes().toString());

				if(oldUpTime > newUpTime) {
					sequenceUpTime = oldUpTime;
					caseBaseLog("Regular KI killed the CBR KI at: " + deathcheck);
					sequencecounter++;
					
					if(winChance < 10.0) {
						for(int i = 0; i < caseSequence.size(); i++) {
							if(cbrProject.getCB(request.getSituation().getPlayer()).getCases().size() > 50) {
								Status status = tempStati.get(i);
								Instance currentIni = caseSequence.get(i);	
								Plan copyplan = new Plan();
								copyplan.setActionsAsString(currentIni.getAttForDesc(RetrievalHelper.PLAN_DESC).getValueAsString());
								removeCasesFromSequence(i, caseSequence);

									try {
									Instance gc = createInstance(status, copyplan.getActionsAsString(),
											caseSequence.get(i).toString());	
									cbrProject.getCB(request.getSituation().getPlayer()).removeCase(gc.toString());
									
									caseBaseLog("Removed from CB: " + gc.toString() + ", it was: " + handleInstance.getAttributes().toString());
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
					
					caseSequence.clear();
					tempStati.clear();
					caseBaseLog("Casebase Size after Removing: " + cbrProject.getCB(request.getSituation().getPlayer()).getCases().size());	
				}
				
				
				if(request.getSituation().toString().contains("isEnemyAlive=false")) {

					caseBaseLog("CBR KI killed the regular KI at : " + newUpTime);
					
					for(int i = 0; i < caseSequence.size(); i++) {
						
						Status status = tempStati.get(i);
						Plan copyplan = new Plan();
						Instance currentIni = caseSequence.get(i);						
						copyplan.setActionsAsString(currentIni.getAttForDesc(RetrievalHelper.PLAN_DESC).getValueAsString());
						removeCasesFromSequence(i, caseSequence);

						try {
							Instance goodCase = createInstance(status, copyplan.getActionsAsString(),
									"s" + (cbrProject.getCB(request.getSituation().getPlayer()).getCases().size() + minCBSize));	
							cbrProject.getCB(request.getSituation().getPlayer()).addCase(goodCase);
							minCBSize++;
						} catch (Exception e) {
							e.printStackTrace();
						}						
					}

					if(sequenceUpTime * 1.5 < newUpTime) {	
						
						caseBaseLog("Improved UpTime about : " + (newUpTime - sequenceUpTime) + " seconds!");	
					}	
					caseBaseLog("Casebase Size after Adding: " + cbrProject.getCB(request.getSituation().getPlayer()).getCases().size());
				}
				oldUpTime = currentUpTime;
				casecounter++;
				
			}
		}
	}

	/**
	 * Diese Methode entfernt von einer Übergebenen Liste an Instanzen die jeweils doppelten in ihrer Reihenfolge.
	 * Im Vorteilsfall wird die Situationsinitiierende Instanz wieder zurück gegeben, wenn die Negativkriterien
	 * erreicht werden, wird die Instanz die jeweils für das Negativ zu bewertende Verhalten verantwortlich ist aus der 
	 * Fallbasis entfernt. 
	 * 
	 * @param i - Übergebener Zaehler, der die uebergebene Instanz der jeweiligen Stelle weitergibt. 
	 * @param caseSequence - uebergebene Liste an Instanzen
	 */
	public void removeCasesFromSequence(int i, ArrayList<Instance> caseSequence) {
		
		for(int i2 = i+1; i2 < caseSequence.size(); i2++) {
			
			if(caseSequence.get(i).toString().contains("MoveTo;Shoot") == caseSequence.get(i2).toString().contains("MoveTo;Shoot")) {
				
				caseSequence.remove(i2);
				
			} else if(caseSequence.get(i).toString().contains("Reload")) {
				
				caseSequence.remove(i);
				
			} else if(caseSequence.get(i).toString().contains("UseCover;CollectItem<health>") == caseSequence.get(i2).toString().contains("UseCover;CollectItem<health>")) {
				
				caseSequence.remove(i2);
				
			} else if(caseSequence.get(i2).toString().contains("CollectItem<ammunition>")) {
				
				caseSequence.remove(i2);
				
			} else if(caseSequence.get(i).toString().contains("CollectItem<health>") == caseSequence.get(i2).toString().contains("CollectItem<health>")) {
				
				caseSequence.remove(i2);
				
			} else if(caseSequence.get(i).toString().contains("CollectItem<weapon>;SwitchWeapon") == caseSequence.get(i2).toString().contains("CollectItem<weapon>;SwitchWeapon")) {
				
				caseSequence.remove(i2);
				
			} 
								
		
		}
	}
public static void caseBaseLog(String text) {
		
		
		FileWriter fw = null;
		try {
			fw = new FileWriter(new File(System.getProperty("user.dir"), "caseBaseLog.txt"), true);
			fw.write(text);
			fw.write("\r\n");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				fw.flush();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}