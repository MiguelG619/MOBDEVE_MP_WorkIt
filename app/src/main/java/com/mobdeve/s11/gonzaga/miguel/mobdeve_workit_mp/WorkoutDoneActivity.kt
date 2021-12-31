package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityWorkoutDoneBinding

class WorkoutDoneActivity : AppCompatActivity() {

    lateinit var binding: ActivityWorkoutDoneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkoutDoneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvDone.setOnClickListener {
            var gotoHomeActivity = Intent(applicationContext, HomeActivity::class.java)
            startActivity(gotoHomeActivity)
            finish()
        }
    }
}