package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.dataAccessObjects.WorkoutDAOArrayList
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityRestTimeBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.ExerciseModel
import java.util.*
import java.util.concurrent.TimeUnit

class RestTimeActivity : AppCompatActivity() {

    lateinit var binding: ActivityRestTimeBinding
    lateinit var exercises: ArrayList<ExerciseModel?>
    var exerciseList: ArrayList<ExerciseModel?> = ArrayList()
    var workoutDAO: WorkoutDAOArrayList = WorkoutDAOArrayList()
    var exerciseNumber: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestTimeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val args = intent.getBundleExtra("BUNDLE")
        exercises = args!!.getSerializable("ARRAYLIST") as ArrayList<ExerciseModel?>
        val restTimeExtra = intent.getIntExtra("restTime", 0)
        exerciseNumber = intent.getIntExtra("exerciseNumber", 0)
        val status = intent.getStringExtra("status")
        Toast.makeText(this, exerciseNumber.toString(),
            Toast.LENGTH_LONG).show()
        val restTimeLong = restTimeExtra.toLong()

        // Initialize timer duration
        // Get timer duration from workout exercise
        // 20seconds
        // Convert minute seconds to seconds
        //var duration: Long = TimeUnit.SECONDS.toMillis(4)
        var duration: Long = TimeUnit.SECONDS.toMillis(restTimeLong)

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
                   goBack()
            }

        }.start()
    }

    fun goBack() {
        val gotoRunningExerciseActivity = Intent(applicationContext, RunningExerciseActivity::class.java)
        val args = Bundle()
        val position = intent.getIntExtra("position", 10)
        val workoutName = intent.getStringExtra("workoutName")
        if (workoutName.equals("Burst Workout"))
            exerciseList = workoutDAO.getBurstWorkoutExercises(position)!!
        gotoRunningExerciseActivity.putExtra("workoutName", workoutName)
        gotoRunningExerciseActivity.putExtra("position", position)
        args.putSerializable("ARRAYLIST", exerciseList)
        gotoRunningExerciseActivity.putExtra("BUNDLE", args)
        gotoRunningExerciseActivity.putExtra("exerciseNumber", exerciseNumber+1)
        startActivity(gotoRunningExerciseActivity)
        finish()
    }
}