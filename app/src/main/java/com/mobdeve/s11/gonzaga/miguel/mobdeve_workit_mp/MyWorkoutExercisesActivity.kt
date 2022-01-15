package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.adapters.ExerciseAdapter
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.dataAccessObjects.WorkoutDAOArrayList
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityBusyScheduleWorkoutBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityMyWorkoutExercisesBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.ExerciseModel


class MyWorkoutExercisesActivity : AppCompatActivity(), ExerciseAdapter.OnItemClickListener {
    lateinit var binding: ActivityMyWorkoutExercisesBinding
    var exerciseAdapter: ExerciseAdapter? = null
    var exerciseList: ArrayList<ExerciseModel?> = ArrayList()
    var workoutDAO: WorkoutDAOArrayList = WorkoutDAOArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyWorkoutExercisesBinding.inflate(layoutInflater)
        setContentView(binding.root)



        // BURST WORKOUT DIN LAGAY DITO OR LAGAY SA IBANGG ACTIVITY?
        populateList()
        exerciseAdapter = ExerciseAdapter(exerciseList, this)
        binding.rvExercises.adapter = exerciseAdapter
        binding.rvExercises.layoutManager = LinearLayoutManager(applicationContext,
            LinearLayoutManager.VERTICAL,
            false)


        binding.tvStart.setOnClickListener {
            val gotoRunningExerciseActivity = Intent(applicationContext, RunningExerciseActivity::class.java)
            startActivity(gotoRunningExerciseActivity)
        }

        binding. ivBack.setOnClickListener {
            // Load to Home
            val gotoHomeActivity = Intent(applicationContext, HomeActivity::class.java)
            startActivity(gotoHomeActivity)
        }


        Navbar(findViewById<BottomNavigationView>(R.id.bottom_navigation), this, R.id.nav_home)


    }

    fun populateList() {
        // get adapter position
        val position = intent.getIntExtra("position", 10)
        Toast.makeText(this, position.toString(),
            Toast.LENGTH_LONG).show()
        exerciseList = workoutDAO.getMyWorkoutExercises(position)!!
        updateText(position)

    }

    fun updateText(position: Int) {
        val workoutName = workoutDAO.myWorkoutList[position]!!.workoutName
        binding.tvWorkoutTitle.text = workoutName
        val numExercises = workoutDAO.myWorkoutList[position]!!.numExercises
        if (numExercises <= 1) {
            binding.tvExerciseCount.text = "${numExercises} Exercise"
        } else {
            binding.tvExerciseCount.text = "${numExercises} Exercises"
        }

    }


    override fun onDeleteClick(position: Int) {
        TODO("Not yet implemented")
    }

    fun populateExerciseData() {

    }

    override fun onLoadClick(position: Int) {
        // Send data first based on the position
        var gotoViewExerciseActivity = Intent(applicationContext, ViewExerciseActivity::class.java)
        startActivity(gotoViewExerciseActivity)

    }


}