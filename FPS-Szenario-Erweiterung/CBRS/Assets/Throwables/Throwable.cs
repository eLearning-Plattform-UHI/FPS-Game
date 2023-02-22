using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using TMPro;
using Assets.Scripts.Model;
using Assets.Scripts.CBR.Model;
using Assets.Scripts.CBR.Plan;


public class Throwable : MonoBehaviour
{

    public Transform cam;
    public Transform attackPoint;
    public GameObject objectToThrow;
    public GameObject ExplosionFx;

    public int totalThrows;
    public float throwCooldown;
    public float explodeCooldown;
    public float explosionRadius;
    public int explosionDamage;

    public KeyCode throwKey = KeyCode.Mouse0;
    public float throwForce;
    public float throwUpwardForce;

    bool readyToThrow;

    private void Start()
    {
        readyToThrow = true;
        InvokeRepeating("Throw", 0, 10f);
    }

    private void Update()
    {
        if(Input.GetKeyDown(throwKey) && readyToThrow && totalThrows > 0)
        {
            Throw();
        }



    }

    public void Throw()
    {   
        readyToThrow = false;
        //Vector3 rotation_rnd = new Vector3(0,Random.Range(0, 360),0);
        GameObject projectile = Instantiate(objectToThrow, attackPoint.position, cam.rotation);
        Rigidbody projectileRb = projectile.GetComponent<Rigidbody>();
        //Vector3 forceDirection = cam.transform.forward;
        Vector3 forceDirection = new Vector3(Random.Range(-3, 3),Random.Range(-3, 3),Random.Range(-1, 1));
        RaycastHit hit;

        if(Physics.Raycast(cam.position, cam.forward, out hit, 500f))
        {
            forceDirection = (hit.point - attackPoint.position).normalized;
        }

        Vector3 forceToAdd = forceDirection * throwForce + transform.up * throwUpwardForce;

        projectileRb.AddForce(forceToAdd, ForceMode.Impulse);

        totalThrows--;

        StartCoroutine(Explode(projectile, explodeCooldown));

        Invoke(nameof(ResetThrow), throwCooldown);
    }

     IEnumerator Explode(GameObject projectile, float delayTime)
    {   
        yield return new WaitForSeconds(delayTime);

        Collider[] hitColliders = Physics.OverlapSphere(projectile.transform.position, explosionRadius);
        foreach (Collider hitCollider in hitColliders)
        {   
            if (hitCollider.gameObject.tag == "Player" || hitCollider.gameObject.tag == "Trivial Player" || hitCollider.gameObject.tag == "CBR Player") {
                foreach (Player hitPlayer in GameControllerScript.mPlayers){
                    if (hitCollider.gameObject.name == hitPlayer.mName){
                        Debug.Log(hitPlayer.mName + " was hit by grenade");
                        hitPlayer.TakeDamage(explosionDamage);
                        hitPlayer.mIsAlive = hitPlayer.mPlayerHealth > 0;

                        

                    }
                }
            }  
        }
        Rigidbody projectileRb = projectile.GetComponent<Rigidbody>();
        Instantiate(ExplosionFx, projectile.transform.position, projectile.transform.rotation);
        Destroy(projectile);
    }


    private void ResetThrow()
    {
        readyToThrow = true;
    }
}