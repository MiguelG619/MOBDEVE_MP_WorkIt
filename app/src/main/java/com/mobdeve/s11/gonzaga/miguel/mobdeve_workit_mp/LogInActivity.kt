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
import com.facebook.login.LoginManager
import org.json.JSONException
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
            override fun onSuccess(result: LoginResult?) {
                Log.d("Demo", "Login Successful")
                val graphRequest = GraphRequest.newMeRequest(result?.accessToken) {obj, response ->
                    try {
                        if (obj!!.has("id")) {
                            var obj = JSONObject()
                            val name = obj.getString("name")
                            val firstName = obj.getString("first_name")
                            Log.d("facebookdata", name)
                            Log.d("facebookdata", firstName)
                            // assign it to the textview
                            binding!!.tvDontHaveAc.text = name
                        }
                    } catch(e: Exception) {
                        e.printStackTrace()
                }


                }
                val param = Bundle()
                param.putString("fields", "name, first_name")
                graphRequest.parameters = param
                graphRequest.executeAsync()

            }
            override fun onCancel() {

            }

            override fun onError(error: FacebookException) {

            }


        })
    }



    private fun getFacebookData(obj: JSONObject) {
        val firstName = obj?.getString("first_name")
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager!!.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)

        val graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
            GraphRequest.GraphJSONObjectCallback() { jsonObject: JSONObject?, graphResponse: GraphResponse? ->
                fun onCompleted(jsonObject: JSONObject, response: GraphResponse) {
                    Log.d("Demo", jsonObject.toString())
                    try {
                        var name = jsonObject.getString("name")
                        var firstName = jsonObject.getString("first_name")
                        Log.d("facebookdata", name)
                        Log.d("facebookdata", firstName)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            })

        var bundle = Bundle()
        bundle.putString("fields", "first_name")

        graphRequest.parameters = bundle
        graphRequest.executeAsync()

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