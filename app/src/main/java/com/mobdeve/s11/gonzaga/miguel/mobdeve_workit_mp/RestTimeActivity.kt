package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityRestTimeBinding
import java.util.*
import java.util.concurrent.TimeUnit

class RestTimeActivity : AppCompatActivity() {

    lateinit var binding: ActivityRestTimeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestTimeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize timer duration
        // Get timer duration from workout exercise
        // 20seconds
        // Convert minute seconds to seconds
        var duration: Long = TimeUnit.SECONDS.toMillis(5)

        // Initialize Timer Countdown
        object : CountDownTimer(duration, 1000) {
            override fun onTick(l: Long) {
                // When tick
                // Convert Milisecond to minute and second
                var sDuration: String = String.format(Locale.ENGLISH, "%02d"
                , l / 1000)

                // Set converted string on text view
                binding.tvRestTime.text = sDuration
            }

            override fun onFinish() {
                // When finish
                val gotoWorkoutDoneActivity = Intent(applicationContext, WorkoutDoneActivity::class.java)
                startActivity(gotoWorkoutDoneActivity)
                finish()
            }

        }.start()
    }
}