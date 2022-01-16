package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model
import java.io.Serializable

class ExerciseModel (var title: String? = null,
                     var description: String? = null,
                     var image: Int = 0,
                     var reps: Int = 0,
                     var sets: Int = 0,
                     var restTime: Int = 0
) : Serializable {

}
