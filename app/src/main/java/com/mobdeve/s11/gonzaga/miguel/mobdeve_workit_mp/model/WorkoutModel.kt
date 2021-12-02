package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model

import java.io.Serializable
import java.util.ArrayList

class WorkoutModel (
    var workoutName: String,
    var exercises: ArrayList<ExerciseModel>,
    var numExercises: Int = exercises.size
) : Serializable