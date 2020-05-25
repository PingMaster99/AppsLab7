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
            1 -> R.drawable.save_icon
            2 -> R.drawable.white_trashcan
            3 -> R.drawable.houseicon
            else -> R.drawable.person_icon
        })
    }
}