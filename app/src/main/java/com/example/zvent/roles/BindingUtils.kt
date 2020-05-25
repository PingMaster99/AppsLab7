package com.example.zvent.roles

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.zvent.R

@BindingAdapter("typeImage")
fun ImageView.setTypeImage(index: Int?) {
    Log.i("Binding", index.toString())
    index?.let {
        setImageResource(when (index) {
            1 -> R.drawable.bike_rider
            2 -> R.drawable.person_icon
            3 -> R.drawable.save_icon
            else -> R.drawable.role_icon
        })
    }
}