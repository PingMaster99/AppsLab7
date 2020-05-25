package com.example.zvent.guests

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.zvent.R

@BindingAdapter("typeImage")
fun ImageView.setTypeImage(index: Long?) {
    Log.i("Binding", index.toString())
    index?.let {
        setImageResource(when (index) {
            1L -> R.drawable.bike_rider
            2L -> R.drawable.person_icon
            3L -> R.drawable.save_icon
            else -> R.drawable.role_icon
        })
    }
}
@BindingAdapter("setTypeAssistance")
fun ImageView.setTypeAssistance(index: String) {
    index?.let {
        setImageResource(when (index) {
            "no" -> R.drawable.not_confirmed
            "si" -> R.drawable.confirmed
            else -> R.drawable.confirmed
        })
    }
}