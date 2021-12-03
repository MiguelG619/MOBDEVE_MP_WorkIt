package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.adapters.WorkoutAdapter
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityMyWorkoutBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.ExerciseModel
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.WorkoutModel

class MyWorkoutActivity : AppCompatActivity() {
    // Set what xml file you want to access
    var binding: ActivityMyWorkoutBinding? = null
    // One responsible for populating the userList
    var workoutAdapter: WorkoutAdapter? = null
    // Content of the data
    var workoutList = ArrayList<WorkoutModel>()
    var exerciseList = ArrayList<ExerciseModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyWorkoutBinding.inflate(layoutInflater)
        setContentView(binding!!.root)



        populateList()
        // populates the user adapter
        workoutAdapter = WorkoutAdapter(applicationContext, workoutList)
        // When using an adapter you can set the layout manager
        // Grid or linear layout pwede gamitin
        //Makes it scrollable if there are many content
        binding!!.rvWorkoutList.layoutManager = LinearLayoutManager(applicationContext,
            LinearLayoutManager.VERTICAL,
            false)

        binding!!.rvWorkoutList.adapter = workoutAdapter
       /* R.id..setOnClickListener {
            val gotoMyWorkoutActivity = Intent(applicationContext, MyWorkoutActivity::class.java)
            startActivity(gotoMyWorkoutActivity)
            // Destroys the originating activity to prevent hackers
            finish()
        }*/

        binding!!.tvCreate.setOnClickListener {
            val gotoCreateWorkoutActivity = Intent(applicationContext, CreateWorkoutActivity::class.java)
            startActivity(gotoCreateWorkoutActivity)
            finish()
        }

    }

    fun populateList() {

        var i: Int = 0

        while (i < 3) {
            exerciseList.add(ExerciseModel("Burst Workout", "Jumping Jacks" ,
                "A conditioning exercise performed from a standing position by jumping to a position." +
                        "with legs spread and arms raised and then to the original position.", 30, 3, 10))

            i++
        }
        workoutList.add(
            WorkoutModel("Created Workout 1", exerciseList))

    }

}