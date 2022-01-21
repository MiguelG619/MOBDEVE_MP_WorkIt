package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.adapters.ExerciseAdapter
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.dataAccessObjects.ExerciseDAOArrayList
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.dataAccessObjects.WorkoutDAOArrayList
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityCreateWorkoutBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityNameWorkoutBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.ExerciseModel
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.WorkoutModel

class NameWorkoutActivity : AppCompatActivity(), ExerciseAdapter.OnItemClickListener {
    lateinit var binding: ActivityNameWorkoutBinding
    var exerciseAdapter: ExerciseAdapter? = null
    lateinit var workoutName: String
    var workoutDAO: WorkoutDAOArrayList = WorkoutDAOArrayList()

    lateinit var exerciseList: ArrayList<ExerciseModel?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNameWorkoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Navbar(findViewById<BottomNavigationView>(R.id.bottom_navigation), this, R.id.nav_home)

        populateList()
        exerciseAdapter = ExerciseAdapter(exerciseList, this)
        binding.rvExerciseList.adapter = exerciseAdapter
        binding.rvExerciseList.layoutManager = LinearLayoutManager(applicationContext,
            LinearLayoutManager.VERTICAL,
            false)


        binding.tvDone.setOnClickListener {


            if (binding.etWorkoutName.text.toString().trim().isEmpty()) {
                binding.etWorkoutName.error = "Please enter the workout name you want!."
                binding.etWorkoutName.requestFocus()

            } else {
                workoutName = binding.etWorkoutName.text.toString()
                workoutName.capitalize()

                var createdExerciseList = (this.application as GlobalVariables).tempExerciseList
                var createdWorkout = WorkoutModel(workoutName, createdExerciseList, createdExerciseList.size)


                workoutDAO.addWorkout(createdWorkout)
                // klailangan din ata uipdate yung adapter
                var createdExerciseListSize = createdExerciseList.size
                var i = 0
                while (i < createdExerciseListSize) {
                    createdExerciseList.removeAt(i)
                    i++
                }


                 val gotoMyWorkoutActivity = Intent(applicationContext, MyWorkoutActivity::class.java)
                 startActivity(gotoMyWorkoutActivity)
            }

        }


    }




    private fun populateList() {
        exerciseList = (this.application as GlobalVariables).tempExerciseList
    }

    override fun onDeleteClick(position: Int) {
        TODO("Not yet implemented")
    }

    override fun onLoadClick(position: Int) {
        TODO("Not yet implemented")
    }
}