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
import com.facebook.FacebookSdk
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

        supportActionBar!!.hide()

        FacebookSdk.sdkInitialize(applicationContext);

        callbackManager = CallbackManager.Factory.create()
        shareDialog = ShareDialog(this)

        binding.tvNumber.text = (this.application as GlobalVariables).streak.toString()

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

        Navbar(findViewById<BottomNavigationView>(R.id.bottom_navigation), this, R.id.nav_streak)



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