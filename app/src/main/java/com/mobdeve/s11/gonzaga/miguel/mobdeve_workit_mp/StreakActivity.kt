package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.facebook.CallbackManager
import com.facebook.share.model.SharePhoto
import com.facebook.share.model.SharePhotoContent
import com.facebook.share.widget.ShareDialog
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityStreakBinding
import android.graphics.Bitmap
import android.view.View
import android.graphics.Canvas
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import com.facebook.FacebookSdk
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.utils.SharedPrefUtility


class StreakActivity : AppCompatActivity() {

    lateinit var callbackManager: CallbackManager
    lateinit var shareDialog: ShareDialog
    lateinit var binding: ActivityStreakBinding
    lateinit var sharePhotoContent: SharePhotoContent

    lateinit var sharedPrefUtility: SharedPrefUtility
    lateinit var STREAK: String
    var streak = 0

    // Get latest photo or let user choose the screenshotted picture
    /*imageView.setImageResource(R.drawable.story)*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStreakBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hides the action bar, initializes the navbar, and initialzies the shared prefs
        supportActionBar?.hide()
        Navbar(findViewById(R.id.bottom_navigation), this, R.id.nav_streak)
        initPrefs()

        FacebookSdk.sdkInitialize(applicationContext);

        callbackManager = CallbackManager.Factory.create()
        shareDialog = ShareDialog(this)
        loadData()
        Log.d("sdfsdfdsffsd", "loadData: "+ streak)
        binding.tvNumber.text = streak.toString()


        binding.btnShareLink.setOnClickListener {
            //startForResult.launch(Intent(this, AnotherActivity::class.java))
            //Screenshots the streak to be posted
            var bitmap = screenShot()
            binding.btnShareLink.visibility = View.VISIBLE
            binding.bottomNavigation.visibility = View.VISIBLE

            var sharePhoto = SharePhoto.Builder()
                .setBitmap(bitmap)
                .build()

            sharePhotoContent = SharePhotoContent.Builder()
                .addPhoto(sharePhoto)
                .build()

            shareDialog.show(sharePhotoContent)
        }
    }

    fun initPrefs() {
        sharedPrefUtility = SharedPrefUtility(this)
    }
 /*   override fun onResume() {
        super.onResume()
        loadData()
    }*/

    fun loadData() {
        STREAK = (this.application as GlobalVariables).id + "streak"
        (this.application as GlobalVariables).streak = sharedPrefUtility.getIntegerPreferences(STREAK)!!
        streak = (this.application as GlobalVariables).streak
    }


    /*fun saveData() {
        sharedPrefUtility.saveIntegerPreferences(STREAK, streak)
    }

    override fun onPause() {
        super.onPause()
        saveData()
    }*/


    fun screenShot(): Bitmap? {
        binding.btnShareLink.visibility = View.GONE
        binding.bottomNavigation.visibility = View.GONE
        var view = window.decorView.rootView
        val bitmap = Bitmap.createBitmap(
            view.width,
            view.height, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }



}