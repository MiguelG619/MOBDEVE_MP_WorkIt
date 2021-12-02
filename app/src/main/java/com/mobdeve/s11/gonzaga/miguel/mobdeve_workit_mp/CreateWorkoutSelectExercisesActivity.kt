package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.adapters.ExerciseAdapter
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityBusyScheduleWorkoutBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityCreateWorkoutBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityCreateWorkoutSelectExercisesBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.ExerciseModel

class CreateWorkoutSelectExercisesActivity : AppCompatActivity() {
    // Set what xml file you want to access
    var binding: ActivityCreateWorkoutSelectExercisesBinding? = null
    // One responsible for populating the userList
    var exerciseAdapter: ExerciseAdapter? = null
    // Content of the data
    var exerciseList = ArrayList<ExerciseModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateWorkoutSelectExercisesBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        populateList()
        exerciseAdapter = ExerciseAdapter(applicationContext, exerciseList)
        binding!!.rvExerciseList.layoutManager = LinearLayoutManager(applicationContext,
            LinearLayoutManager.VERTICAL,
            false)
        binding!!.rvExerciseList.adapter = exerciseAdapter
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