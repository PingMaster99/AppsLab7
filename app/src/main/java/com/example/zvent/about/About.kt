package com.example.zvent.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.zvent.R
import com.example.zvent.databinding.FragmentAboutBinding


/**
 * A simple [Fragment] subclass.
 */
class About : Fragment() {
    /**
     * Builds the fragment
     * @param inflater reads XML file
     * @param container that contains the fragment
     * @param savedInstanceState saved instances of the fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // About title
        (activity as AppCompatActivity).supportActionBar?.title = "Acerca de Zvent"

        // Data binding
        val binding = DataBindingUtil.inflate<FragmentAboutBinding>(inflater,
            R.layout.fragment_about, container, false)

        return binding.root
    }
}
