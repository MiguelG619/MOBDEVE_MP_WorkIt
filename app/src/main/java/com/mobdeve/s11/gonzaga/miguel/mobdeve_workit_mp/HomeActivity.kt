package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.dataAccessObjects.WorkoutDAOArrayList
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityHomeBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.WorkoutModel
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.utils.SharedPrefUtility
import java.text.SimpleDateFormat
import java.util.*

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    lateinit var firstName: String
    lateinit var sharedPrefUtility: SharedPrefUtility
    lateinit var WORKOUT: String
    var myWorkouts: ArrayList<WorkoutModel?> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        supportActionBar?.hide()

        setContentView(binding.root)

        initPrefs()
        loadData()


        /*if (intent.hasExtra("firstNameExtra")) {
            firstName = intent.getStringExtra("firstNameExtra").toString()
        }*/
        firstName = (this.application as GlobalVariables).name
        binding!!.tvUsername.text = "Hello, $firstName"

        val c: Date = Calendar.getInstance().time

        val df = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
        val formattedDate: String = df.format(c)

        binding.tvDate.text = formattedDate


        Navbar(findViewById(R.id.bottom_navigation), this, R.id.nav_home)
        var myWorkoutsNumber = myWorkouts.size
        Log.d("zzzzz", "loadData: "+myWorkoutsNumber)
        //val myWorkoutsNumber = WorkoutDAOArrayList().myWorkoutList.size
        binding.tvDaysMyWorkout.text = "$myWorkoutsNumber Workouts"

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

    //load data here
    fun initPrefs() {
        sharedPrefUtility = SharedPrefUtility(this)
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    fun loadData() {
        WORKOUT = (this.application as GlobalVariables).id + "myWorkouts"
        (this.application as GlobalVariables).myWorkoutList = sharedPrefUtility.getMyWorkouts(WORKOUT)
        myWorkouts = (this.application as GlobalVariables).myWorkoutList

    }
}