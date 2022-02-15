package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityRegisterBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.User
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.utils.SharedPrefUtility

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    lateinit var firstName: String
    lateinit var lastName: String
    lateinit var email: String
    lateinit var password: String
    lateinit var sharedPrefUtility: SharedPrefUtility
    lateinit var USER_ID: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth, hides action bar, and initializes the sharedprefs
        auth = Firebase.auth
        initPrefs()
        supportActionBar?.hide()

        binding.mcvContinue.setOnClickListener {
            firstName = binding.edtFname.text.toString()
            email = binding.edtEmail.text.toString()
            password = binding.edtCpwd.text.toString()
            if (isUserInputValid()) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val user = auth.currentUser
                            updateUI(user)

                        } else {
                            // If sign in fails, display a message to the user.
                            try {
                                throw task.getException()!!;
                            } catch (e: FirebaseAuthUserCollisionException) {
                                Log.d(
                                    "ERRrrrrrrrrrr",
                                    "FirebaseAuthUserCollisionException " + e.toString())
                                Toast.makeText(this, "Failed to register! Email is already in use by another account!",
                                    Toast.LENGTH_SHORT).show()

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
                } else {
                Toast.makeText(this, "Failed to register! Try again!", Toast.LENGTH_SHORT).show()
            }
            }
        }

    fun updateUI(user: FirebaseUser?) {
        if(user != null){
            (this.application as GlobalVariables).id = user.uid.toString()
            saveData()
            Firebase.auth.signOut()
            val gotoHomeActivity  = Intent(applicationContext, LogInActivity::class.java)
            //Passing data to from one page to another or in this case a string
            gotoHomeActivity.putExtra("firstNameExtra", firstName)
            startActivity(gotoHomeActivity)
            finish()
        }
    }

    fun initPrefs() {
        sharedPrefUtility = SharedPrefUtility(this)
    }

    fun saveData() {
        var userId = (this.application as GlobalVariables).id
        USER_ID = userId
        sharedPrefUtility.saveStringPreferences(USER_ID, userId)
        sharedPrefUtility.saveStringPreferences(USER_ID + "firstName", firstName)
       /*Toast.makeText(this, sharedPrefUtility.getStringPreferences(USER_ID + "firstName"), Toast.LENGTH_SHORT).show()*/
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