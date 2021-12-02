package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.adapters.ExerciseAdapter
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.adapters.WorkoutAdapter
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityBusyScheduleWorkoutBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityMyWorkoutBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.ExerciseModel
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.WorkoutModel

class MyWorkoutExercisesActivity : AppCompatActivity() {
    // Set what xml file you want to access
    var binding: ActivityMyWorkoutBinding? = null
    // One responsible for populating the userList
    var exerciseAdapter: ExerciseAdapter? = null
    // Content of the data
    var exerciseList = ArrayList<ExerciseModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyWorkoutBinding.inflate(layoutInflater)
        setContentView(binding!!.root)


        populateList()
        // populates the exercise adapter
        exerciseAdapter = ExerciseAdapter(applicationContext, exerciseList)
        // When using an adapter you can set the layout manager
        // Grid or linear layout pwede gamitin
        //Makes it scrollable if there are many content
        binding!!.rvWorkoutList.layoutManager = LinearLayoutManager(applicationContext,
            LinearLayoutManager.VERTICAL,
            false)

        binding!!.rvWorkoutList.adapter = exerciseAdapter

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