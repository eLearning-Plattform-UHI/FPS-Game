using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using TMPro;
using Assets.Scripts.Model;
using Assets.Scripts.CBR.Model;
using Assets.Scripts.CBR.Plan;

public class MeleeScript : MonoBehaviour
{
    Animator anim;
    public KeyCode meleeKey = KeyCode.Mouse0;
    public GameObject knifeObject;
    public Transform cam;
    public Transform attackPoint;
    public GameObject mPlayer;
    public GameObject knife;
    public string mName;
    public int mDamage;
    
    public float meleeRange;
    public float attackCooldown;

    bool readyToAttack;

    // public MeleeScript(GameObject player): base(player, "MeleeScript", 75, 1f, StaticMenueFunctions.FindComponentInChildWithTag<Component>(player, "MeleeScript").gameObject, 1)
    //     {
    //         // mPlayer = player;
    //         // mName = name;
    //         // mDamage = damage;
    //         //knifeObject = weaponModel;
    //     }

    void Start()
    {   
        //knife = Instantiate(knifeObject, attackPoint.position, cam.rotation);
        InvokeRepeating("Attack", 0, 10f);
            
    }

    // Update is called once per frame
    void Update()
    {
        if (Input.GetKeyDown(meleeKey)){
            Attack();
        }
            
    }
    private void Attack()
    {
        anim = GetComponent<Animator>();
        anim.SetBool("Active", true);
        readyToAttack = false;
        //Messer wird erzeugt, muss noch entfernt werden

        //Rigidbody knifeRb = knife.GetComponent<Rigidbody>();

        Invoke(nameof(ResetAttack), attackCooldown);

        Collider[] hitColliders = Physics.OverlapSphere(knife.transform.position, meleeRange);
        foreach (Collider hitCollider in hitColliders)
        {   
            if (hitCollider.gameObject.tag == "Player" || hitCollider.gameObject.tag == "Trivial Player" || hitCollider.gameObject.tag == "CBR Player") {
                foreach (Player hitPlayer in GameControllerScript.mPlayers){
                    if (hitCollider.gameObject.name == hitPlayer.mName){
                        Debug.Log(hitPlayer.mName + " was hit with a knife");
                        hitPlayer.TakeDamage(100);
                        hitPlayer.mIsAlive = hitPlayer.mPlayerHealth > 0;
                               
                    }
                }
            }  
        }    
    }

    private void ResetAttack()
    {
        readyToAttack = true;
    }
}
