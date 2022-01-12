package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ItemRowExerciseBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.WorkoutModel


// Populates the rows
class WorkoutAdapter(
    private var myWorkoutList: ArrayList<WorkoutModel?>,
    private val listener: OnItemClickListener

)
    : RecyclerView.Adapter<WorkoutAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onDeleteClick(position: Int)
        fun onLoadClick(position: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Automatically uses item_row_user
        val itemBinding = ItemRowExerciseBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindUser(myWorkoutList[position]!!)

    }

    // Define the size of the list
    override fun getItemCount() = myWorkoutList.size

    // Change whole content but very heavy
/*    fun addWorkout(usersArrayList: ArrayList<ExerciseModel?>?) {
        exerciseList.clear()
        exerciseList.addAll(usersArrayList!!)
        // Updates the UI for changes
        notifyDataSetChanged()
    }*/

    // Add only a single exercise
    fun createWorkout(workout: WorkoutModel) {
        /*Search workout first either by passing it to the parameter or searching for the name of
        the workout*/
        myWorkoutList.add(0, workout)
        notifyItemInserted(0)
        notifyDataSetChanged()
    }

    fun removeUser(position: Int) {
        myWorkoutList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount)
    }


    inner class ViewHolder(private val itemBinding: ItemRowExerciseBinding)
        : RecyclerView.ViewHolder(itemBinding.root) {

        init {
            /*itemBinding.btnButtonDelete.setOnClickListener(View.OnClickListener() {
                if (listener != null) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onDeleteClick(position)
                    }
                }
            })*/
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onLoadClick(position)
                }
            }

        }


        // Send user data to per row
        fun bindUser(workout: WorkoutModel) {
            itemBinding.tvExerciseTitle.text = workout.workoutName
            itemBinding.tvExerciseSetsReps.text = "${workout.numExercises} exercises"
        }

    }



}