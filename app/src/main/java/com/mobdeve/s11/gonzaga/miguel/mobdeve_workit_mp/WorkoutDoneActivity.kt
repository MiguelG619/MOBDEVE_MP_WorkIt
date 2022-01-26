package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityWorkoutDoneBinding

class WorkoutDoneActivity : AppCompatActivity() {

    lateinit var binding: ActivityWorkoutDoneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkoutDoneBinding.inflate(layoutInflater)

        supportActionBar?.hide()

        setContentView(binding.root)

        val workoutName = intent.getStringExtra("workoutName")
        var subWorkout = intent.getIntExtra("day", 0).toString()
        val exerciseNumber = intent.getIntExtra("exerciseNumber", 10)

        if (workoutName.equals("Busy Workout") || workoutName.equals("Burst Workout")) {
            subWorkout = "Day ${subWorkout}"
        }

        binding.tvWorkoutName.text = "You completed the \n ${subWorkout} \n of the \n ${workoutName}"
        binding.tvNumberOfExercises.text = "${exerciseNumber} Exercises Finished"

        binding.tvDone.setOnClickListener {
            (this.application as GlobalVariables).streak++
            var gotoHomeActivity = Intent(applicationContext, HomeActivity::class.java)
            startActivity(gotoHomeActivity)
            finish()
        }
    }
}