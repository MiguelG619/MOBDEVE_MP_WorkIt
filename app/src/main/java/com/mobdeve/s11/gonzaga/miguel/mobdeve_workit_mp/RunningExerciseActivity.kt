package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityBusyScheduleWorkoutBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityRunningExerciseBinding

class RunningExerciseActivity : AppCompatActivity() {

    var binding: ActivityRunningExerciseBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRunningExerciseBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.tvDone.setOnClickListener {
            val gotoRestTimeActivity = Intent(applicationContext, RestTimeActivity::class.java)
            startActivity(gotoRestTimeActivity)
            finish()
        }
    }


}