package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityBurstWorkoutExercisesBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityViewExerciseBinding

class ViewExerciseActivity : AppCompatActivity() {

    lateinit var binding: ActivityViewExerciseBinding
    lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Navbar(findViewById<BottomNavigationView>(R.id.bottom_navigation), this, R.id.nav_home)


        name = intent.getStringExtra("title").toString()
        val description = intent.getStringExtra("description")
        val image = intent.getIntExtra("image", 0)
        val reps = intent.getIntExtra("reps", 0)
        val sets = intent.getIntExtra("sets", 0)
        val rest = intent.getIntExtra("rest", 0)

        binding.tvExerciseTitle.text = name
        binding.tvExerciseDes.text = description
        binding.imageExercise.setImageResource(image)
        binding.tvReps.text = "${reps.toString()} Reps"
        binding.tvSets.text = "${sets.toString()} Sets"
        binding.tvTime.text = "${rest.toString()} Seconds"

    }
}