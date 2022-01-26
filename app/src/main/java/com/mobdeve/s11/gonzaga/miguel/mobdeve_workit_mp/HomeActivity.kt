package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.dataAccessObjects.WorkoutDAOArrayList
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityHomeBinding
import java.text.SimpleDateFormat
import java.util.*

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    var firstName = "User"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        if (intent.hasExtra("firstNameExtra")) {
            firstName = intent.getStringExtra("firstNameExtra").toString()
        }
        binding!!.tvUsername.text = "Hello, $firstName"

        val c: Date = Calendar.getInstance().time

        val df = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
        val formattedDate: String = df.format(c)

        binding.tvDate.text = formattedDate


        Navbar(findViewById<BottomNavigationView>(R.id.bottom_navigation), this, R.id.nav_home)

        var myWorkoutsNumber = (this.application as GlobalVariables).myWorkoutList.size
        //val myWorkoutsNumber = WorkoutDAOArrayList().myWorkoutList.size
        binding.tvDaysMyWorkout.text = "${myWorkoutsNumber.toString()} Workouts"

         binding.mcvRowBurst.setOnClickListener {
             val gotoBurstActivity = Intent(applicationContext, BurstWorkoutActivity::class.java)
             startActivity(gotoBurstActivity)
         }
         binding.mcvRowBusy.setOnClickListener {
             val gotoBusyScheduleWorkoutActivityActivity = Intent(applicationContext, BusyScheduleWorkoutActivity::class.java)
             startActivity(gotoBusyScheduleWorkoutActivityActivity)
         }
        binding.mcvRowMyWorkout.setOnClickListener {
            val gotoMyWorkoutActivityActivity = Intent(applicationContext, MyWorkoutActivity::class.java)
            startActivity(gotoMyWorkoutActivityActivity)
        }
    }
}