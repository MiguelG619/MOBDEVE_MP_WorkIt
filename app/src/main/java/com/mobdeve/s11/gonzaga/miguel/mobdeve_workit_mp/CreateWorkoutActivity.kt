package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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

        supportActionBar?.hide()

        setContentView(binding!!.root)

        supportActionBar!!.hide()

        Navbar(findViewById<BottomNavigationView>(R.id.bottom_navigation), this, R.id.nav_home)

        populateList()
        exerciseAdapter = ExerciseAdapter(exerciseList, this)
        binding!!.rvExerciseList.adapter = exerciseAdapter
        binding!!.rvExerciseList.layoutManager = LinearLayoutManager(applicationContext,
            LinearLayoutManager.VERTICAL,
            false)


        binding!!.tvDone.setOnClickListener {
            var tempExerciseList =  (this.application as GlobalVariables).tempExerciseList
            if (tempExerciseList.isEmpty())
              Toast.makeText(this, "Add exercises first!", Toast.LENGTH_SHORT).show()
            else {
                var gotoNameWorkoutActivity = Intent(applicationContext, NameWorkoutActivity::class.java)
                startActivity(gotoNameWorkoutActivity)
                //SAve it workoutdao array

            }


        }

    }


    fun populateList() {
        exerciseList = exerciseDAO.allExercisesList

    }

    override fun onDeleteClick(position: Int) {
        TODO("Not yet implemented")
    }

    override fun onLoadClick(position: Int) {
        // Send data first based on the position
        //Save na lang agad

        var gotoAddExerciseToWorkoutActivityercise = Intent(applicationContext, AddExerciseToWorkoutActivity::class.java)
        gotoAddExerciseToWorkoutActivityercise.putExtra("exerciseName", exerciseList[position]!!.title)
        gotoAddExerciseToWorkoutActivityercise.putExtra("exerciseDescription", exerciseList[position]!!.description)
        gotoAddExerciseToWorkoutActivityercise.putExtra("exerciseImage", exerciseList[position]!!.image)
        gotoAddExerciseToWorkoutActivityercise.putExtra("exerciseReps", exerciseList[position]!!.reps)
        gotoAddExerciseToWorkoutActivityercise.putExtra("exerciseSets", exerciseList[position]!!.sets)
        gotoAddExerciseToWorkoutActivityercise.putExtra("exerciseRest", exerciseList[position]!!.restTime)

        startActivity(gotoAddExerciseToWorkoutActivityercise)
    }
}
