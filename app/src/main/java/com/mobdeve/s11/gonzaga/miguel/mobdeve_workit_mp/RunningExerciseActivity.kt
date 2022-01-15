package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityRunningExerciseBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.ExerciseModel
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.utils.SharedPrefUtility
import java.util.ArrayList

class RunningExerciseActivity : AppCompatActivity() {

    lateinit var binding: ActivityRunningExerciseBinding
    var exerciseList: ArrayList<ExerciseModel?> = ArrayList()
    lateinit var exercises: ArrayList<ExerciseModel?>
    var status: String = "On Progress"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRunningExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var exerciseNumber = intent.getIntExtra("exerciseNumber", 0)
        val position = intent.getIntExtra("position", 10)
        val workoutName = intent.getStringExtra("workoutName")
        val args = intent.getBundleExtra("BUNDLE")
        exercises = args!!.getSerializable("ARRAYLIST") as ArrayList<ExerciseModel?>
        Toast.makeText(this, exerciseNumber.toString(),
            Toast.LENGTH_LONG).show()

        binding.tvExerciseTitle.text = exercises?.get(exerciseNumber)?.title
        binding.tvReps.text = "${exercises?.get(exerciseNumber)?.reps} Reps"
        binding.tvSets.text = "${exercises?.get(exerciseNumber)?.sets} Sets"



        binding.tvDone.setOnClickListener {

            val restTime = exercises?.get(exerciseNumber)?.resTime
            val gotoRestActivity  = Intent(applicationContext, RestTimeActivity::class.java)
            if (exerciseNumber == exercises.size)
                status = "Done"
            val args = Bundle()
            args.putSerializable("ARRAYLIST", exerciseList)
            gotoRestActivity.putExtra("BUNDLE", args)
            gotoRestActivity.putExtra("status", status)
            gotoRestActivity.putExtra("position", position)
            gotoRestActivity.putExtra("workoutName", workoutName)
            gotoRestActivity.putExtra("exerciseNumber", exerciseNumber)
            //Passing data to from one page to another or in this case a string
            gotoRestActivity.putExtra("restTime", restTime)
            startActivity(gotoRestActivity)
            finish()
            //receive if done then change
        }
    }

}