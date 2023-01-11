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

    public float meleeRange;
    public float attackCooldown;

    bool readyToAttack;

    void Start()
    {
        anim = GetComponent<Animator>();
    }

    // Update is called once per frame
    void Update()
    {
        if (Input.GetKeyDown(meleeKey)){
            anim.SetBool("Active", true);
            Attack();
        }
            
    }
    private void Attack()
    {
        
        readyToAttack = false;

        GameObject knife = Instantiate(knifeObject, attackPoint.position, cam.rotation);

        //Messer wird erzeugt, muss noch entfernt werden

        //Rigidbody knifeRb = knife.GetComponent<Rigidbody>();
        Debug.Log(knife);
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
