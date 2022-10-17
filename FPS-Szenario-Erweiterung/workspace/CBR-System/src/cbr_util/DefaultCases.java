package cbr_util;
import java.util.HashMap;

import main.CBRSystem;
import model.Status;

/**
 * Hilfsklasse, die Defaultcases zur Verf&uuml;gung stellt, die jedem Spieler,
 * der auf das CBR-System zugreift, als Start-Fallbasis zur Verf&uuml;gung
 * gestellt wird.
 * 
 * @author Jannis Hillmann & Jobst-Julius Bartels
 *
 */
public final class DefaultCases {

	/**
	 * Map zur Speicherung von einem Plan zu einem Status.
	 */
	private static HashMap<Status, String> status = null;

	static {
		initList();
		fillList();
	}

	/**
	 * Methode zur Initialisierung der Map.
	 */
	private static void initList() {
		status = new HashMap<>();
	}
	
	/**
	 * In dieser Methode werden die Defaultcases erzeugt und der Map
	 * hinzugef&uuml;gt.
	 */
	/**
	 * 
	 */
	private static void fillList() {

		String plan = "";


//Konkreter Fall wenn Gegner Tot ist, dass Weapon gesammelt wird
Status Pickweapon = new Status();

		//Most important
		Pickweapon.setEnemyVisible(false);
		Pickweapon.setEquippedWeapon("Pistol");
		Pickweapon.setEnemyAlive(false);
		Pickweapon.setWeaponNeeded(true);
		Pickweapon.setDistanceToEnemy(Status.Distance.unknown.getValue());
		Pickweapon.setWeaponDistance(Status.Distance.middle.getValue());
		
		//Lessimportant
		Pickweapon.setCurrentAmmu(Status.CurrentAmmunition.middle.getValue());
		Pickweapon.setCurrentOverallAmmu(Status.CurrentOverallAmmunition.middle.getValue());
		Pickweapon.setAmmunitionDistance(Status.AmmunitionDistance.middle.getValue());
		Pickweapon.setCoverDistance(Status.CoverDistance.middle.getValue());
		Pickweapon.setHealthDistance(Status.HealthDistance.far.getValue());
		Pickweapon.setAmmunitionNeeded(false);
		Pickweapon.setCoverNeeded(false);
		Pickweapon.setCovered(false);
		Pickweapon.setHealthNeeded(true);
		Pickweapon.setLastPosition(Status.LastPosition.middle.getValue());
		Pickweapon.setOwnHealth(Status.OwnHealth.much.getValue());
		Pickweapon.setWinChance(Status.WinChance.equal.getValue());
		
		Pickweapon.setMovingCoverDistance(Status.MovingCoverDistance.middle.getValue());
		Pickweapon.setAmmunitionLargeDistance(Status.AmmunitionLargeDistance.middle.getValue());
		Pickweapon.setIsGadgetNeeded(true);
		
		plan = "CollectItem<weapon>;SwitchWeapon";
		status.put(Pickweapon, plan);

		
//Ganz konkreter Fall wenn die Waffe nah ist -> sammeln
Status PickweaponClose = new Status();
		
		//Most important
		PickweaponClose.setEnemyVisible(true);
		PickweaponClose.setEquippedWeapon("Pistol");
		PickweaponClose.setEnemyAlive(true);
		PickweaponClose.setWeaponNeeded(true);
		PickweaponClose.setDistanceToEnemy(Status.Distance.middle.getValue());
		PickweaponClose.setWeaponDistance(Status.Distance.near.getValue());
		
		//Lessimportant
		PickweaponClose.setCurrentAmmu(Status.CurrentAmmunition.full.getValue());
		PickweaponClose.setCurrentOverallAmmu(Status.CurrentOverallAmmunition.full.getValue());
		PickweaponClose.setAmmunitionDistance(Status.AmmunitionDistance.middle.getValue());
		PickweaponClose.setCoverDistance(Status.CoverDistance.middle.getValue());
		PickweaponClose.setHealthDistance(Status.HealthDistance.far.getValue());
		PickweaponClose.setAmmunitionNeeded(false);
		PickweaponClose.setCoverNeeded(false);
		PickweaponClose.setCovered(false);
		PickweaponClose.setHealthNeeded(true);
		PickweaponClose.setLastPosition(Status.LastPosition.middle.getValue());
		PickweaponClose.setOwnHealth(Status.OwnHealth.much.getValue());
		PickweaponClose.setWinChance(Status.WinChance.equal.getValue());
		
		PickweaponClose.setMovingCoverDistance(Status.MovingCoverDistance.middle.getValue());
		PickweaponClose.setAmmunitionLargeDistance(Status.AmmunitionLargeDistance.middle.getValue());
		PickweaponClose.setIsGadgetNeeded(true);
		
		plan = "CollectItem<weapon>;SwitchWeapon;Shoot";
		status.put(PickweaponClose, plan);

		
//Konkreter Fall wenn Gegner Tot ist, dass Health gesammelt wird
Status Pickhealth = new Status();

		//Most important
		Pickhealth.setEnemyVisible(false);
		Pickhealth.setEnemyAlive(false);
		Pickhealth.setHealthNeeded(true);
		Pickhealth.setHealthDistance(Status.HealthDistance.middle.getValue());
		Pickhealth.setDistanceToEnemy(Status.Distance.unknown.getValue());
		Pickhealth.setOwnHealth(Status.OwnHealth.middle.getValue());
		
		
		//Lessimportant
		Pickhealth.setCurrentAmmu(Status.CurrentAmmunition.full.getValue());
		Pickhealth.setCurrentOverallAmmu(Status.CurrentOverallAmmunition.full.getValue());
		Pickhealth.setAmmunitionDistance(Status.AmmunitionDistance.middle.getValue());
		Pickhealth.setWeaponDistance(Status.Distance.middle.getValue());
		Pickhealth.setEquippedWeapon("Pistol");
		Pickhealth.setCoverDistance(Status.CoverDistance.middle.getValue());
		Pickhealth.setAmmunitionNeeded(false);
		Pickhealth.setCoverNeeded(false);
		Pickhealth.setCovered(false);
		Pickhealth.setWeaponNeeded(true);
		Pickhealth.setLastPosition(Status.LastPosition.middle.getValue());
		Pickhealth.setWinChance(Status.WinChance.equal.getValue());
		
		Pickhealth.setMovingCoverDistance(Status.MovingCoverDistance.middle.getValue());
		Pickhealth.setAmmunitionLargeDistance(Status.AmmunitionLargeDistance.middle.getValue());
		Pickhealth.setIsGadgetNeeded(true);
		
		plan = "CollectItem<health>";
		status.put(Pickhealth, plan);



//Ganz konkreter Fall wenn health nahe ist -> sammeln
Status PickhealthClose = new Status();

		//Most important
		PickhealthClose.setEnemyVisible(true);
		PickhealthClose.setEnemyAlive(true);
		PickhealthClose.setHealthNeeded(true);
		PickhealthClose.setHealthDistance(Status.HealthDistance.near.getValue());
		PickhealthClose.setDistanceToEnemy(Status.Distance.middle.getValue());
		PickhealthClose.setOwnHealth(Status.OwnHealth.middle.getValue());
		
		//Lessimportant
		PickhealthClose.setCurrentAmmu(Status.CurrentAmmunition.full.getValue());
		PickhealthClose.setCurrentOverallAmmu(Status.CurrentOverallAmmunition.full.getValue());
		PickhealthClose.setAmmunitionDistance(Status.AmmunitionDistance.middle.getValue());
		PickhealthClose.setWeaponDistance(Status.Distance.middle.getValue());
		PickhealthClose.setEquippedWeapon("Pistol");
		PickhealthClose.setCoverDistance(Status.CoverDistance.middle.getValue());
		PickhealthClose.setAmmunitionNeeded(false);
		PickhealthClose.setCoverNeeded(false);
		PickhealthClose.setCovered(false);
		PickhealthClose.setWeaponNeeded(true);
		PickhealthClose.setLastPosition(Status.LastPosition.middle.getValue());
		PickhealthClose.setWinChance(Status.WinChance.equal.getValue());
		
		PickhealthClose.setMovingCoverDistance(Status.MovingCoverDistance.middle.getValue());
		PickhealthClose.setAmmunitionLargeDistance(Status.AmmunitionLargeDistance.middle.getValue());
		PickhealthClose.setIsGadgetNeeded(false);
		
		plan = "CollectItem<health>";
		status.put(PickhealthClose, plan);
		
//Konkreter Fall wenn Gegner Tot ist, dass Ammu gesammelt wird
Status Pickammu = new Status();

		//Most important
		Pickammu.setEnemyVisible(false);
		Pickammu.setEnemyAlive(false);
		Pickammu.setAmmunitionNeeded(true);
		Pickammu.setEquippedWeapon("Pistol");
		Pickammu.setCurrentAmmu(Status.CurrentAmmunition.few.getValue());
		Pickammu.setCurrentOverallAmmu(Status.CurrentOverallAmmunition.few.getValue());
		Pickammu.setAmmunitionDistance(Status.AmmunitionDistance.middle.getValue());
		
		Pickammu.setAmmunitionLargeDistance(Status.AmmunitionLargeDistance.middle.getValue());
		
		//Lessimportant
		Pickammu.setWeaponDistance(Status.Distance.middle.getValue());
		Pickammu.setCoverDistance(Status.CoverDistance.middle.getValue());
		Pickammu.setDistanceToEnemy(Status.Distance.middle.getValue());
		Pickammu.setHealthDistance(Status.HealthDistance.near.getValue());
		Pickammu.setCoverNeeded(false);
		Pickammu.setCovered(false);
		Pickammu.setHealthNeeded(false);
		Pickammu.setWeaponNeeded(false);
		Pickammu.setLastPosition(Status.LastPosition.middle.getValue());
		Pickammu.setOwnHealth(Status.OwnHealth.middle.getValue());
		Pickammu.setWinChance(Status.WinChance.winning.getValue());
		
		Pickammu.setMovingCoverDistance(Status.MovingCoverDistance.middle.getValue());	
		Pickammu.setIsGadgetNeeded(false);
		
		plan = "CollectItem<ammunition>";
		status.put(Pickammu, plan);
		
		
//Aggressiver Rohling, wenn CBR im Vorteil ist.
Status Attack = new Status();

		//Most important
		Attack.setEnemyVisible(true);
		Attack.setEnemyAlive(true);
		Attack.setWinChance(Status.WinChance.winning.getValue());
		Attack.setOwnHealth(Status.OwnHealth.much.getValue());
		
		//Lessimportant
		Attack.setCurrentAmmu(Status.CurrentAmmunition.full.getValue());
		Attack.setCurrentOverallAmmu(Status.CurrentOverallAmmunition.middle.getValue());
		Attack.setAmmunitionDistance(Status.AmmunitionDistance.far.getValue());
		Attack.setCoverDistance(Status.CoverDistance.far.getValue());
		Attack.setDistanceToEnemy(Status.Distance.near.getValue());
		Attack.setHealthDistance(Status.HealthDistance.far.getValue());
		Attack.setWeaponDistance(Status.Distance.far.getValue());
		Attack.setEquippedWeapon("Pistol");
		Attack.setAmmunitionNeeded(true);
		Attack.setCoverNeeded(false);
		Attack.setCovered(false);
		Attack.setHealthNeeded(true);
		Attack.setWeaponNeeded(true);
		Attack.setLastPosition(Status.LastPosition.middle.getValue());
		
		Attack.setMovingCoverDistance(Status.MovingCoverDistance.middle.getValue());
		Attack.setAmmunitionLargeDistance(Status.AmmunitionLargeDistance.middle.getValue());
		Attack.setIsGadgetNeeded(false);
		
		plan = "MoveTo;Shoot";
		status.put(Attack, plan);
		
	Status AttackwithGun = new Status();

		//Most important
		AttackwithGun.setEnemyVisible(true);
		AttackwithGun.setEnemyAlive(true);
		AttackwithGun.setWinChance(Status.WinChance.winning.getValue());
		AttackwithGun.setOwnHealth(Status.OwnHealth.much.getValue());
		
		//Lessimportant
		AttackwithGun.setCurrentAmmu(Status.CurrentAmmunition.full.getValue());
		AttackwithGun.setCurrentOverallAmmu(Status.CurrentOverallAmmunition.middle.getValue());
		AttackwithGun.setAmmunitionDistance(Status.AmmunitionDistance.far.getValue());
		AttackwithGun.setCoverDistance(Status.CoverDistance.far.getValue());
		AttackwithGun.setDistanceToEnemy(Status.Distance.middle.getValue());
		AttackwithGun.setHealthDistance(Status.HealthDistance.far.getValue());
		AttackwithGun.setWeaponDistance(Status.Distance.far.getValue());
		AttackwithGun.setEquippedWeapon("Machine Gun");
		AttackwithGun.setAmmunitionNeeded(true);
		AttackwithGun.setCoverNeeded(false);
		AttackwithGun.setCovered(false);
		AttackwithGun.setHealthNeeded(true);
		AttackwithGun.setWeaponNeeded(true);
		AttackwithGun.setLastPosition(Status.LastPosition.middle.getValue());
		
		AttackwithGun.setMovingCoverDistance(Status.MovingCoverDistance.middle.getValue());
		AttackwithGun.setAmmunitionLargeDistance(Status.AmmunitionLargeDistance.middle.getValue());
		AttackwithGun.setIsGadgetNeeded(false);
		
		
		plan = "MoveTo;Shoot";
		status.put(AttackwithGun, plan);
		
		
	Status CloseGun = new Status();

		//Most important
		CloseGun.setEnemyVisible(true);
		CloseGun.setEnemyAlive(true);
		CloseGun.setWinChance(Status.WinChance.equal.getValue());
		CloseGun.setOwnHealth(Status.OwnHealth.much.getValue());
		
		//Lessimportant
		CloseGun.setCurrentAmmu(Status.CurrentAmmunition.full.getValue());
		CloseGun.setCurrentOverallAmmu(Status.CurrentOverallAmmunition.middle.getValue());
		CloseGun.setAmmunitionDistance(Status.AmmunitionDistance.far.getValue());
		CloseGun.setCoverDistance(Status.CoverDistance.far.getValue());
		CloseGun.setDistanceToEnemy(Status.Distance.middle.getValue());
		CloseGun.setHealthDistance(Status.HealthDistance.far.getValue());
		CloseGun.setWeaponDistance(Status.Distance.far.getValue());
		CloseGun.setEquippedWeapon("Machine Gun");
		CloseGun.setAmmunitionNeeded(true);
		CloseGun.setCoverNeeded(false);
		CloseGun.setCovered(false);
		CloseGun.setHealthNeeded(true);
		CloseGun.setWeaponNeeded(true);
		CloseGun.setLastPosition(Status.LastPosition.middle.getValue());
		
		CloseGun.setMovingCoverDistance(Status.MovingCoverDistance.middle.getValue());
		CloseGun.setAmmunitionLargeDistance(Status.AmmunitionLargeDistance.middle.getValue());
		CloseGun.setIsGadgetNeeded(false);
		
		plan = "Shoot;Shoot";
		status.put(CloseGun, plan);
		
	
 // Wird ersetzt durch HideDefaultCover und HideMovingCover
 
//Defensiver Rohling, wenn CBR im Nachteil ist.
Status Defense = new Status();

		//Most important
		Defense.setWinChance(Status.WinChance.loosing.getValue());
		Defense.setOwnHealth(Status.OwnHealth.few.getValue());
		Defense.setCoverNeeded(true);
		
		//Lessimportant
		Defense.setCurrentAmmu(Status.CurrentAmmunition.few.getValue());
		Defense.setCurrentOverallAmmu(Status.CurrentOverallAmmunition.middle.getValue());
		Defense.setAmmunitionDistance(Status.AmmunitionDistance.middle.getValue());
		Defense.setCoverDistance(Status.CoverDistance.middle.getValue());
		Defense.setDistanceToEnemy(Status.Distance.middle.getValue());
		Defense.setHealthDistance(Status.HealthDistance.middle.getValue());
		Defense.setWeaponDistance(Status.Distance.far.getValue());
		Defense.setEquippedWeapon("Pistol");
		Defense.setAmmunitionNeeded(true);
		Defense.setCovered(false);
		Defense.setEnemyVisible(true);
		Defense.setEnemyAlive(true);
		Defense.setHealthNeeded(true);
		Defense.setWeaponNeeded(true);
		Defense.setLastPosition(Status.LastPosition.middle.getValue());
		
		Defense.setMovingCoverDistance(Status.MovingCoverDistance.far.getValue());
		Defense.setAmmunitionLargeDistance(Status.AmmunitionLargeDistance.middle.getValue());
		Defense.setIsGadgetNeeded(false);
		
		plan = "UseCover;CollectItem<health>;Shoot";
		status.put(Defense, plan);

		
//Nachladen, wenn extremwert erreicht wird
Status Reload = new Status();

		//Most important
		Reload.setEquippedWeapon("Machine Gun"); //weniger wichtig
		Reload.setCurrentAmmu(Status.CurrentAmmunition.empty.getValue());
		

		
		//Lessimportant
		Reload.setCurrentOverallAmmu(Status.CurrentOverallAmmunition.middle.getValue());
		Reload.setAmmunitionDistance(Status.AmmunitionDistance.middle.getValue());
		Reload.setCoverDistance(Status.CoverDistance.middle.getValue());
		Reload.setDistanceToEnemy(Status.Distance.middle.getValue());
		Reload.setHealthDistance(Status.HealthDistance.near.getValue());
		Reload.setWeaponDistance(Status.Distance.middle.getValue());
		Reload.setAmmunitionNeeded(false);
		Reload.setCoverNeeded(false);
		Reload.setCovered(false);
		Reload.setEnemyVisible(true);
		Reload.setEnemyAlive(true);
		Reload.setHealthNeeded(true);
		Reload.setWeaponNeeded(true);
		Reload.setLastPosition(Status.LastPosition.middle.getValue());
		Reload.setOwnHealth(Status.OwnHealth.middle.getValue());
		Reload.setWinChance(Status.WinChance.equal.getValue());
		
		Reload.setMovingCoverDistance(Status.MovingCoverDistance.middle.getValue());
		Reload.setAmmunitionLargeDistance(Status.AmmunitionLargeDistance.middle.getValue());
		Reload.setIsGadgetNeeded(false);
		
		plan = "Reload";
		status.put(Reload, plan);
		
Status Reload2 = new Status();

		//Most important
		Reload2.setEquippedWeapon("Pistol"); //weniger wichtig
		Reload2.setCurrentAmmu(Status.CurrentAmmunition.empty.getValue());
		
		plan = "Reload";
		status.put(Reload2, plan);
		
Status nearShoot = new Status();

		//Most important
		nearShoot.setEnemyVisible(true);
		nearShoot.setEnemyAlive(true);
		nearShoot.setHealthNeeded(true);
		nearShoot.setHealthDistance(Status.HealthDistance.far.getValue());
		nearShoot.setDistanceToEnemy(Status.Distance.near.getValue());
		nearShoot.setOwnHealth(Status.OwnHealth.middle.getValue());
		
		
		//Lessimportant
		nearShoot.setCurrentAmmu(Status.CurrentAmmunition.middle.getValue());
		nearShoot.setCurrentOverallAmmu(Status.CurrentOverallAmmunition.middle.getValue());
		nearShoot.setAmmunitionDistance(Status.AmmunitionDistance.middle.getValue());
		nearShoot.setWeaponDistance(Status.Distance.middle.getValue());
		nearShoot.setEquippedWeapon("Pistol");
		nearShoot.setCoverDistance(Status.CoverDistance.middle.getValue());
		nearShoot.setAmmunitionNeeded(false);
		nearShoot.setCoverNeeded(false);
		nearShoot.setCovered(false);
		nearShoot.setWeaponNeeded(true);
		nearShoot.setLastPosition(Status.LastPosition.middle.getValue());
		nearShoot.setWinChance(Status.WinChance.equal.getValue());
		
		nearShoot.setMovingCoverDistance(Status.MovingCoverDistance.middle.getValue());
		nearShoot.setAmmunitionLargeDistance(Status.AmmunitionLargeDistance.middle.getValue());
		nearShoot.setIsGadgetNeeded(false);
		
		plan = "MoveTo;Shoot;Shoot";
		status.put(nearShoot, plan);
			
		
		// Fall wenn sich die Mobile Deckung näher ist als die Normale
Status HideMovingCover = new Status();

				//Most important
		HideMovingCover.setEnemyVisible(true);
		HideMovingCover.setWinChance(Status.WinChance.equal.getValue());
		HideMovingCover.setOwnHealth(Status.OwnHealth.middle.getValue());
		HideMovingCover.setCoverNeeded(true);
		HideMovingCover.setMovingCoverDistance(Status.MovingCoverDistance.near.getValue());
		HideMovingCover.setCoverDistance(Status.CoverDistance.middle.getValue());
				
				//Lessimportant
		HideMovingCover.setCurrentAmmu(Status.CurrentAmmunition.few.getValue());
		HideMovingCover.setCurrentOverallAmmu(Status.CurrentOverallAmmunition.middle.getValue());
		HideMovingCover.setAmmunitionDistance(Status.AmmunitionDistance.middle.getValue());
		HideMovingCover.setDistanceToEnemy(Status.Distance.middle.getValue());
		HideMovingCover.setHealthDistance(Status.HealthDistance.middle.getValue());
		HideMovingCover.setWeaponDistance(Status.Distance.far.getValue());
		HideMovingCover.setEquippedWeapon("Pistol");
		HideMovingCover.setAmmunitionNeeded(true);
		HideMovingCover.setCovered(false);	
		HideMovingCover.setEnemyAlive(true);
		HideMovingCover.setHealthNeeded(true);
		HideMovingCover.setWeaponNeeded(true);
		HideMovingCover.setLastPosition(Status.LastPosition.middle.getValue());
						
		HideMovingCover.setAmmunitionLargeDistance(Status.AmmunitionLargeDistance.middle.getValue());
		HideMovingCover.setIsGadgetNeeded(false);
		
		CBRSystem.writeToFile("Plan: UseMovingCover");
		plan = "UseMovingCover;CollectItem<health>;Shoot";
		status.put(HideMovingCover, plan);
		
				
	  // Fall wenn sich die Mobile Deckung und die Standard auf selber Distanz befinden
Status HideDefaultCover = new Status();

							//Most important
		HideDefaultCover.setWinChance(Status.WinChance.equal.getValue());
		HideDefaultCover.setOwnHealth(Status.OwnHealth.middle.getValue());
		HideDefaultCover.setCoverNeeded(true);
		HideMovingCover.setMovingCoverDistance(Status.MovingCoverDistance.middle.getValue());
		HideMovingCover.setCoverDistance(Status.CoverDistance.middle.getValue());
							
							//Lessimportant
		HideDefaultCover.setCurrentAmmu(Status.CurrentAmmunition.few.getValue());
		HideDefaultCover.setCurrentOverallAmmu(Status.CurrentOverallAmmunition.middle.getValue());
		HideDefaultCover.setAmmunitionDistance(Status.AmmunitionDistance.middle.getValue());
		HideDefaultCover.setCoverDistance(Status.CoverDistance.far.getValue());
		HideDefaultCover.setDistanceToEnemy(Status.Distance.middle.getValue());
		HideDefaultCover.setHealthDistance(Status.HealthDistance.middle.getValue());
		HideDefaultCover.setWeaponDistance(Status.Distance.far.getValue());
		HideDefaultCover.setEquippedWeapon("Pistol");
		HideDefaultCover.setAmmunitionNeeded(true);
		HideDefaultCover.setCovered(false);
		HideDefaultCover.setEnemyVisible(true);
		HideDefaultCover.setEnemyAlive(true);
		HideDefaultCover.setHealthNeeded(true);
		HideDefaultCover.setWeaponNeeded(true);
		HideDefaultCover.setLastPosition(Status.LastPosition.middle.getValue());
							
		HideDefaultCover.setMovingCoverDistance(Status.MovingCoverDistance.middle.getValue());					
		HideDefaultCover.setAmmunitionLargeDistance(Status.AmmunitionLargeDistance.middle.getValue());
		HideDefaultCover.setIsGadgetNeeded(false);
							
		plan = "UseCover;CollectItem<health>;Shoot";
		status.put(HideDefaultCover, plan);	
		
		
		//Konkreter Fall wenn Munnition oder das Gadget benötigt wird 
Status PickGadget = new Status();

		//Most important
		PickGadget.setEnemyVisible(false);
		PickGadget.setEnemyAlive(false);
		PickGadget.setAmmunitionNeeded(true);			
		PickGadget.setIsGadgetNeeded(true);		
		PickGadget.setAmmunitionLargeDistance(Status.AmmunitionLargeDistance.middle.getValue());
				
		//Lessimportant
		PickGadget.setWeaponDistance(Status.Distance.middle.getValue());
		PickGadget.setCoverDistance(Status.CoverDistance.middle.getValue());
		PickGadget.setDistanceToEnemy(Status.Distance.middle.getValue());
		PickGadget.setHealthDistance(Status.HealthDistance.near.getValue());
		PickGadget.setAmmunitionDistance(Status.AmmunitionDistance.middle.getValue());
		PickGadget.setCurrentAmmu(Status.CurrentAmmunition.middle.getValue());
		PickGadget.setCurrentOverallAmmu(Status.CurrentOverallAmmunition.middle.getValue());
		PickGadget.setEquippedWeapon("Pistol");
		PickGadget.setCoverNeeded(false);
		PickGadget.setCovered(false);
		PickGadget.setHealthNeeded(false);
		PickGadget.setWeaponNeeded(false);
		PickGadget.setLastPosition(Status.LastPosition.middle.getValue());
		PickGadget.setOwnHealth(Status.OwnHealth.middle.getValue());
		PickGadget.setWinChance(Status.WinChance.winning.getValue());
				
		PickGadget.setMovingCoverDistance(Status.MovingCoverDistance.middle.getValue());	
		PickGadget.setIsGadgetNeeded(false);
				
		plan = "CollectItem<ammuLarge>";
		status.put(PickGadget, plan);		
	
		
		//Konkreter Fall wenn der Spieler sich in Deckung befindet und das Gadget besitzt
Status PlaceGadget = new Status();

		//Most important	
		PickGadget.setIsGadgetNeeded(false);
		PlaceGadget.setCovered(true);
					
		//Lessimportant
		PlaceGadget.setDistanceToEnemy(Status.Distance.far.getValue());
		PlaceGadget.setHealthNeeded(false);
		PlaceGadget.setEnemyVisible(false);
		PlaceGadget.setEnemyAlive(false);
		PlaceGadget.setAmmunitionNeeded(false);
		PlaceGadget.setWeaponDistance(Status.Distance.middle.getValue());
		PlaceGadget.setCoverDistance(Status.CoverDistance.middle.getValue());
		PlaceGadget.setHealthDistance(Status.HealthDistance.near.getValue());
		PlaceGadget.setCoverNeeded(false);
		PlaceGadget.setHealthNeeded(false);
		PlaceGadget.setWeaponNeeded(false);
		PlaceGadget.setLastPosition(Status.LastPosition.middle.getValue());
		PlaceGadget.setOwnHealth(Status.OwnHealth.middle.getValue());
		PlaceGadget.setWinChance(Status.WinChance.winning.getValue());
						
		PlaceGadget.setMovingCoverDistance(Status.MovingCoverDistance.middle.getValue());	
				
		plan = "PlaceGadget";
		status.put(PlaceGadget, plan);		
//		
//Status s1 = new Status();
//		
//		//Sub-Status: Enemy
//		s1.setEnemyVisible(false);
//		s1.setEnemyAlive(false);
//		s1.setDistanceToEnemy(Status.Distance.unknown.getValue());
//		s1.setLastPosition(Status.LastPosition.middle.getValue());
//
//		//Sub-Status: Items		
//			// need	
//		s1.setWeaponNeeded(true);
//		s1.setHealthNeeded(false);
//		s1.setAmmunitionNeeded(true);
//		
//			// equipment
//		s1.setOwnHealth(Status.OwnHealth.full.getValue());
//		s1.setEquippedWeapon("Pistol");
//		s1.setCurrentOverallAmmu(Status.CurrentOverallAmmunition.much.getValue());
//		s1.setCurrentAmmu(Status.CurrentAmmunition.full.getValue());
//		
//			// distance
//		s1.setWeaponDistance(Status.WeaponDistance.middle.getValue());
//		s1.setAmmunitionDistance(Status.AmmunitionDistance.middle.getValue());
//		s1.setHealthDistance(Status.HealthDistance.far.getValue());
//		
//		//Sub-Status: Blocked
//		s1.setCoverNeeded(false);
//		s1.setCovered(false);
//		s1.setCoverDistance(Status.CoverDistance.unknown.getValue());
//		
//		
//		plan = "CollectItem<weapon>;SwitchWeapon";
//		status.put(s1, plan);
//		
//		
//		Status s2 = new Status();
//		
//		//Sub-Status: Enemy
//		s2.setEnemyVisible(true);
//		s2.setEnemyAlive(true);
//		s2.setDistanceToEnemy(Status.Distance.middle.getValue());
//		s2.setLastPosition(Status.LastPosition.middle.getValue());
//		
//		//Sub-Status: Items		
//			// need	
//		s2.setAmmunitionNeeded(true);
//		s2.setWeaponNeeded(true);
//		s2.setHealthNeeded(false);
//		
//			// equipment
//		s2.setOwnHealth(Status.OwnHealth.full.getValue());
//		s2.setCurrentAmmu(Status.CurrentAmmunition.full.getValue());
//		s2.setCurrentOverallAmmu(Status.CurrentOverallAmmunition.middle.getValue());		
//		s2.setEquippedWeapon("Pistol");
//		
//			// distance
//		s2.setWeaponDistance(Status.WeaponDistance.near.getValue());
//		s2.setAmmunitionDistance(Status.AmmunitionDistance.middle.getValue());
//		s2.setHealthDistance(Status.HealthDistance.far.getValue());	
//		
//		//Sub-Status: Blocked	
//		s2.setCoverNeeded(false);
//		s2.setCovered(false);
//		s2.setCoverDistance(Status.CoverDistance.unknown.getValue());
//		
//		plan = "Shoot;CollectItem<weapon>;SwitchWeapon";
//		status.put(s2, plan);
//		
//		
//		Status s3 = new Status();
//		
//		//Sub-Status: Enemy
//		s3.setEnemyVisible(false);
//		s3.setEnemyAlive(false);
//		s3.setDistanceToEnemy(Status.Distance.unknown.getValue());
//		s3.setLastPosition(Status.LastPosition.unknown.getValue());
//		
//		//Sub-Status: Items		
//			// need	
//		s3.setAmmunitionNeeded(true);
//		s3.setWeaponNeeded(true);
//		s3.setHealthNeeded(true);
//		
//			// equipment
//		s3.setOwnHealth(Status.OwnHealth.middle.getValue());
//		s3.setCurrentAmmu(Status.CurrentAmmunition.middle.getValue());
//		s3.setCurrentOverallAmmu(Status.CurrentOverallAmmunition.middle.getValue());		
//		s3.setEquippedWeapon("Pistol");
//
//			// distance
//		s3.setWeaponDistance(Status.WeaponDistance.middle.getValue());
//		s3.setAmmunitionDistance(Status.AmmunitionDistance.far.getValue());
//		s3.setHealthDistance(Status.HealthDistance.middle.getValue());	
//		
//		//Sub-Status: Blocked
//		s3.setCoverNeeded(false);
//		s3.setCovered(false);
//		s3.setCoverDistance(Status.CoverDistance.unknown.getValue());
//		
//		plan = "CollectItem<health>";
//		status.put(s3, plan);
//		
//		Status s4 = new Status();
//		
//		//Sub-Status: Enemy
//		s4.setEnemyVisible(true);
//		s4.setEnemyAlive(true);
//		s4.setDistanceToEnemy(Status.Distance.middle.getValue());
//		s4.setLastPosition(Status.LastPosition.middle.getValue());
//		
//		//Sub-Status: Items		
//			// need	
//		s4.setAmmunitionNeeded(true);
//		s4.setWeaponNeeded(true);
//		s4.setHealthNeeded(true);
//		
//			// equipment
//		s4.setOwnHealth(Status.OwnHealth.middle.getValue());
//		s4.setCurrentAmmu(Status.CurrentAmmunition.middle.getValue());
//		s4.setCurrentOverallAmmu(Status.CurrentOverallAmmunition.middle.getValue());		
//		s4.setEquippedWeapon("Pistol");
//
//			// distance
//		s4.setWeaponDistance(Status.WeaponDistance.near.getValue());
//		s4.setAmmunitionDistance(Status.AmmunitionDistance.near.getValue());
//		s4.setHealthDistance(Status.HealthDistance.near.getValue());	
//		
//		//Sub-Status: Blocked
//		s4.setCoverNeeded(false);
//		s4.setCovered(false);
//		s4.setCoverDistance(Status.CoverDistance.unknown.getValue());
//		
//		plan = "CollectItem<health>;Shoot";
//		status.put(s4, plan);
//		
//		
//		
//		Status s5 = new Status();
//		
//		//Sub-Status: Enemy
//		s5.setEnemyVisible(false);
//		s5.setEnemyAlive(false);
//		s5.setDistanceToEnemy(Status.Distance.middle.getValue());
//		s5.setLastPosition(Status.LastPosition.middle.getValue());
//		
//		//Sub-Status: Items		
//			// need	
//		s5.setAmmunitionNeeded(true);
//		s5.setWeaponNeeded(false);
//		s5.setHealthNeeded(false);
//		
//			// equipment
//		s5.setOwnHealth(Status.OwnHealth.full.getValue());
//		s5.setCurrentAmmu(Status.CurrentAmmunition.middle.getValue());
//		s5.setCurrentOverallAmmu(Status.CurrentOverallAmmunition.much.getValue());		
//		s5.setEquippedWeapon("Machine Gun");
//
//			// distance
//		s5.setWeaponDistance(Status.WeaponDistance.far.getValue());
//		s5.setAmmunitionDistance(Status.AmmunitionDistance.near.getValue());
//		s5.setHealthDistance(Status.HealthDistance.middle.getValue());	
//		
//		//Sub-Status: Blocked
//		s5.setCoverNeeded(false);
//		s5.setCovered(false);
//		s5.setCoverDistance(Status.CoverDistance.unknown.getValue());
//		
//		plan = "CollectItem<ammu>";
//		status.put(s5, plan);
//		
//		Status s6 = new Status();
//		
//		//Sub-Status: Enemy
//		s6.setEnemyVisible(true);
//		s6.setEnemyAlive(true);
//		s6.setDistanceToEnemy(Status.Distance.far.getValue());
//		s6.setLastPosition(Status.LastPosition.middle.getValue());
//		
//		//Sub-Status: Items		
//			// need	
//		s6.setAmmunitionNeeded(true);
//		s6.setWeaponNeeded(false);
//		s6.setHealthNeeded(false);
//		
//			// equipment
//		s6.setOwnHealth(Status.OwnHealth.full.getValue());
//		s6.setCurrentAmmu(Status.CurrentAmmunition.middle.getValue());
//		s6.setCurrentOverallAmmu(Status.CurrentOverallAmmunition.few.getValue());		
//		s6.setEquippedWeapon("Pistole");
//
//			// distance
//		s6.setWeaponDistance(Status.WeaponDistance.far.getValue());
//		s6.setAmmunitionDistance(Status.AmmunitionDistance.near.getValue());
//		s6.setHealthDistance(Status.HealthDistance.far.getValue());	
//		
//		//Sub-Status: Blocked
//		s6.setCoverNeeded(false);
//		s6.setCovered(false);
//		s6.setCoverDistance(Status.CoverDistance.unknown.getValue());
//		
//		plan = "CollectItem<ammu>;Shoot";
//		status.put(s6, plan);
//
//		Status s7 = new Status();
//		
//		//Sub-Status: Enemy
//		s7.setEnemyVisible(true);
//		s7.setEnemyAlive(true);
//		s7.setDistanceToEnemy(Status.Distance.middle.getValue());
//		s7.setLastPosition(Status.LastPosition.middle.getValue());
//		
//		//Sub-Status: Items		
//			// need	
//		s7.setAmmunitionNeeded(false);
//		s7.setWeaponNeeded(true);
//		s7.setHealthNeeded(false);
//		
//			// equipment
//		s7.setOwnHealth(Status.OwnHealth.full.getValue());
//		s7.setCurrentAmmu(Status.CurrentAmmunition.middle.getValue());
//		s7.setCurrentOverallAmmu(Status.CurrentOverallAmmunition.full.getValue());		
//		s7.setEquippedWeapon("Pistole");
//
//			// distance
//		s7.setWeaponDistance(Status.WeaponDistance.unknown.getValue());
//		s7.setAmmunitionDistance(Status.AmmunitionDistance.far.getValue());
//		s7.setHealthDistance(Status.HealthDistance.unknown.getValue());	
//		
//		//Sub-Status: Blocked
//		s7.setCoverNeeded(false);
//		s7.setCovered(false);
//		s7.setCoverDistance(Status.CoverDistance.unknown.getValue());
//		
//		plan = "MoveTo;Shoot";
//		status.put(s7, plan);
//		
//		
//		Status s8 = new Status();
//		
//		//Sub-Status: Enemy
//		s8.setEnemyVisible(true);
//		s8.setEnemyAlive(true);
//		s8.setDistanceToEnemy(Status.Distance.middle.getValue());
//		s8.setLastPosition(Status.LastPosition.middle.getValue());
//		
//		//Sub-Status: Items		
//			// need	
//		s8.setAmmunitionNeeded(true);
//		s8.setWeaponNeeded(false);
//		s8.setHealthNeeded(false);
//		
//			// equipment
//		s8.setOwnHealth(Status.OwnHealth.full.getValue());
//		s8.setCurrentAmmu(Status.CurrentAmmunition.much.getValue());
//		s8.setCurrentOverallAmmu(Status.CurrentOverallAmmunition.middle.getValue());	
//		s8.setEquippedWeapon("Machine Gun");
//
//			// distance
//		s8.setWeaponDistance(Status.WeaponDistance.middle.getValue());
//		s8.setAmmunitionDistance(Status.AmmunitionDistance.middle.getValue());
//		s8.setHealthDistance(Status.HealthDistance.far.getValue());	
//		
//		//Sub-Status: Blocked
//		s8.setCoverNeeded(false);
//		s8.setCovered(false);
//		s8.setCoverDistance(Status.CoverDistance.unknown.getValue());
//		
//		plan = "Shoot";
//		status.put(s8, plan);
//		
//		Status s9 = new Status();
//		
//		//Sub-Status: Enemy
//		s9.setEnemyVisible(false);
//		s9.setEnemyAlive(false);
//		s9.setDistanceToEnemy(Status.Distance.unknown.getValue());
//		s9.setLastPosition(Status.LastPosition.middle.getValue());
//		
//		//Sub-Status: Items		
//			// need	
//		s9.setAmmunitionNeeded(false);
//		s9.setWeaponNeeded(true);
//		s9.setHealthNeeded(true);
//		
//			// equipment
//		s9.setOwnHealth(Status.OwnHealth.middle.getValue());
//		s9.setCurrentAmmu(Status.CurrentAmmunition.few.getValue());
//		s9.setCurrentOverallAmmu(Status.CurrentOverallAmmunition.middle.getValue());		
//		s9.setEquippedWeapon("Machine Gun");
//
//			// distance
//		s9.setWeaponDistance(Status.WeaponDistance.middle.getValue());
//		s9.setAmmunitionDistance(Status.AmmunitionDistance.middle.getValue());
//		s9.setHealthDistance(Status.HealthDistance.middle.getValue());	
//		
//		//Sub-Status: Blocked
//		s9.setCoverNeeded(false);
//		s9.setCovered(false);
//		s9.setCoverDistance(Status.CoverDistance.unknown.getValue());
//		
//		plan = "Reload";
//		status.put(s9, plan);
		
		
	}

	/**
	 * Simpler Getter f&uuml;r die Map, welche die DefaultCases beinhaltet.
	 * 
	 * @return Eine Map mit den DefaultCases.
	 */
	public static HashMap<Status, String> getDefaultCases() {
		return status;
	}
}
