package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*

import com.facebook.login.LoginResult

import android.content.Intent
import android.util.Log
import com.facebook.*
import com.facebook.login.LoginManager
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityLogInBinding


class LogInActivity : AppCompatActivity() {

    var binding: ActivityLogInBinding? = null
    var callbackManager: CallbackManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding!!.root)


        binding!!.mcvLogin.setOnClickListener {
            if (isUserInputValid() === true) {

            val gotoHomeActivity  = Intent(applicationContext, HomeActivity::class.java)
            //Passing data to from one page to another or in this case a string based on the name of the user
            //  gotoHomeActivity.putExtra("firstNameExtra", username)
            startActivity(gotoHomeActivity)
            // Destroys the originating activity to prevent hackers
            finish()
            }
        }

        binding!!.tvDontHaveAc.setOnClickListener {
            val gotoRegisterActivity  = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(gotoRegisterActivity)
            finish()
        }

        var fbLogIn = binding!!.btnFBLogIn

        callbackManager = CallbackManager.Factory.create()
        fbLogIn.setPermissions(Arrays.asList("user_friends"))

        // Callback registration
        fbLogIn.registerCallback(callbackManager, object : FacebookCallback<LoginResult?> {
            override fun onSuccess(result: LoginResult?) {
                val graphRequest = GraphRequest.newMeRequest(result?.accessToken) { obj, response ->

                    try {
                        if (obj!!.has("id")) {
                            var firstName = obj.getString("first_name")
                            Log.d("facebookdata", obj.getString("name"))
                            Log.d("facebookdata", firstName)

                            val gotoHomeActivity  = Intent(applicationContext, HomeActivity::class.java)
                            //Passing data to from one page to another or in this case a string
                            gotoHomeActivity.putExtra("firstNameExtra", firstName)
                            startActivity(gotoHomeActivity)
                            // Destroys the originating activity to prevent hackers
                            finish()

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

    fun isUserInputValid(): Boolean {
        var valid: Boolean = true
        // Check if edit text is empty and valid --> valid not yet coded
       if (binding!!.edtEmail.text.toString().trim().isEmpty()) {
           binding!!.edtEmail.error = "Please enter your correct email address."
           valid = false
       }

        // Check if edit text is empty and valid --> valid not yet coded
        if (binding!!.edtPwd.text.toString().trim().isEmpty()) {
            binding!!.edtPwd.error = "Please enter your correct password."
            valid = false
        }


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