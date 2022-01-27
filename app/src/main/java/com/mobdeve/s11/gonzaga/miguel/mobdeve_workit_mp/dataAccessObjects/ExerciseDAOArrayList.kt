package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.dataAccessObjects

import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.R
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.ExerciseModel
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.WorkoutModel

class ExerciseDAOArrayList {
    var allExercisesList = ArrayList<ExerciseModel?>()

    // One of the first things that will be instantiated when the class is created
    // creates the content of the userList array
    init {

        allExercisesList.add(
            ExerciseModel( "Jumping Jacks" ,
                "A conditioning exercise performed from a standing position by jumping to a position with legs spread " +
                        "and arms raised and then to the original position. ",
                R.drawable.jumping_jacks,
                20, 1, 5)
        )
        allExercisesList.add(
            ExerciseModel( "Jogging in place" ,
                "Lift your right arm and left foot at the same time." +
                        "Raise your knee as high as your hips." +
                        "Then switch to the opposite foot, quickly lifting your right foot to hip height. " +
                        "At the same time, move your right arm back and your left arm forward and up.",
                R.drawable.jogging,
                30, 2, 10)
        )
        allExercisesList.add(
            ExerciseModel( "Push-up" ,
                "The push-up builds both upper-body and core strength. " +
                        "Get down on all fours, placing your hands slightly wider than your shoulders. " +
                        "Straighten your arms and legs. " +
                        "Lower your body until your chest nearly touches the floor. " +
                        "Pause, then push yourself back up. " +
                        "Repeat.",
                R.drawable.pushup,
                10, 3, 20)
        )
        allExercisesList.add(
            ExerciseModel( "Squats" ,
                "The squat is a dynamic strength training exercise that requires several muscles in your upper and lower body to work together simultaneously. Stand with the feet shoulder-width apart and the toes pointing slightly outward. Arms should be straight out in front. " +
                        "Bend the knees to push the hips backward, keeping the back straight and the torso upright. Once the knees reach a 90-degree angle, push back up through the feet.",
                R.drawable.squats,
                15, 3, 15)
        )
        allExercisesList.add(
            ExerciseModel( "Crunches" ,
                "It specifically trains your abdominal muscles, which are part of your core." +
                        "Lie down on your back. Bend your knees and place your arms across your chest. Contract your abs and inhale. " +
                        "Exhale and lift your upper body, keeping your head and neck relaxed. " +
                        "Inhale and return to the starting position. ",
                R.drawable.crunches,
                20, 3, 10)
        )
        allExercisesList.add(
            ExerciseModel( "Triceps Dips" ,
                "The triceps dip exercise is a great bodyweight exercise that builds arm and shoulder strength. " +
                        "Sit on the edge of a stable chair, weight bench, or step and grip the edge next to your hips. " +
                        "Press into your palms to lift your body. " +
                        "Lower yourself until your elbows are bent between 45 and 90 degrees. " +
                        "Push yourself back up slowly until your arms are almost straight and repeat. ",
                R.drawable.triceps_dips, 150, 2, 3)
        )
        allExercisesList.add(
            ExerciseModel( "High Knees" ,
                "Stand tall with your feet about hip-to-shoulder-width apart and your arms at your sides. " +
                        "Begin by bringing your right knee toward your chest, slightly above waist level. Simultaneously, move your left hand up in a pumping motion. " +
                        "Quickly lower your right leg and left hand. " +
                        "Repeat with your left leg and right hand. ",
                R.drawable.high_knees,
                40, 2, 3)
        )


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

    fun getExercises(): ArrayList<ExerciseModel?> = allExercisesList

    fun getExercise(exerciseId: Int): ExerciseModel? = allExercisesList[exerciseId]

    fun updateExercise(exercise: ExerciseModel?): Int {
        TODO("Not yet implemented")
    }


}



