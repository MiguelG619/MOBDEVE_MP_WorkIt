package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model

import java.io.Serializable
import java.util.ArrayList

class WorkoutModel (
    var workout_name: String,
    var exerciseModels: ArrayList<ExerciseModel>
) : Serializable