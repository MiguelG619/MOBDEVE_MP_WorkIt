package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.adapters.ExerciseAdapter
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.dataAccessObjects.ExerciseDAOArrayList
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityCreateWorkoutSelectExercisesBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.ExerciseModel

class CreateWorkoutSelectExercisesActivity : AppCompatActivity(), ExerciseAdapter.OnItemClickListener {
    // Set what xml file you want to access
    var binding: ActivityCreateWorkoutSelectExercisesBinding? = null
    // One responsible for populating the userList
    var exerciseAdapter: ExerciseAdapter? = null
    // Content of the data
    var exerciseList: ArrayList<ExerciseModel?> = ArrayList()
    var exerciseDAO: ExerciseDAOArrayList = ExerciseDAOArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateWorkoutSelectExercisesBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        populateList()
        exerciseAdapter = ExerciseAdapter(exerciseList, this)
        binding!!.rvExerciseList.layoutManager = LinearLayoutManager(applicationContext,
            LinearLayoutManager.VERTICAL,
            false)
        binding!!.rvExerciseList.adapter = exerciseAdapter

        var navbar = Navbar(findViewById<BottomNavigationView>(R.id.bottom_navigation), this, R.id.nav_home)
    }

    fun populateList() {
        exerciseList = exerciseDAO.getExercises()!!
    }

    override fun onDeleteClick(position: Int) {
        TODO("Not yet implemented")
    }

    override fun onLoadClick(position: Int) {
        TODO("Not yet implemented")
    }
}