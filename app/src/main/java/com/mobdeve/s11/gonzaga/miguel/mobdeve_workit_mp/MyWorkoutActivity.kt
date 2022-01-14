package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.adapters.WorkoutAdapter
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.dataAccessObjects.WorkoutDAOArrayList
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityMyWorkoutBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.WorkoutModel

class MyWorkoutActivity : AppCompatActivity(), WorkoutAdapter.OnItemClickListener {
    // Set what xml file you want to access
    var binding: ActivityMyWorkoutBinding? = null
    // One responsible for populating the userList
    var workoutAdapter: WorkoutAdapter? = null
    // Content of the data
    lateinit var workoutList: ArrayList<WorkoutModel?>
    var workoutDAO: WorkoutDAOArrayList = WorkoutDAOArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyWorkoutBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        populateList()
        // populates the user adapter
        workoutAdapter = WorkoutAdapter(workoutList, this)
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

        }

        binding!!.ivBack.setOnClickListener {
            // Load to Home
            val gotoHomeActivity = Intent(applicationContext, HomeActivity::class.java)
            startActivity(gotoHomeActivity)

        }

        Navbar(findViewById<BottomNavigationView>(R.id.bottom_navigation), this, R.id.nav_home)
    }

    fun populateList() {
        workoutList = workoutDAO.getMyWorkouts()!!
    }

    override fun onDeleteClick(position: Int) {
        TODO("Not yet implemented")
    }

    override fun onLoadClick(position: Int) {
        // sned workout info

        var gotoMyWorkoutExercisesActivity = Intent(applicationContext, MyWorkoutExercisesActivity::class.java)
        gotoMyWorkoutExercisesActivity.putExtra("position", position)
        startActivity(gotoMyWorkoutExercisesActivity)
    }

}