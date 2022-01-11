package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.adapters.ExerciseAdapter
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.dataAccessObjects.WorkoutDAOArrayList
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityMyWorkoutExercisesBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.ExerciseModel

class MyWorkoutExercisesActivity : AppCompatActivity(), ExerciseAdapter.OnItemClickListener {
    // Set what xml file you want to access
    var binding: ActivityMyWorkoutExercisesBinding? = null
    // One responsible for populating the userList
    var exerciseAdapter: ExerciseAdapter? = null
    // Content of the data
    var exerciseList: ArrayList<ExerciseModel?> = ArrayList()


    var workoutDAO: WorkoutDAOArrayList = WorkoutDAOArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyWorkoutExercisesBinding.inflate(layoutInflater)
        setContentView(binding!!.root)


        populateList()
        // populates the exercise adapter
        exerciseAdapter = ExerciseAdapter(exerciseList, this)
        // When using an adapter you can set the layout manager
        // Grid or linear layout pwede gamitin
        //Makes it scrollable if there are many content
        binding!!.rvWorkoutList.layoutManager = LinearLayoutManager(applicationContext,
            LinearLayoutManager.VERTICAL,
            false)

        binding!!.rvWorkoutList.adapter = exerciseAdapter
        Navbar(findViewById<BottomNavigationView>(R.id.bottom_navigation), this, R.id.nav_home)
    }

    fun populateList() {
        exerciseList = workoutDAO.getMyWorkoutExercises(0)!!
    }

    override fun onLoadClick(position: Int) {
        TODO("Not yet implemented")
    }

    override fun onDeleteClick(position: Int) {
        TODO("Not yet implemented")
    }

}