package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.adapters.ExerciseAdapter
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityBusyScheduleWorkoutBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.ExerciseModel


class BusyScheduleWorkoutActivity : AppCompatActivity() {
    // Set what xml file you want to access
    var binding: ActivityBusyScheduleWorkoutBinding? = null
    // One responsible for populating the userList
    var exerciseAdapter: ExerciseAdapter? = null
    // Content of the data
    var exerciseList = ArrayList<ExerciseModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusyScheduleWorkoutBinding.inflate(layoutInflater)
        setContentView(binding!!.root)


        populateList()
        // populates the exercise adapter
        exerciseAdapter = ExerciseAdapter(applicationContext, exerciseList)
        // When using an adapter you can set the layout manager
        // Grid or linear layout pwede gamitin
        //Makes it scrollable if there are many content
        binding!!.rvExercises.layoutManager = LinearLayoutManager(applicationContext,
            LinearLayoutManager.VERTICAL,
            false)

        binding!!.rvExercises.adapter = exerciseAdapter
        binding!!.tvStart.setOnClickListener {
            /*binding!!.etUsername.text = "Hello"*/
            //  Navigates
            // applicationcontext kung na saan ka , profilactivity yung destination mo
            val gotoRunningExerciseActivity = Intent(applicationContext, RunningExerciseActivity::class.java)
            //Passing data to from one page to another or in this case a string
            //Passing objects or big data
            /*var bundle = Bundle()
            bundle.putString("username_extra", binding!!.etUsername.text.toString())*/
            // Better to use putExtra without an s for strings
            startActivity(gotoRunningExerciseActivity)
            // Destroys the originating activity to prevent hackers
            finish()
        }

        binding!!.ivBack.setOnClickListener {
            // Load to Home
            /*val gotoRunningExerciseActivity = Intent(applicationContext, RunningExerciseActivity::class.java)
            startActivity(gotoRunningExerciseActivity)
            finish()*/
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