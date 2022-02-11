package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*

import com.facebook.login.LoginResult

import android.content.Intent
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.facebook.*
import com.facebook.login.LoginManager
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityLogInBinding


class LogInActivity : AppCompatActivity() {

    var binding: ActivityLogInBinding? = null
    var callbackManager: CallbackManager? = null
    private lateinit var auth: FirebaseAuth
    lateinit var email: String
    lateinit var password: String
    var firstName: String = "User"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)

        supportActionBar?.hide()

        setContentView(binding!!.root)

        auth = Firebase.auth

        binding!!.mcvLogin.setOnClickListener {
            if (isUserInputValid()) {
                doLogin()
            }
        }

        binding!!.tvDontHaveAc.setOnClickListener {
            val gotoRegisterActivity  = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(gotoRegisterActivity)
            finish()
        }

        var fbLogIn = binding!!.btnFBLogIn
        firstName = "Sean"

        callbackManager = CallbackManager.Factory.create()
        fbLogIn.setPermissions(Arrays.asList("user_friends"))

        // Callback registration
        fbLogIn.registerCallback(callbackManager, object : FacebookCallback<LoginResult?> {
            override fun onSuccess(loginResult: LoginResult?) {
                val graphRequest = GraphRequest.newMeRequest(loginResult?.accessToken) { obj, response ->

                    try {
                        if (obj!!.has("id")) {
                            firstName = obj.getString("first_name")
                            Log.d("facebookdata", obj.getString("name"))
                            Log.d("facebookdata", firstName)

                            handleFacebookAccessToken(loginResult!!.accessToken)


                        }
                    } catch (e: Exception) {
                        e.stackTrace
                    }
                }

                val param = Bundle()
                param.putString("fields", "name, first_name")
                graphRequest.parameters = param
                graphRequest.executeAsync()

            }

            override fun onCancel() {
                // App code
            }

            override fun onError(exception: FacebookException) {
                // App code
            }
        })

    }

     override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
         // Check if there is user logged in
        if(currentUser != null){
            val gotoHomeActivity  = Intent(applicationContext, HomeActivity::class.java)
            //Passing data to from one page to another or in this case a string
            //gotoHomeActivity.putExtra("firstNameExtra", firstName)
            gotoHomeActivity.putExtra("firstNameExtra", firstName)
            startActivity(gotoHomeActivity)
            // Destroys the originating activity to prevent hackers
            finish()
        }
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d("TESSTTTT", "handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TESSTTTT", "signInWithCredential:success")
                    val user = auth.currentUser

                    val gotoHomeActivity  = Intent(applicationContext, HomeActivity::class.java)
                    //Passing data to from one page to another or in this case a string
                    //gotoHomeActivity.putExtra("firstNameExtra", firstName)
                    gotoHomeActivity.putExtra("firstNameExtra", firstName)
                    startActivity(gotoHomeActivity)
                            // Destroys the originating activity to prevent hackers
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TESSTTTT", "signInWithCredential:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    fun doLogin() {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    Log.d("EEEEEEEEEEEEEEEeeeewe", "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Log.w("EEEEEEEEEEEEEEEeeeewe", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Wrong information given. Sign up if you don't have an account",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    fun updateUI(user: FirebaseUser?) {
        if(user != null){
            val gotoHomeActivity  = Intent(applicationContext, HomeActivity::class.java)
            //Passing data to from one page to another or in this case a string
            gotoHomeActivity.putExtra("firstNameExtra", firstName)
            startActivity(gotoHomeActivity)
            finish()
        }
    }

    fun isUserInputValid(): Boolean {
        var valid: Boolean = true
        // Check if edit text is empty and valid --> valid not yet coded
        if (binding!!.edtEmail.text.toString().trim().isEmpty()) {
            binding!!.edtEmail.error = "Please enter your email address."
            binding!!.edtEmail.requestFocus()
            valid = false
        } else email = binding!!.edtEmail.text.toString()

        if (!Patterns.EMAIL_ADDRESS.matcher(binding!!.edtEmail.text).matches()) {
            binding!!.edtEmail.error = "Please enter a valid email address."
            binding!!.edtEmail.requestFocus()
            valid = false
        } else email = binding!!.edtEmail.text.toString()

        // Check if edit text is empty and valid --> valid not yet coded
        if (binding!!.edtPwd.text.toString().trim().isEmpty()) {
            binding!!.edtPwd.error = "Please enter your correct password."
            binding!!.edtPwd.requestFocus()
            valid = false
        } else password = binding!!.edtPwd.text.toString()


        return valid
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager!!.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    var accessTokenTracker = object: AccessTokenTracker() {
        override fun onCurrentAccessTokenChanged(
            oldAccessToken: AccessToken?,
            currentAccessToken: AccessToken?
        ) {
            if (currentAccessToken == null)
                LoginManager.getInstance().logOut()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        accessTokenTracker.stopTracking()
    }


}