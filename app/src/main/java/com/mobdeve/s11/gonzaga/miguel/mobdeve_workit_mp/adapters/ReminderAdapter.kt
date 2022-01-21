package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.adapters/*


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReminderAdapter(
    //private val listItems: ArrayList<Reminder>,
    private val listener: NoteListener
) : RecyclerView.Adapter<ReminderAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        //return listItems.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
       */
/* val item = listItems[position]
        holder.tvTitle.text = item.title
        holder.tvTime.text = item.time
        holder.tvStatus.text = item.status
        holder.tvRepeat.text = item.repeat
        holder.itemView.setOnClickListener {
            listener.OnItemClicked(item)*//*

        }
    }

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       */
/* var tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        var tvTime = itemView.findViewById<TextView>(R.id.tvTime)
        var tvStatus = itemView.findViewById<TextView>(R.id.tvStatus)
        var tvRepeat = itemView.findViewById<TextView>(R.id.tvRepeat)*//*

    }

    interface NoteListener{
        fun OnItemClicked(reminder: Reminder)
    }
}

*/
