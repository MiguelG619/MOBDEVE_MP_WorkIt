package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
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
    lateinit var status: String
    var position: Int = 0
    lateinit var workoutName: String
    var doneSets: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestTimeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val args = intent.getBundleExtra("BUNDLE")
        exercises = args!!.getSerializable("ARRAYLIST") as ArrayList<ExerciseModel?>
        val restTimeExtra = intent.getIntExtra("restTime", 0)
        exerciseNumber = intent.getIntExtra("exerciseNumber", 0)
        doneSets = intent.getIntExtra("doneSets", 1) + 1
        position = intent.getIntExtra("position", 10)
        setWorkout()


        val restTimeLong = restTimeExtra.toLong()

        // Initialize timer duration
        // Get timer duration from workout exercise
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

    fun setWorkout() {
        workoutName = intent.getStringExtra("workoutName").toString()
        if (workoutName.equals("Burst Workout"))
            exerciseList = workoutDAO.getBurstWorkoutExercises(position)!!
        else if (workoutName.equals("Busy Workout"))
            exerciseList = workoutDAO.getBusyWorkoutExercises(position)!!
        else if (workoutName.equals("My Workout")) {
            var myWorkoutList = (this.application as GlobalVariables).myWorkoutList
            exerciseList = myWorkoutList[position]!!.exercises!!
        }

    }

    fun isDoneWorkout(): Boolean {
        status = intent.getStringExtra("status").toString()
        if (status.equals("Done"))
            return true
        return false
    }

    fun isDoneSet(): Boolean {
        var sets = intent.getIntExtra("sets", 1)
        if (doneSets == sets) {

            return true
        } else if (doneSets == sets && exerciseNumber+1 != exercises.size) {

        }

        return false
    }

    fun goBack() {
        if (isDoneWorkout() && isDoneSet()) {
            val gotoDoneActivity = Intent(applicationContext, WorkoutDoneActivity::class.java)
            gotoDoneActivity.putExtra("workoutName", workoutName)
            gotoDoneActivity.putExtra("day", position+1)
            gotoDoneActivity.putExtra("exerciseNumber", exerciseList.size)
            startActivity(gotoDoneActivity)
            finish()
        } else {
            val gotoRunningExerciseActivity = Intent(applicationContext, RunningExerciseActivity::class.java)


            gotoRunningExerciseActivity.putExtra("workoutName", workoutName)
            gotoRunningExerciseActivity.putExtra("position", position)
            gotoRunningExerciseActivity.putExtra("status", status)

            val args = Bundle()
            args.putSerializable("ARRAYLIST", exerciseList)
            gotoRunningExerciseActivity.putExtra("BUNDLE", args)
            if (isDoneSet()) {
                gotoRunningExerciseActivity.putExtra("exerciseNumber", exerciseNumber+1)
                gotoRunningExerciseActivity.putExtra("doneSets", 0)
            } else {
                gotoRunningExerciseActivity.putExtra("exerciseNumber", exerciseNumber)
                gotoRunningExerciseActivity.putExtra("doneSets", doneSets)
            }

            startActivity(gotoRunningExerciseActivity)
            finish()
        }
    }
}