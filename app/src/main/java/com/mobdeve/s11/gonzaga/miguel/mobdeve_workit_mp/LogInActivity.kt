package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.facebook.appevents.AppEventsLogger;
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityBusyScheduleWorkoutBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityLogInBinding
import java.util.Arrays.asList

import com.facebook.login.LoginResult

import android.R
import android.util.Log
import android.view.View

import com.facebook.login.widget.LoginButton
import java.util.*
import android.content.Intent
import com.facebook.*
import org.json.JSONObject


class LogInActivity : AppCompatActivity() {

    var callbackManager: CallbackManager? = null
    var binding: ActivityLogInBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        var fbLogIn = binding!!.btnFBLogIn

        callbackManager = CallbackManager.Factory.create()
        fbLogIn.setPermissions(Arrays.asList("user_friends"))


        // Callback registration
        fbLogIn.registerCallback(callbackManager, object : FacebookCallback<LoginResult?> {
            override fun onSuccess(loginResult: LoginResult?) {
                Log.d("Demo", "Login successful.")
                val graphRequest = GraphRequest.newMeRequest(loginResult?.accessToken) {
                    `object`, response ->
                    getFacebookData(`object`!!)
                }
                val parameters = Bundle()
                parameters.putString("fields", "first_name")
                graphRequest.parameters = parameters
                graphRequest.executeAsync()

                val gotoHomeActivity = Intent(applicationContext, HomeActivity::class.java)
                //Passing data to from one page to another or in this case a string
                //Passing objects or big data
                gotoHomeActivity.putExtra("extra_firstName", graphRequest.parameters.toString())

                startActivity(gotoHomeActivity)
                // Destroys the originating activity to prevent hackers
                finish()

            }

            override fun onCancel() {
                Log.d("Demo", "Login canceled.")
            }

            override fun onError(exception: FacebookException) {
                Log.d("Demo", "Login error.")
            }


        })
    }



    private fun getFacebookData(obj: JSONObject) {
        val firstName = obj?.getString("first_name")
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager!!.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}