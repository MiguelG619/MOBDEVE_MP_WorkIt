package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import android.app.Application
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.ExerciseModel
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.WorkoutModel
import java.util.*
import kotlin.collections.ArrayList

class GlobalVariables: Application() {
    var streak: Int = 0
    var tempExerciseList = ArrayList<ExerciseModel?>()
    var myWorkoutList = ArrayList<WorkoutModel?>()
    var reminderMinute: Int = 0
    var reminderHour: Int = 0
}