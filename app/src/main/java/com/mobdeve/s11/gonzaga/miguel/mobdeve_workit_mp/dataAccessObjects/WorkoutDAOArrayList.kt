package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.dataAccessObjects

import android.util.Log
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.ExerciseModel
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.WorkoutModel

class WorkoutDAOArrayList() {
    lateinit var burstWorkout: WorkoutModel
    lateinit var busyWorkout: WorkoutModel
    var myWorkoutList = ArrayList<WorkoutModel?>()



    // One of the first things that will be instantiated when the class is created
    // creates the content of the userList array
    init {
        var burstExerciseList = ArrayList<ExerciseModel?>()
        var busyExerciseList = ArrayList<ExerciseModel?>()
        var myOwnExerciseList = ArrayList<ExerciseModel?>()
        // Burst Workout
            var i = 0
            while (i < 5) {
                burstExerciseList.add(
                    ExerciseModel( "Jumping Jacks" ,
                        "A conditioning exercise performed from a standing position " +
                                "by jumping to a position with legs spread and arms raised and then " +
                                "to the original position.",
                        30, 3, 10)
                )
                i++
            }
            burstWorkout = WorkoutModel("Burst Workout", burstExerciseList, burstExerciseList.size)
       // Busy workout
            var j = 0
            while (j < 5) {
                busyExerciseList.add(
                    ExerciseModel( "Jumping Jacks" ,
                        "A conditioning exercise performed from a standing position " +
                                "by jumping to a position with legs spread and arms raised and then " +
                                "to the original position.",
                        30, 3, 10)
                )
                j++
            }
            busyWorkout = WorkoutModel("Busy Workout", busyExerciseList, busyExerciseList.size)

        // Own workouts
            /*var k = 0
            var myWorkoutNumber = 1
            while (k < myWorkoutNumber) {
                //Call myWorkouts from db

                k++
            }*/
            /*call and create my workout list using for ()
            myWorkout = WorkoutModel("Burst Workout", exerciseList, exerciseList.size)*/

    }

    // Long is for the user ID when we use a database
    fun addWorkout(workout: WorkoutModel?): Long {
        myWorkoutList.add(workout!!)
        return 1L
    }

    fun getMyWorkouts(): ArrayList<WorkoutModel?>? = myWorkoutList

    fun getMyWorkout(workoutId: Int): WorkoutModel? = myWorkoutList[workoutId]

    fun getMyWorkoutExercises(workoutId: Int): ArrayList<ExerciseModel?>? = myWorkoutList[workoutId]?.exercises

    fun getBurstWorkoutExercises() : ArrayList<ExerciseModel?>? = burstWorkout.exercises

    fun getBusyWorkoutExercises() : ArrayList<ExerciseModel?>? = busyWorkout.exercises


    fun updateExercise(workout: WorkoutModel?): Int {
        TODO("Not yet implemented")
    }
    fun removeExercise(workout: WorkoutModel): Int {
        TODO("Not yet implemented")
    }

}