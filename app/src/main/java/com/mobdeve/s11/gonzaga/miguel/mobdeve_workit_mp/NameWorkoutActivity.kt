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
    lateinit var exerciseAdapter: ExerciseAdapter
    lateinit var workoutName: String
    var workoutDAO: WorkoutDAOArrayList = WorkoutDAOArrayList()

    lateinit var exerciseList: ArrayList<ExerciseModel?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNameWorkoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

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
                Log.d("sssssssssssssss", "createdWorkout: " + createdWorkout.workoutName)

                var myWorkouts = (this.application as GlobalVariables).myWorkoutList
                myWorkouts.add(createdWorkout)

               // Log.d("fsdsdf", "createdwokrout "+ myWorkouts[1]!!.workoutName)


                //var createdExerciseListSize = createdExerciseList.size

                 val gotoMyWorkoutActivity = Intent(applicationContext, MyWorkoutActivity::class.java)
                //send index to update in adapter
                /*var workoutDAOSizeIndex = workoutDAO.myWorkoutList.size - 1
                gotoMyWorkoutActivity.putExtra("index", workoutDAOSizeIndex)
                gotoMyWorkoutActivity.putExtra("createdWorkout", createdWorkout)*/
                startActivity(gotoMyWorkoutActivity)
            }

        }


    }




    private fun populateList() {
        exerciseList = (this.application as GlobalVariables).tempExerciseList
    }

    override fun onDeleteClick(position: Int) {
        exerciseAdapter.removeExercise(position)
        exerciseList.removeAt(position)
        Log.d("qqqqqqq", "onDeleteClick: "+ exerciseList[position]?.title)
    }

    override fun onLoadClick(positionExercise: Int) {
        var gotoViewExerciseActivity = Intent(applicationContext, ViewExerciseActivity::class.java)
        gotoViewExerciseActivity.putExtra("title", exerciseList[positionExercise]?.title)
        gotoViewExerciseActivity.putExtra("description", exerciseList[positionExercise]?.description)
        gotoViewExerciseActivity.putExtra("image", exerciseList[positionExercise]?.image)
        gotoViewExerciseActivity.putExtra("reps", exerciseList[positionExercise]?.reps)
        gotoViewExerciseActivity.putExtra("sets", exerciseList[positionExercise]?.sets)
        gotoViewExerciseActivity.putExtra("rest", exerciseList[positionExercise]?.restTime)
        startActivity(gotoViewExerciseActivity)
    }
}