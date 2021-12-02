package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityAddExerciseToWorkoutBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityBusyScheduleWorkoutBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityCreateWorkoutBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityRunningExerciseBinding

class AddExerciseToWorkoutActivity : AppCompatActivity() {


    var binding: ActivityAddExerciseToWorkoutBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddExerciseToWorkoutBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.tvAdd.setOnClickListener {
            val gotoCreateWorkoutActivity =
                Intent(applicationContext, CreateWorkoutActivity::class.java)
            startActivity(gotoCreateWorkoutActivity)
            finish()
        }
    }
}
