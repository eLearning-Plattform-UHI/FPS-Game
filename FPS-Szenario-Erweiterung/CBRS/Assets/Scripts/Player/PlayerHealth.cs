using UnityEngine;
using UnityEngine.UI;
using Assets.Scripts.Model;


/**
 * Skript zur Visualisierung der Lebenspunkte des menschlichen Spielers.
 */
public class PlayerHealth : MonoBehaviour
{
    /**
     * Lebenspunkte für den Lebensbalken.
     */
    public int mStartingHealth = Player.mMaxLife;
    public int mStartingShield = Player.mMaxShield;
    /**
     * Aktuelle Lebenspunkte/Schild.
     */
    public int mCurrentHealth;
    public int mCurrentShield;
    /**
     * Bei einem Treffer "blitzt" (flasht) ein rotes Bild auf --> Geschwindigkeit des Flashens.
     */
    public float mFlashSpeed = 5f;
    /**
     * Rote Farbe.
     */
    public Color flashColour = new Color(1f, 0f, 0f, 0.1f);
    /**
     * Schaden genommen?
     */
    public bool damaged { get; set; }
    /**
     * Audio Quelle bei Treffer.
     */
    private AudioSource playerAudio;
    /**
     * Slider für die Darstellung der Lebenspunkte.
     */
    private Slider healthSlider;
    /**
     * Slider für die Darstellung des Schilds.
     */
    private Slider shieldSlider;
    /**
     * Image, welches flashen soll, wenn Schaden genommen wird.
     */
    private Image damageImage;

    /**
     * GameObject des Images.
     */
    private GameObject imageObject;
    /**
     * GameObject des Health Sliders.
     */
    private GameObject sliderObject;
    /**
     * GameObject des Shield Sliders.
     */
    private GameObject sliderShieldObject;
    /**
     * GameObject des "Kastens" für die Anzeige der Munition.
     */
    private GameObject ammuDisplayerObject;
    /**
     * Variable, die den Text hält, der zur Anzeige der Munition gezeigt werden soll (bspw: "12/48").
     */
    private Text ammuDisplayerText;

    /**
     * Wer ist der aktuelle Spieler?
     */
    public Player currentPlayer { get; set; }

    /**
    * Unity Methode, die beim Aufruf des Skripts *einmalig* ausgeführt wird.
    */
    private void Awake()
    {
        playerAudio = GetComponent<AudioSource>();
        mCurrentHealth = mStartingHealth;
        mCurrentShield = mStartingShield;
    }

    /**
     * Diese Methode intialisiert alle relevanten Objekt für den Slider.
     */
    private void InitSlider()
    {
        // Health Slider
        if (sliderObject == null)
        {
            sliderObject = GameObject.FindGameObjectWithTag("HealthSlider");
        }
        if (sliderObject != null && healthSlider == null)
        {
            healthSlider = sliderObject.GetComponent<Slider>();
        }
        // Shield Slider
        if (sliderShieldObject == null)
        {
            sliderShieldObject = GameObject.FindGameObjectWithTag("ShieldSlider");
        }
        if (sliderShieldObject != null && shieldSlider == null)
        {
            shieldSlider = sliderShieldObject.GetComponent<Slider>();
        }        
    }

    /**
     * Unity-Methode.
     */
    private void Update()
    {

        if (imageObject == null)
        {
            imageObject = GameObject.FindGameObjectWithTag("DamageImage");
        }

        if (ammuDisplayerObject == null)
        {
            ammuDisplayerObject = GameObject.FindGameObjectWithTag("Ammunition");
        }

        if (ammuDisplayerObject != null && ammuDisplayerText == null)
        {
            ammuDisplayerText = StaticMenueFunctions.FindComponentInChildren<Text>(ammuDisplayerObject);
        }

        InitSlider();

        UpdateAmmunition(PlayerShooting.mHumanPlayer);
        UpdateLifeBar(PlayerShooting.mHumanPlayer);

        if (imageObject != null)
        {
            damageImage = imageObject.GetComponent<Image>();
        }

        if (damaged)
        {
            damageImage.color = flashColour;
        }
        else
        {
            damageImage.color = Color.Lerp(damageImage.color, Color.clear, mFlashSpeed * Time.deltaTime);
        }
        damaged = false;
    }

    /**
     * Diese Methode aktualisiert die Munitionsanzeige.
     */
    public void UpdateAmmunition(Player player)
    {
        if (ammuDisplayerText != null && player != null)
        {
            ammuDisplayerText.text = player.mEquippedWeapon.mCurrentMagazineAmmu + "/" + player.mEquippedWeapon.mCurrentOverallAmmu;
        }
        
    }
    /**
     * Diese Methode aktualisiert den Healthslider.
     */
    public void UpdateLifeBar(Player player)
    {
        healthSlider.value = player.mPlayerHealth;
        shieldSlider.value = player.mPlayerShield;
    }
    /**
     * Diese Methode händelt alle nötigen Vorgängen, wenn der Spieler Schaden genommen hat.
     */
    public void TakeDamage(Player hitPlayer)
    {
        mCurrentHealth = hitPlayer.mPlayerHealth;
        mCurrentShield = hitPlayer.mPlayerShield;

        InitSlider();

        UpdateLifeBar(hitPlayer);

        playerAudio.Play();
    }

    /**
     * Diese Methode setzt alle Werte zurück (Slider, Ammu, ...) --> bspw. beim Tod/Respawn relevant.
     */
    public void ResetValues()
    {
        damaged = false;
        healthSlider.value = mStartingHealth;  
        shieldSlider.value = mStartingShield;  
    }
}
