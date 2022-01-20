package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ItemRowExerciseBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.ExerciseModel
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.WorkoutModel


// Populates the rows
class ExerciseAdapter(
    private var exerciseList: ArrayList<ExerciseModel?>,
    private val listener: OnItemClickListener

)
    : RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onDeleteClick(position: Int)
        fun onLoadClick(position: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemRowExerciseBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindUser(exerciseList[position]!!)
    }

    override fun getItemCount() = exerciseList.size

    // Change whole content but very heavy
/*    fun addExercises(usersArrayList: ArrayList<ExerciseModel?>?) {
        exerciseList.clear()
        exerciseList.addAll(usersArrayList!!)
        // Updates the UI for changes
        notifyDataSetChanged()
    }*/

    // Add only a single exercise
    fun addExerciseToWorkout(exercise: ExerciseModel?, workout: WorkoutModel) {
        /*Search workout first either by passing it to the parameter or searching for the name of
        the workout*/
        workout.exercises?.add(0, exercise)
        notifyItemInserted(0)
        notifyDataSetChanged()
    }

    fun removeUser(position: Int, workout: WorkoutModel) {
        workout.exercises?.removeAt(position)
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
        fun bindUser(exercise: ExerciseModel) {
            itemBinding.tvExerciseTitle.text = exercise.title
            itemBinding.tvExerciseSetsReps.text = "${exercise.sets} sets X ${exercise.reps} reps"
            itemBinding.ivExercise.setImageResource(exercise.image)
        }

    }



}