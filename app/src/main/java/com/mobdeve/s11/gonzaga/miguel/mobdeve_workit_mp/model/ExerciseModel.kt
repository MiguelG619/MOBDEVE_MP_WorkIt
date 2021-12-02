package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model
import java.io.Serializable

class ExerciseModel (var workoutName: String? = null,
                     var title: String? = null,
                     var description: String? = null,
                     var reps: Int = 0,
                     var sets: Int = 0,
                     var resTime: Int = 0
) : Serializable {

}
