package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.adapters.ExerciseAdapter
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.dataAccessObjects.ExerciseDAOArrayList
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityCreateWorkoutBinding

import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.ExerciseModel

class CreateWorkoutActivity : AppCompatActivity(), ExerciseAdapter.OnItemClickListener {
    // Set what xml file you want to access
    var binding: ActivityCreateWorkoutBinding? = null

    // One responsible for populating the userList
    var exerciseAdapter: ExerciseAdapter? = null

    // Content of the data
    lateinit var exerciseList: ArrayList<ExerciseModel?>
    var exerciseDAO: ExerciseDAOArrayList = ExerciseDAOArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateWorkoutBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        Navbar(findViewById<BottomNavigationView>(R.id.bottom_navigation), this, R.id.nav_home)

        populateList()
        exerciseAdapter = ExerciseAdapter(exerciseList, this)
        binding!!.rvExerciseList.adapter = exerciseAdapter
        binding!!.rvExerciseList.layoutManager = LinearLayoutManager(applicationContext,
            LinearLayoutManager.VERTICAL,
            false)

        binding!!.tvAddExercise.setOnClickListener {


        }

        binding!!.tvDone.setOnClickListener {
            // Add popup
        }

    }
    fun populateList() {
        exerciseList = exerciseDAO.getExercises()!!

    }

    override fun onDeleteClick(position: Int) {
        TODO("Not yet implemented")
    }

    override fun onLoadClick(position: Int) {
        // Send data first based on the position
        var gotoViewExerciseActivity = Intent(applicationContext, ViewExerciseActivity::class.java)
        startActivity(gotoViewExerciseActivity)
    }
}
