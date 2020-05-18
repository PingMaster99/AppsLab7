package com.example.zvent.start

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

import com.example.zvent.R
import com.example.zvent.database.ZventDatabase
import com.example.zvent.databinding.FragmentStartBinding
import kotlinx.android.synthetic.main.fragment_results.*

/**
 * A simple [Fragment] subclass.
 */
class Start : Fragment() {

    private lateinit var viewModelFactory: StartViewModelFactory
    private lateinit var viewModel: StartViewModel
    private lateinit var binding: FragmentStartBinding

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
        binding = DataBindingUtil.inflate<FragmentStartBinding>(inflater,
            R.layout.fragment_start, container, false)

        // Options menu
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.lifecycleOwner = this

        val application = requireNotNull(this.activity).application
        val dataSource = ZventDatabase.getInstance(application).guestDatabaseDao
        viewModelFactory = StartViewModelFactory(dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory).get(StartViewModel::class.java)

        binding.viewModel = viewModel


        viewModel.startRegister.observe(viewLifecycleOwner, Observer { isStarted ->
            if(isStarted) {
                requireView().findNavController().navigate(StartDirections.actionNavStartToNavRegister())
                viewModel.startComplete()
            } else {
                Toast.makeText(this.context, "Recuerde asignar invitados y roles antes de registrarlos", Toast.LENGTH_SHORT).show()
            }
        })

        binding.guests.setOnClickListener{view: View ->
            view.findNavController().navigate(StartDirections.actionNavStartToGuestList())
        }

        viewModel.guestCount.observe(viewLifecycleOwner, Observer { count ->
            if(count == 0) {
                viewModel.startComplete()
            }
        })
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
        requireView().findNavController()) || super.onOptionsItemSelected(item)
    }

}
