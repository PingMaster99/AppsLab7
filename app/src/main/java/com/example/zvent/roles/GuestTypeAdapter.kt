package com.example.zvent.roles

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zvent.database.GuestType
import com.example.zvent.databinding.RoleItemLayoutBinding

class GuestTypeAdapter (val listener : GuestTypeClickListener) : ListAdapter<GuestType, GuestTypeAdapter.ViewHolder>(GuestTypeDiffCallback()) {

    override fun onBindViewHolder(holder: GuestTypeAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(listener, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestTypeAdapter.ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: RoleItemLayoutBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: GuestTypeClickListener, item: GuestType){
            binding.guestType = item
            binding.clickListener = clickListener
            Log.i("GuestTypeAdapter", item.toString())
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup) : ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RoleItemLayoutBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }

    }
}

class GuestTypeDiffCallback : DiffUtil.ItemCallback<GuestType>() {

    override fun areItemsTheSame(oldItem: GuestType, newItem: GuestType): Boolean {
        return oldItem.typeId == newItem.typeId
    }
    override fun areContentsTheSame(oldItem: GuestType, newItem: GuestType): Boolean {
        return oldItem == newItem
    }

}

class GuestTypeClickListener (val clickListener: (typeId: Long) -> Unit) {
    fun onClick(type: GuestType) = clickListener(type.typeId)
}