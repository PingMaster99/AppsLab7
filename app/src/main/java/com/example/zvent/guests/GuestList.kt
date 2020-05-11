package com.example.zvent.guests

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.zvent.R
import com.example.zvent.databinding.FragmentGuestListBinding

/**
 * A simple [Fragment] subclass.
 */
class GuestList : Fragment() {

    private lateinit var binding: FragmentGuestListBinding
    private lateinit var viewModel: GuestListViewModel

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

        // Data binding and ViewModel
        binding = DataBindingUtil.inflate<FragmentGuestListBinding>(inflater,
            R.layout.fragment_guest_list, container, false)
        viewModel = ViewModelProvider(this).get(GuestListViewModel::class.java)

        // Changes action bar title
        (activity as AppCompatActivity).supportActionBar?.title = "Lista de invitados"

        // Click listener toadd a guest (floating action button)
        binding.addGuest.setOnClickListener{view: View ->
            view.findNavController().navigate(R.id.action_guestList_to_addGuest)
        }

        // Sets the list of guests according to the ViewModel
        binding.currentGuests.text = viewModel.getString()
        // If no guests have been registered, a message to register guests displays
        if(binding.currentGuests.text == "") {
            binding.currentGuests.text = getString(R.string.noGuestsRegistered)
        }

        // Inflate the layout for this fragment
        return binding.root
    }
}
