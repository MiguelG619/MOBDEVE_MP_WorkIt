package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.adapters.ExerciseAdapter
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.adapters.WorkoutAdapter
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.dataAccessObjects.WorkoutDAOArrayList
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityBusyScheduleWorkoutBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.ExerciseModel
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.WorkoutModel


class BusyScheduleWorkoutActivity : AppCompatActivity(), WorkoutAdapter.OnItemClickListener {
    lateinit var binding: ActivityBusyScheduleWorkoutBinding
    var workoutDAO: WorkoutDAOArrayList = WorkoutDAOArrayList()
    var workoutAdapter: WorkoutAdapter? = null
    lateinit var workoutList: ArrayList<WorkoutModel?>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusyScheduleWorkoutBinding.inflate(layoutInflater)

        supportActionBar?.hide()
        setContentView(binding.root)


        // BURST WORKOUT DIN LAGAY DITO OR LAGAY SA IBANGG ACTIVITY?
        populateList()
        workoutAdapter = WorkoutAdapter(workoutList, this)
        binding.rvExercises.adapter = workoutAdapter
        binding.rvExercises.layoutManager = LinearLayoutManager(applicationContext,
            LinearLayoutManager.VERTICAL,
            false)



        binding. ivBack.setOnClickListener {
            // Load to Home
            val gotoHomeActivity = Intent(applicationContext, HomeActivity::class.java)
            startActivity(gotoHomeActivity)

        }

        Navbar(findViewById<BottomNavigationView>(R.id.bottom_navigation), this, R.id.nav_home)


    }

    fun populateList() {
        workoutList = workoutDAO.getBusyWorkouts()!!
    }

    override fun onDeleteClick(position: Int) {
        TODO("Not yet implemented")
    }

    fun populateExerciseData() {

    }

    override fun onLoadClick(position: Int) {
        // Send data first based on the position
        var gotoBusyWorkoutExercisesActivity =
            Intent(applicationContext, BusyWorkoutExercisesActivity::class.java)
        gotoBusyWorkoutExercisesActivity.putExtra("position", position)
        startActivity(gotoBusyWorkoutExercisesActivity)
    }


}