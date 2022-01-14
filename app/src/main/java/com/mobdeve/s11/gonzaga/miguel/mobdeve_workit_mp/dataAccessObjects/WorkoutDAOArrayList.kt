package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.dataAccessObjects

import android.util.Log
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.ExerciseModel
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.WorkoutModel

class WorkoutDAOArrayList() {
    var burstWorkout = ArrayList<WorkoutModel?>()
    var busyWorkout = ArrayList<WorkoutModel?>()
    var myWorkoutList = ArrayList<WorkoutModel?>()



    // One of the first things that will be instantiated when the class is created
    // creates the content of the userList array
    init {
        var burstExerciseList = ArrayList<ExerciseModel?>()
        var busyExerciseList = ArrayList<ExerciseModel?>()
        var myOwnExerciseList1 = ArrayList<ExerciseModel?>()
        var myOwnExerciseList2 = ArrayList<ExerciseModel?>()
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
        var j = 0
            while (j < 7) {
                burstWorkout.add(WorkoutModel("Day ${j+1}", burstExerciseList, burstExerciseList.size))
                j++
            }

       // Busy workout
        j = 0
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
            j = 0
            while (j < 7) {
                busyWorkout.add(WorkoutModel("Day ${j+1}", busyExerciseList, busyExerciseList.size))
                j++
            }

        // My workout
        var k = 0
        while (k < 5) {
            myOwnExerciseList1.add(
                ExerciseModel( "Jumping Jacks" ,
                    "A conditioning exercise performed from a standing position " +
                            "by jumping to a position with legs spread and arms raised and then " +
                            "to the original position.",
                    30, 3, 10)
            )
            k++
        }
        myWorkoutList.add(WorkoutModel("My Workout 1", myOwnExerciseList1, myOwnExerciseList1.size))

        var l = 0
        while (l < 5) {
            myOwnExerciseList2.add(
                ExerciseModel( "Jumping Jacks" ,
                    "A conditioning exercise performed from a standing position " +
                            "by jumping to a position with legs spread and arms raised and then " +
                            "to the original position.",
                    30, 3, 10)
            )
            l++
        }
        myWorkoutList.add(WorkoutModel("My Workout 2", myOwnExerciseList2, myOwnExerciseList2.size))


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

    fun getBurstWorkouts() : ArrayList<WorkoutModel?>? = burstWorkout

    fun getBurstWorkout(workoutId: Int): WorkoutModel? = burstWorkout[workoutId]

    fun getBurstWorkoutExercises(workoutId: Int): ArrayList<ExerciseModel?>? = burstWorkout[workoutId]?.exercises

    fun getBusyWorkouts() : ArrayList<WorkoutModel?>? = busyWorkout

    fun getBusyWorkout(workoutId: Int): WorkoutModel? = busyWorkout[workoutId]

    fun getBusyWorkoutExercises(workoutId: Int): ArrayList<ExerciseModel?>? = busyWorkout[workoutId]?.exercises


    fun updateExercise(workout: WorkoutModel?): Int {
        TODO("Not yet implemented")
    }
    fun removeExercise(workout: WorkoutModel): Int {
        TODO("Not yet implemented")
    }

}