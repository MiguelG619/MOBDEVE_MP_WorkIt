package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityRegisterBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.User

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    lateinit var firstName: String
    lateinit var lastName: String
    lateinit var email: String
    lateinit var password: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        // Initialize Firebase Auth
        auth = Firebase.auth



        binding.mcvContinue.setOnClickListener {

            /*if (isUserInputValid()) {*/
                auth.createUserWithEmailAndPassword("email6@gmail.com", "123456")
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            var user = User("Karl", "yu", "email5@gmail.com")
                           /* var userID = Firebase.auth.currentUser!!.uid
                            Toast.makeText(
                                this,
                                "${user.firstName} \n ${user.lastName} \n ${user.email} \n ${userID}",
                                Toast.LENGTH_SHORT
                            ).show()*/


                            var userId = Firebase.auth.currentUser!!.uid
                            /*database = Firebase.database.reference
                            database.child("users").child(userId).setValue(user)*/
                                    FirebaseDatabase.getInstance().getReference("users")
                                        .child(userId).setValue(user)
                                .addOnCompleteListener(this) { task ->
                                    if (task.isSuccessful) {
                                        Toast.makeText(
                                            this,
                                            "${user.firstName} \n ${user.lastName} \n ${user.email} \n ${userId} SUCESS",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else {
                                        Toast.makeText(
                                            this,
                                            "fail",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }



                            /*val gotoHomeActivity  = Intent(applicationContext, HomeActivity::class.java)
                            startActivity(gotoHomeActivity)
                            finish()*/

                        } else {
                            // If sign in fails, display a message to the user.
                            try {
                                throw task.getException()!!;
                            } catch (e: FirebaseAuthUserCollisionException) {
                                Log.d(
                                    "ERRrrrrrrrrrr",
                                    "FirebaseAuthUserCollisionException " + e.toString()
                                )
                                // show error toast ot user ,user already exist
                            } catch (e: FirebaseNetworkException) {
                                Log.d("ERRrrrrrrrrrr", "FirebaseNetworkException " + e.toString())
                                //show error tost network exception
                            } catch (e: Exception) {
                                Log.d("ERRrrrrrrrrrr", "Exception " + e.toString())
                            }
                        }
                        //updateUI(null)
                    }
            /*} else {
                Toast.makeText(this, "Failed to register! Try again!", Toast.LENGTH_SHORT).show()
            }*/
        }
        }






    fun isUserInputValid(): Boolean {
        var valid = true
        var setPW= true
        var rePW= true

        if (binding.edtFname.text.toString().trim().isEmpty()) {
            binding.edtFname.error = "Please enter your first name."
            binding.edtFname.requestFocus()
            valid = false
        } else firstName = binding.edtFname.text.toString().capitalize()

        if (binding.edtLname.text.toString().trim().isEmpty()) {
            binding.edtLname.error = "Please enter your last name."
            binding.edtLname.requestFocus()
            valid = false
        } else lastName = binding.edtLname.text.toString().capitalize()

        if (binding.edtEmail.text.toString().trim().isEmpty()) {
            binding.edtEmail.error = "Please enter your email."
            binding.edtEmail.requestFocus()
            valid = false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(binding.edtEmail.text).matches()) {
            binding.edtEmail.error = "Please enter your email."
            binding.edtEmail.requestFocus()
            valid = false
        } else email = binding.edtEmail.text.toString()

        if (binding.edtPwd.text.toString().trim().isEmpty()) {
            binding.edtPwd.error = "Please enter your password."
            binding.edtPwd.requestFocus()
            setPW = false
            valid = false
        }

        if (binding.edtPwd.text.length < 6) {
            binding.edtPwd.error = "Minimum password length should be six (6) characters."
            binding.edtPwd.requestFocus()
            setPW = false
            valid = false
        }


        if (binding.edtCpwd.text.toString().trim().isEmpty()) {
            binding.edtCpwd.error = "Please enter your password."
            binding.edtCpwd.requestFocus()
            rePW = false
            valid = false
        }


        if (!binding.edtCpwd.text.toString().equals(binding.edtPwd.text.toString())  && setPW && rePW) {
            binding.edtPwd.error = "Please enter the same password."
            binding.edtCpwd.error = "Please enter the same password."
            valid = false
        } else password = binding.edtPwd.text.toString()

        if (valid && !binding.cbTerms.isChecked) {
            binding.cbTerms.error = valid.toString()
            valid = false
        } else binding.cbTerms.error = null


        return valid
    }
}