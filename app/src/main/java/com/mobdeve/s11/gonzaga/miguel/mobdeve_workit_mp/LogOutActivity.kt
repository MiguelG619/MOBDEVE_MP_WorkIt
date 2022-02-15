package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.facebook.FacebookSdk
import com.facebook.login.LoginManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityLogOutBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityStreakBinding

class LogOutActivity : AppCompatActivity() {

    lateinit var binding: ActivityLogOutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogOutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hides the aciton bar and initializes the navbar
        supportActionBar?.hide()
        Navbar(findViewById(R.id.bottom_navigation), this, R.id.nav_logout)

        FacebookSdk.sdkInitialize(getApplicationContext())

        binding.btnYes.setOnClickListener {
            (this.application as GlobalVariables).streak = 0
            //  Logout the user first
            // Logout FB and the firebase
            LoginManager.getInstance().logOut();
            Firebase.auth.signOut()
            var gotoLogInActivity = Intent(applicationContext, LogInActivity::class.java)
            startActivity(gotoLogInActivity)
            finish()
        }

        binding.btnNo.setOnClickListener {
            var gotoHomeActivity = Intent(applicationContext, HomeActivity::class.java)
            startActivity(gotoHomeActivity)
            finish()
        }

    }
}