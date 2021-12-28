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
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.dataAccessObjects.WorkoutDAOArrayList
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.ExerciseModel


class BusyScheduleWorkoutActivity : AppCompatActivity(), ExerciseAdapter.OnItemClickListener {
    // var binding: ActivityBusyScheduleWorkoutBinding? = null
    var exerciseAdapter: ExerciseAdapter? = null
    var exerciseList: ArrayList<ExerciseModel?> = ArrayList()
    var workoutDAO: WorkoutDAOArrayList = WorkoutDAOArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*binding = ActivityBusyScheduleWorkoutBinding.inflate(layoutInflater)
        setContentView(binding!!.root)*/
        setContentView(R.layout.activity_busy_schedule_workout)

        val rvExercises = findViewById<RecyclerView>(R.id.rv_exercises)
        val tvStart = findViewById<TextView>(R.id.tv_start)
        val ivBack = findViewById<ImageView>(R.id.iv_back)

        // BURST WORKOUT DIN LAGAY DITO OR LAGAY SA IBANGG ACTIVITY?
        populateList()
        exerciseAdapter = ExerciseAdapter(exerciseList, this)
        rvExercises.adapter = exerciseAdapter
        rvExercises.layoutManager = LinearLayoutManager(applicationContext,
            LinearLayoutManager.VERTICAL,
            false)


        tvStart.setOnClickListener {
            val gotoRunningExerciseActivity = Intent(applicationContext, RunningExerciseActivity::class.java)
            startActivity(gotoRunningExerciseActivity)
            finish()
        }

        ivBack.setOnClickListener {
            // Load to Home
            val gotoHomeActivity = Intent(applicationContext, HomeActivity::class.java)
            startActivity(gotoHomeActivity)
            finish()
        }


        var navbar = Navbar(findViewById<BottomNavigationView>(R.id.bottom_navigation), this, R.id.nav_home)

    }

    fun populateList() {
        exerciseList = workoutDAO.getBusyWorkoutExercises()!!
    }

    override fun onDeleteClick(position: Int) {
        TODO("Not yet implemented")
    }

    override fun onLoadClick(position: Int) {
        TODO("Not yet implemented")
    }
}