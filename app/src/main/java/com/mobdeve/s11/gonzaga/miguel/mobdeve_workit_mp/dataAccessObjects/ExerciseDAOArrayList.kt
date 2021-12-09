package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.dataAccessObjects

import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.ExerciseModel
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.WorkoutModel

class ExerciseDAOArrayList {
    var allExercisesList = ArrayList<ExerciseModel?>()

    // One of the first things that will be instantiated when the class is created
    // creates the content of the userList array
    init {
        var i = 0
        while (i < 5) {
            allExercisesList.add(
                ExerciseModel( "Jumping Jacks" ,
                    "A conditioning exercise performed from a standing position " +
                            "by jumping to a position with legs spread and arms raised and then " +
                            "to the original position.",
                    30, 3, 10)
            )
            i++
        }

    }

    fun addExerciseToWorkout(exercise: ExerciseModel?, workout: WorkoutModel) : Long {
        /*Search workout first either by passing it to the parameter or searching for the name of
        the workout*/
        workout.exercises?.add(exercise!!)
        return 1L
    }

    fun removeExerciseFromWorkout(exerciseId: Int, workout: WorkoutModel): Int {
        workout.exercises?.removeAt(exerciseId)
        return 1
    }

    // Long is for the user ID when we use a database
     fun createExercise(exercise: ExerciseModel?): Long {
        allExercisesList.add(exercise!!)
        return 1L
    }

     fun getExercises(): ArrayList<ExerciseModel?>? = allExercisesList

     fun getExercise(exerciseId: Int): ExerciseModel? = allExercisesList[exerciseId]

     fun updateExercise(exercise: ExerciseModel?): Int {
        TODO("Not yet implemented")
    }


}