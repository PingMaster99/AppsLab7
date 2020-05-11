package com.example.zvent.start

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

import com.example.zvent.R
import com.example.zvent.databinding.FragmentStartBinding
import com.example.zvent.guests.GuestListViewModel

/**
 * A simple [Fragment] subclass.
 */
class Start : Fragment() {

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
        // Data binding
        val binding = DataBindingUtil.inflate<FragmentStartBinding>(inflater,
            R.layout.fragment_start, container, false)

        // Navigates to guest registration if clicked
        binding.startButton.setOnClickListener{ view: View ->
            if(GuestListViewModel.guestList.size > 0) {
                view.findNavController().navigate(R.id.action_navStart_to_navRegister)
            } else {
                Toast.makeText(view!!.context,
                    "¡Debe tener invitados antes de poder registrarlos!" +
                            " Presione el boton 'INVITADOS' para ver su lista.",
                    Toast.LENGTH_SHORT).show()
            }
        }

        binding.guests.setOnClickListener{ view: View ->
            view.findNavController().navigate(R.id.action_navStart_to_guestList)
        }

        // Options menu
        setHasOptionsMenu(true)

        return binding.root
    }

    /**
     * Changes app title and subtitle when fragment is resumed
     */
    override fun onResume() {
        (activity as AppCompatActivity).supportActionBar?.title = "Zvent"
        (activity as AppCompatActivity).supportActionBar?.subtitle = "Registro VIP"
        super.onResume()
    }

    /**
     * Generates the menu
     * @param menu
     * @param inflater reads XML
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.options_menu, menu)
    }

    /**
     * Adds functionality to the different menu options (navigates to option destination)
     * @param item from the menu
     * @return Boolean
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!,
        view!!.findNavController()) || super.onOptionsItemSelected(item)
    }

}
