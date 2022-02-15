package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.adapters.WorkoutAdapter
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.dataAccessObjects.WorkoutDAOArrayList
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityMyWorkoutBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.ExerciseModel
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.WorkoutModel
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.utils.SharedPrefUtility

class MyWorkoutActivity : AppCompatActivity(), WorkoutAdapter.OnItemClickListener {
    // Set what xml file you want to access
    var binding: ActivityMyWorkoutBinding? = null
    // One responsible for populating the userList
    var workoutAdapter: WorkoutAdapter? = null
    // Content of the data
    lateinit var workoutList: ArrayList<WorkoutModel?>
    lateinit var sharedPrefUtility: SharedPrefUtility
    lateinit var WORKOUT: String
    var myWorkouts: ArrayList<WorkoutModel?> = ArrayList()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyWorkoutBinding.inflate(layoutInflater)

        supportActionBar?.hide()

        setContentView(binding!!.root)

        initPrefs()
        populateList()
        // populates the user adapter
        workoutAdapter = WorkoutAdapter(workoutList, this)

        // When using an adapter you can set the layout manager
        // Grid or linear layout pwede gamitin
        //Makes it scrollable if there are many content
        binding!!.rvWorkoutList.layoutManager = LinearLayoutManager(applicationContext,
            LinearLayoutManager.VERTICAL,
            false)

        R.layout.activity_splash
        // Send updated workoutlist from namewokrout
        /*if (intent.hasExtra("index")) {
            index = intent.getIntExtra("index", 0)
            *//*Log.d("zzzzzzzzzzz", "workoutdao: " + index)*//*
            Log.d("zzzzzzzzzzz", "workoutdao: " + workoutDAO.myWorkoutList[1]?.workoutName)

            // make workoutDAO Global? kasi abvak isang beses lang pwede mag add ng workout?

            workoutAdapter!!.notifyItemInserted(index)
            workoutAdapter!!.notifyDataSetChanged()
        }*/


        binding!!.rvWorkoutList.adapter = workoutAdapter

        binding!!.tvWorkoutNumber.text = "${workoutList.size} Workouts"

        binding!!.tvCreate.setOnClickListener {
            val gotoCreateWorkoutActivity = Intent(applicationContext, CreateWorkoutActivity::class.java)
            startActivity(gotoCreateWorkoutActivity)
        }

        binding!!.ivBack.setOnClickListener {
            // Load to Home
            val gotoHomeActivity = Intent(applicationContext, HomeActivity::class.java)
            startActivity(gotoHomeActivity)

        }

        Navbar(findViewById(R.id.bottom_navigation), this, R.id.nav_home)
    }

    fun populateList() {
        workoutList = (this.application as GlobalVariables).myWorkoutList
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

    fun initPrefs() {
        sharedPrefUtility = SharedPrefUtility(this)
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    fun loadData() {
        WORKOUT = (this.application as GlobalVariables).id + "myWorkouts"
        (this.application as GlobalVariables).myWorkoutList = sharedPrefUtility.getMyWorkouts(WORKOUT)
        myWorkouts = (this.application as GlobalVariables).myWorkoutList
    }


    //save and load based on the user's id
}