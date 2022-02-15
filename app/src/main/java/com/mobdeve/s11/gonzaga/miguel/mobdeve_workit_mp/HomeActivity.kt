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



        setContentView(binding.root)

        // Initializes sharedpreferences data, hides actionbar,
        // and initializes the navbar
        initPrefs()
        loadData()
        supportActionBar?.hide()
        Navbar(findViewById(R.id.bottom_navigation), this, R.id.nav_home)


        // Gets the name of the logged in user
        firstName = (this.application as GlobalVariables).name
        binding!!.tvUsername.text = "Hello, $firstName"

        // Format date to current time
        val c: Date = Calendar.getInstance().time
        val df = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
        val formattedDate: String = df.format(c)
        binding.tvDate.text = formattedDate

        //Sets the number of workouts created
        var myWorkoutsNumber = myWorkouts.size
        binding.tvDaysMyWorkout.text = "$myWorkoutsNumber Workouts"

        // Goes to Burst Workout
         binding.mcvRowBurst.setOnClickListener {
             val gotoBurstActivity = Intent(applicationContext, BurstWorkoutActivity::class.java)
             startActivity(gotoBurstActivity)
         }

        // Goes to Busy Schedule Workout
         binding.mcvRowBusy.setOnClickListener {
             val gotoBusyScheduleWorkoutActivityActivity = Intent(applicationContext, BusyScheduleWorkoutActivity::class.java)
             startActivity(gotoBusyScheduleWorkoutActivityActivity)
         }

        // Goes to My Workout
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