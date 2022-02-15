package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityWorkoutDoneBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.utils.SharedPrefUtility

class WorkoutDoneActivity : AppCompatActivity() {

    lateinit var binding: ActivityWorkoutDoneBinding
    lateinit var sharedPrefUtility: SharedPrefUtility
    lateinit var STREAK: String
    var streak = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkoutDoneBinding.inflate(layoutInflater)

        supportActionBar?.hide()

        setContentView(binding.root)

        initPrefs()

        val workoutName = intent.getStringExtra("workoutName")
        var subWorkout = intent.getIntExtra("day", 0).toString()
        val exerciseNumber = intent.getIntExtra("exerciseNumber", 10)

        if (workoutName.equals("Busy Workout") || workoutName.equals("Burst Workout")) {
            subWorkout = "Day ${subWorkout}"
        }

        binding.tvWorkoutName.text = "You completed the \n ${subWorkout} \n of the \n ${workoutName}"
        binding.tvNumberOfExercises.text = "${exerciseNumber} Exercises Finished"
        loadData()
        streak++
        saveData(streak)

        binding.tvDone.setOnClickListener {
            var gotoHomeActivity = Intent(applicationContext, HomeActivity::class.java)
            startActivity(gotoHomeActivity)
            finish()
        }
    }

    fun initPrefs() {
        sharedPrefUtility = SharedPrefUtility(this)
    }

    fun loadData() {
        STREAK = (this.application as GlobalVariables).id + "streak"
        (this.application as GlobalVariables).streak = sharedPrefUtility.getIntegerPreferences(STREAK)!!
        streak = (this.application as GlobalVariables).streak
    }

    fun saveData(streak: Int) {
        (this.application as GlobalVariables).streak = streak
        sharedPrefUtility.saveIntegerPreferences(STREAK, streak)
        var g = sharedPrefUtility.getIntegerPreferences(STREAK)
        Log.d("zzzzzzzzz", STREAK +"saveData: streak "+ streak + "dsdsdsds" + g)

    }

}