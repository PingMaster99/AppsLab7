package com.example.zvent.guests

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zvent.database.Guest
import com.example.zvent.database.GuestWithType
import com.example.zvent.databinding.GuestLayoutBinding

class GuestAdapter (val listener : GuestClickListener) : ListAdapter<Guest, GuestAdapter.ViewHolder>(QuestionTypeDiffCallback()) {

    override fun onBindViewHolder(holder: GuestAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(listener, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestAdapter.ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: GuestLayoutBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: GuestClickListener, item: Guest){
            binding.guest = item
            binding.clickListener = clickListener
            Log.i("QuestionTypeAdapter", item.toString())
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup) : ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = GuestLayoutBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }

    }
}

class QuestionTypeDiffCallback : DiffUtil.ItemCallback<Guest>() {

    override fun areItemsTheSame(oldItem: Guest, newItem: Guest): Boolean {
        return oldItem.text == newItem.text
    }
    override fun areContentsTheSame(oldItem: Guest, newItem: Guest): Boolean {
        return oldItem == newItem
    }

}

class GuestClickListener (val clickListener: (typeId: Long) -> Unit) {
    fun onClick(type: Guest) = clickListener(type.guestId)
}