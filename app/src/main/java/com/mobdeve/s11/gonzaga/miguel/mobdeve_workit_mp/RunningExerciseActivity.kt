package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityRunningExerciseBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.ExerciseModel
import java.util.ArrayList

class RunningExerciseActivity : AppCompatActivity() {

    lateinit var binding: ActivityRunningExerciseBinding
    var exerciseList: ArrayList<ExerciseModel?> = ArrayList()
    lateinit var exercises: ArrayList<ExerciseModel?>
    var status: String = "On Progress"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRunningExerciseBinding.inflate(layoutInflater)

        supportActionBar?.hide()
        setContentView(binding.root)

        var exerciseNumber = intent.getIntExtra("exerciseNumber", 0)
        val position = intent.getIntExtra("position", 10)
        var doneSets = intent.getIntExtra("doneSets", 0)
        Toast.makeText(this, doneSets.toString() + "doneSets ruunning", Toast.LENGTH_SHORT).show()
        val workoutName = intent.getStringExtra("workoutName")
        val args = intent.getBundleExtra("BUNDLE")
        exercises = args!!.getSerializable("ARRAYLIST") as ArrayList<ExerciseModel?>
        binding.tvExerciseTitle.text = exercises.get(exerciseNumber)?.title
        binding.imageExercise.setImageResource(exercises.get(exerciseNumber)?.image!!)
        binding.tvReps.text = "${exercises?.get(exerciseNumber)?.reps} Reps"
        var sets = exercises?.get(exerciseNumber)?.sets
        binding.tvSets.text = "${doneSets} out of ${sets} Sets Done"



        binding.tvDone.setOnClickListener {

            val restTime = exercises.get(exerciseNumber)?.restTime
            val gotoRestActivity  = Intent(applicationContext, RestTimeActivity::class.java)

            if (exerciseNumber+1 == exercises.size)
                status = "Done"
            val args = Bundle()
            args.putSerializable("ARRAYLIST", exerciseList)
            gotoRestActivity.putExtra("BUNDLE", args)
            gotoRestActivity.putExtra("status", status)
            gotoRestActivity.putExtra("sets", sets)
            gotoRestActivity.putExtra("doneSets", doneSets)
            gotoRestActivity.putExtra("position", position)
            gotoRestActivity.putExtra("workoutName", workoutName)
            gotoRestActivity.putExtra("exerciseNumber", exerciseNumber)
            gotoRestActivity.putExtra("restTime", restTime)
            startActivity(gotoRestActivity)
            finish()
        }
    }

}