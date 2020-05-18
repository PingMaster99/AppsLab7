package com.example.zvent.register

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.zvent.R
import com.example.zvent.database.GuestDatabaseDao
import com.example.zvent.database.ZventDatabase
import com.example.zvent.databinding.FragmentRegisterGuestBinding
import com.example.zvent.guests.GuestListViewModel

/**
 * A simple [Fragment] subclass.
 */
class RegisterGuest : Fragment() {

    // Data binding and ViewModel
    private lateinit var binding: FragmentRegisterGuestBinding
    private lateinit var viewModel: RegisterGuestViewModel
    private lateinit var viewModelFactory: RegisterGuestViewModelFactory



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
        // Clears previous subtitle
        (activity as AppCompatActivity).supportActionBar?.subtitle = ""

        // Data binding initialization
        binding = DataBindingUtil.inflate<FragmentRegisterGuestBinding>(inflater,
            R.layout.fragment_register_guest, container, false)

        setHasOptionsMenu(true) // Menu contains register options

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.lifecycleOwner = this

        RegisterGuestViewModel.registeredGuests = 0
        RegisterGuestViewModel.guestResults = ""
        val application = requireNotNull(this.activity).application
        val dataSource = ZventDatabase.getInstance(application).guestDatabaseDao
        viewModelFactory = RegisterGuestViewModelFactory(dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory).get(RegisterGuestViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.registerComplete.observe(viewLifecycleOwner, Observer {
            if(it) {
                requireView().findNavController().navigate(RegisterGuestDirections.actionNavRegisterToResults())
                viewModel.finishRegister()
            }
        })



        viewModel.guests.observe(viewLifecycleOwner, Observer {
            viewModel.initialize(it)
            (activity as AppCompatActivity).supportActionBar?.title =
                getString(R.string.guests_registered, viewModel.guestNumber, viewModel.totalCount)
        })
    }

    /**
     * Generates the menu
     * @param menu
     * @param inflater reads XML
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.register_options, menu)
    }

    /**
     * Adds functionality to the different menu options
     * @param item from the menu
     * @return Boolean
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        // Item clicked
        when(item.itemId) {
            // Register guest
            R.id.check -> {
                viewModel.nextGuestRegistered()                       // Next guest
                // Displays that guest was registered
                Toast.makeText(requireView().context, "Registrado", Toast.LENGTH_SHORT).show()
            }

            // Do not register
            R.id.clear -> {
                viewModel.nextGuest()                       // Next guest
                // Displays that the guest was not registered
                Toast.makeText(requireView().context, "No registrado", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
