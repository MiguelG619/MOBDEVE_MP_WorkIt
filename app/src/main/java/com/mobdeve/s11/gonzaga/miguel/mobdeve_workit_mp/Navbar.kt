package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class Navbar(bottomNavigationView: BottomNavigationView, appCon: Context, navItem: Int) {

    init {

        bottomNavigationView.selectedItemId = navItem
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(appCon, HomeActivity::class.java)
                    appCon.startActivity(intent)
                }
                R.id.nav_streak -> {
                    val intent = Intent(appCon, StreakActivity::class.java)
                    appCon.startActivity(intent)
                }R.id.nav_reminder -> {
                    val intent = Intent(appCon, ReminderActivity::class.java)
                    appCon.startActivity(intent)
                }
                R.id.nav_logout -> {
                    val intent = Intent(appCon, LogOutActivity::class.java)
                    appCon.startActivity(intent)
                }
            }
            true
        }
    }


}