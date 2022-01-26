package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityAddExerciseToWorkoutBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityBusyScheduleWorkoutBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityCreateWorkoutBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityRunningExerciseBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.ExerciseModel
import java.util.ArrayList

class AddExerciseToWorkoutActivity : AppCompatActivity() {


    var binding: ActivityAddExerciseToWorkoutBinding? = null
    lateinit var exercise: ExerciseModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddExerciseToWorkoutBinding.inflate(layoutInflater)

        supportActionBar?.hide()
        setContentView(binding!!.root)


        Navbar(findViewById(R.id.bottom_navigation), this, R.id.nav_home)

        val exerciseName = intent.getStringExtra("exerciseName")
        val exerciseDescription = intent.getStringExtra("exerciseDescription")
        val exerciseImage = intent.getIntExtra("exerciseImage", 0)
        val exerciseReps = intent.getIntExtra("exerciseReps", 0)
        val exerciseSets = intent.getIntExtra("exerciseSets", 0)
        val exerciseRest = intent.getIntExtra("exerciseRest", 0)

        binding!!.tvExerciseTitle.text = exerciseName
        binding!!.tvExerciseDes.text = exerciseDescription
        binding!!.exerciseImage.setImageResource(exerciseImage)
        binding!!.tvReps.text = "${exerciseReps.toString()} Reps"
        binding!!.tvSets.text = "${exerciseSets.toString()} Sets"
        binding!!.tvTime.text = "${exerciseRest.toString()} Seconds"

        binding!!.tvAdd.setOnClickListener {
            //
            (this.application as GlobalVariables).tempExerciseList.add(ExerciseModel(
                exerciseName,
                exerciseDescription,
                exerciseImage,
                exerciseReps,
                exerciseSets,
                exerciseRest)
            )
            Toast.makeText(this,  exerciseName+ " is added to your custom workout!", Toast.LENGTH_SHORT).show()
            val gotoCreateWorkoutActivity = Intent(applicationContext, CreateWorkoutActivity::class.java)
            startActivity(gotoCreateWorkoutActivity)
        }
    }
}
