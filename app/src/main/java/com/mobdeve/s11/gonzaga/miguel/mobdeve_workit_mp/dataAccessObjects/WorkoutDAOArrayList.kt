package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.dataAccessObjects

import android.util.Log
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.R
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

                burstExerciseList.add(
                    ExerciseModel( "Jumping Jacks" ,
                        "A conditioning exercise performed from a standing position " +
                                "by jumping to a position with legs spread and arms raised and then " +
                                "to the original position.",
                        R.drawable.jumping_jacks,
                        20, 1, 5)
                )
                burstExerciseList.add(
                    ExerciseModel( "Jogging in place" ,
                        "Lift your right arm and left foot at the same time." +
                                "Raise your knee as high as your hips." +
                                "Then switch to the opposite foot, quickly lifting your right foot to hip height." +
                                "At the same time, move your right arm back and your left arm forward and up." +
                                "Continue these movements.",
                        R.drawable.jogging,
                        30, 2, 10)
                )
        burstExerciseList.add(
            ExerciseModel( "Push-up" ,
                "The push-up builds both upper-body and core strength." +
                        "Get down on all fours, placing your hands slightly wider than your shoulders." +
                        "Straighten your arms and legs." +
                        "Lower your body until your chest nearly touches the floor." +
                        "Pause, then push yourself back up." +
                        "Repeat.",
                R.drawable.pushup,
                10, 3, 20)
        )
        burstExerciseList.add(
            ExerciseModel( "Squats" ,
                "The squat is a dynamic strength training exercise that requires several muscles in your upper and lower body to work together simultaneously. Stand with the feet shoulder-width apart and the toes pointing slightly outward. The arms should be straight out in front." +
                        "Bend the knees to push the hips backward, keeping the back straight and the torso upright. Once the knees reach a 90-degree angle or lower, push back up through the feet to straighten the legs.",
                R.drawable.squats,
                15, 3, 15)
        )
        burstExerciseList.add(
            ExerciseModel( "Crunches" ,
                "It specifically trains your abdominal muscles, which are part of your core." +
                        "Lie down on your back. Bend your knees and place your arms across your chest. Contract your abs and inhale." +
                        "Exhale and lift your upper body, keeping your head and neck relaxed." +
                        "Inhale and return to the starting position.",
                R.drawable.crunches,
                20, 3, 10)
        )
        burstExerciseList.add(
            ExerciseModel( "Triceps Dips" ,
                "The triceps dip exercise is a great bodyweight exercise that builds arm and shoulder strength." +
                        "Sit on the edge of a stable chair, weight bench, or step and grip the edge next to your hips." +
                        "Press into your palms to lift your body." +
                        "Lower yourself until your elbows are bent between 45 and 90 degrees." +
                        "Push yourself back up slowly until your arms are almost straight and repeat.",
                R.drawable.triceps_dips,
                40, 2, 3)
        )
        burstExerciseList.add(
            ExerciseModel( "High Knees" ,
                "Stand tall with your feet about hip-to-shoulder-width apart and your arms at your sides." +
                        "Begin by bringing your right knee toward your chest, slightly above waist level. Simultaneously, move your left hand up in a pumping motion." +
                        "Quickly lower your right leg and left hand." +
                        "Repeat with your left leg and right hand." +
                        "Alternate your right and left leg for the desired time.",
                R.drawable.high_knees,
                40, 2, 3)
        )


        var j = 0
            while (j < 7) {
                burstWorkout.add(WorkoutModel("Workout Number ${j+1}", burstExerciseList, burstExerciseList.size))
                j++
            }
        j = 0
        while (j < 5) {
            busyWorkout.add(WorkoutModel("Workout Number ${j+1}", burstExerciseList, burstExerciseList.size))
            j++
        }


                /*busyExerciseList.add(
                    ExerciseModel( "Jumping Jacks" ,
                        "A conditioning exercise performed from a standing position " +
                                "by jumping to a position with legs spread and arms raised and then " +
                                "to the original position.",
                        R.drawable.jumping_jacks,
                        30, 2, 5)
                )

                busyExerciseList.add(
                    ExerciseModel( "Jogging in place" ,
                        "Lift your right arm and left foot at the same time." +
                                "Raise your knee as high as your hips." +
                                "Then switch to the opposite foot, quickly lifting your right foot to hip height." +
                                "At the same time, move your right arm back and your left arm forward and up." +
                                "Continue these movements.",
                        R.drawable.jogging,
                        30, 2, 3)
                )

            j = 0
            while (j < 5) {
                busyWorkout.add(WorkoutModel("Workout Number ${j+1}", busyExerciseList, busyExerciseList.size))
                j++
            }*/

        // My workout

            myOwnExerciseList1.add(
                ExerciseModel( "Jumping Jacks" ,
                    "A conditioning exercise performed from a standing position " +
                            "by jumping to a position with legs spread and arms raised and then " +
                            "to the original position.",
                    R.drawable.jumping_jacks,
                    30, 2, 5)
            )
        myOwnExerciseList1.add(
            ExerciseModel( "Jogging in place" ,
                "Lift your right arm and left foot at the same time." +
                        "Raise your knee as high as your hips." +
                        "Then switch to the opposite foot, quickly lifting your right foot to hip height." +
                        "At the same time, move your right arm back and your left arm forward and up." +
                        "Continue these movements.",
                R.drawable.jogging,
                30, 2, 3)
        )

        myWorkoutList.add(WorkoutModel("My Workout 1", myOwnExerciseList1, myOwnExerciseList1.size))

        /*var l = 0
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
        myWorkoutList.add(WorkoutModel("My Workout 2", myOwnExerciseList2, myOwnExerciseList2.size))*/


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