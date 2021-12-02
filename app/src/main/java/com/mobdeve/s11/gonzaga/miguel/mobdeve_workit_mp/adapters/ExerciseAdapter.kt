package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ItemRowExerciseBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.ExerciseModel


// Populates the rows
class ExerciseAdapter(private val context: Context,
                      private var exerciseList: ArrayList<ExerciseModel>
)
    : RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Automatcially uses item_row_user
        val itemBinding = ItemRowExerciseBinding
            .inflate(LayoutInflater. from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }


    // Define the size of the list
    override fun getItemCount() = exerciseList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindExercise(exerciseList[position])
    }


    class ViewHolder(private val itemBinding: ItemRowExerciseBinding)
        :RecyclerView.ViewHolder(itemBinding.root) {

        // Send user data to per row
        fun bindExercise(exercise: ExerciseModel) {
            itemBinding.tvExerciseTitle.text = exercise.title
            itemBinding.tvExerciseSetsReps.text = "${exercise.sets} sets X ${exercise.reps} reps"
        }
    }
}