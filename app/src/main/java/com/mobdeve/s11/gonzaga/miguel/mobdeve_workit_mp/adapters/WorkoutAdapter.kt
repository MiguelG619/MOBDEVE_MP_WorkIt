package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.MyWorkoutActivity
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ItemRowExerciseBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.WorkoutModel


// Populates the rows
class WorkoutAdapter(private val context: Context,
                     private var workoutList: ArrayList<WorkoutModel>
)
    : RecyclerView.Adapter<WorkoutAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Automatically uses item_row_user
        val itemBinding = ItemRowExerciseBinding
            .inflate(LayoutInflater. from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }


    // Define the size of the list
    override fun getItemCount() = workoutList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindExercise(workoutList[position])
        /*holder.itemView.setOnClickListener {
                val gotoMyWorkoutActivity = Intent(applicationContext, MyWorkoutActivity::class.java)
                startActivity(gotoMyWorkoutActivity)
                // Destroys the originating activity to prevent hackers
                finish()*/
    }




class ViewHolder(private val itemBinding: ItemRowExerciseBinding)
    :RecyclerView.ViewHolder(itemBinding.root) {



    // Send user data to per row
    fun bindExercise(workout: WorkoutModel) {
        itemBinding.tvExerciseTitle.text = workout.workoutName
        itemBinding.tvExerciseSetsReps.text = workout.numExercises.toString()
    }


}
}
