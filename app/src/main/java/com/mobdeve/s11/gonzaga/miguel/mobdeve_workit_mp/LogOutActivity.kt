package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.facebook.FacebookSdk
import com.facebook.login.LoginManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityLogOutBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityStreakBinding

class LogOutActivity : AppCompatActivity() {

    lateinit var binding: ActivityLogOutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogOutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var navbar = Navbar(findViewById<BottomNavigationView>(R.id.bottom_navigation), this, R.id.nav_logout)

        FacebookSdk.sdkInitialize(getApplicationContext());

        binding.btnYes.setOnClickListener {
            //  Logout the user first
            // Logout FB
            LoginManager.getInstance().logOut();
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