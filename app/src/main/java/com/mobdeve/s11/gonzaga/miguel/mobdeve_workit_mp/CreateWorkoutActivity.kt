package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.adapters.ExerciseAdapter
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityBusyScheduleWorkoutBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityCreateWorkoutBinding

import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.ExerciseModel

class CreateWorkoutActivity : AppCompatActivity() {
    // Set what xml file you want to access
    var binding: ActivityCreateWorkoutBinding? = null
    // One responsible for populating the userList
    var exerciseAdapter: ExerciseAdapter? = null
    // Content of the data
    var exerciseList = ArrayList<ExerciseModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateWorkoutBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        populateList()
        exerciseAdapter = ExerciseAdapter(applicationContext, exerciseList)
        binding!!.rvExerciseList.layoutManager = LinearLayoutManager(applicationContext,
            LinearLayoutManager.VERTICAL,
            false)

        binding!!.tvAddExercise.setOnClickListener {
            val gotoRunningExerciseActivity = Intent(applicationContext, RunningExerciseActivity::class.java)
            startActivity(gotoRunningExerciseActivity)

            finish()
        }

        binding!!.tvDone.setOnClickListener {
          // Add popup
        }
    }
    fun populateList() {

        exerciseList.add(ExerciseModel("Burst Workout", "Jumping Jacks" ,
            "A conditioning exercise performed from a standing position by jumping to a position." +
                    "with legs spread and arms raised and then to the original position.", 30, 3, 10))
        exerciseList.add(ExerciseModel("Burst Workout", "Jumping Jacks" ,
            "A conditioning exercise performed from a standing position by jumping to a position." +
                    "with legs spread and arms raised and then to the original position.", 30, 3, 10))
        exerciseList.add(ExerciseModel("Burst Workout", "Jumping Jacks" ,
            "A conditioning exercise performed from a standing position by jumping to a position." +
                    "with legs spread and arms raised and then to the original position.", 30, 3, 10))
    }
}