package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import android.app.Application
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.ExerciseModel

class GlobalVariables: Application() {
    var streak: Int = 0
    var tempExerciseList = ArrayList<ExerciseModel?>()
}