package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import android.app.Activity
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.facebook.CallbackManager
import com.facebook.share.model.SharePhoto
import com.facebook.share.model.SharePhotoContent
import com.facebook.share.widget.ShareDialog
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityStreakBinding
import android.graphics.Bitmap

import android.os.Environment
import android.text.format.DateFormat
import android.view.View
import java.io.File
import java.io.FileOutputStream
import java.util.*
import android.content.Intent
import android.graphics.Canvas
import android.net.Uri
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.facebook.FacebookCallback
import com.facebook.FacebookSdk
import com.facebook.share.Sharer
import com.google.android.material.bottomnavigation.BottomNavigationView


class StreakActivity : AppCompatActivity() {

    lateinit var callbackManager: CallbackManager
    lateinit var shareDialog: ShareDialog
    lateinit var binding: ActivityStreakBinding
    lateinit var sharePhotoContent: SharePhotoContent

    // Get latest photo or let user choose the screenshotted picture
    /*imageView.setImageResource(R.drawable.story)*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStreakBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FacebookSdk.sdkInitialize(applicationContext);


        callbackManager = CallbackManager.Factory.create()
        shareDialog = ShareDialog(this)

        binding.btnShareLink.setOnClickListener {
            //startForResult.launch(Intent(this, AnotherActivity::class.java))
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

        var navbar = Navbar(findViewById<BottomNavigationView>(R.id.bottom_navigation), this, R.id.nav_streak)
        /*binding.bottomNavigation.selectedItemId = R.id.nav_home
        binding.bottomNavigation.selectedItemId = R.id.nav_home
        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> startActivity(Intent(applicationContext, HomeActivity::class.java))
                R.id.nav_streak -> startActivity(Intent(applicationContext, StreakActivity::class.java))
                //R.id.nav_reminder -> startActivity(Intent(applicationContext, ReminderActivity::class.java))
                //R.id.nav_logout -> startActivity(Intent(applicationContext, LogOutActivity::class.java))
            }
            true
        }*/


    }




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