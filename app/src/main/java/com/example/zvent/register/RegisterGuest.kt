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
import com.example.zvent.databinding.FragmentRegisterGuestBinding
import com.example.zvent.guests.GuestListViewModel

/**
 * A simple [Fragment] subclass.
 */
class RegisterGuest : Fragment() {

    // Guests that are invited
    var guestList = GuestListViewModel().getList()

    // Data binding and ViewModel
    private lateinit var binding: FragmentRegisterGuestBinding
    private lateinit var viewModel: RegisterGuestViewModel

    // Guests registered and total amount of guests
    private var guestNumber = RegisterGuestViewModel().guestNumber
    private var registered = RegisterGuestViewModel().registered

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
        viewModel = ViewModelProvider(this).get(RegisterGuestViewModel::class.java)


        // Variables are resumed to ViewModel status
        guestNumber = viewModel.guestNumber
        registered = viewModel.registered

        // LiveData observers automatically update guest fields (name, phone, and email)
        viewModel.currentName.observe(viewLifecycleOwner, Observer { newName ->
            binding.guestName.text = newName.toString()
        })
        viewModel.currentPhone.observe(viewLifecycleOwner, Observer { newPhone ->
            binding.guestPhone.text = newPhone.toString()
        })
        viewModel.currentEmail.observe(viewLifecycleOwner, Observer { newEmail ->
            binding.guestEmail.text = newEmail.toString()
        })

        setDescription()        // Sets the first guest
        setHasOptionsMenu(true) // Menu contains register options

        return binding.root
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
                viewModel.currentGuest.registered = "si"    // Guest is registered
                viewModel.registered++                      // Adds one more registered guest
                guestNumber++                               // One more guest checked
                viewModel.nextGuest()                       // Next guest
                // Displays that guest was registered
                Toast.makeText(view!!.context, "Registrado", Toast.LENGTH_SHORT).show()
                setDescription()    // Changes displayed guest
            }

            // Do not register
            R.id.clear -> {
                guestNumber++   // Adds one to the number of checked guests
                viewModel.currentGuest.registered = "no"    // Not registered
                viewModel.nextGuest()                       // Next guest
                // Displays that the guest was not registered
                Toast.makeText(view!!.context, "No registrado", Toast.LENGTH_SHORT).show()
                setDescription()    // Changes displayed guest
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Changes the guest that's shown on screen
     * Instances Results fragment once all guests have been checked
     */
    private fun setDescription() {
        // Keeps changing until all guests have been checked
        if(guestNumber <= guestList.size) {
            // Updates appbar title
            (activity as AppCompatActivity).supportActionBar?.title = "Registrando ($guestNumber/" + guestList.size + ")"
        // Once all guests have been checked
        } else {
            // Saves the state of all guests in a string
            var guestString = ""
            for(guest in guestList) {
                if(guest.phone!= viewModel.currentGuest.phone) {
                    guestString = guestString + guest.name + ": " + guest.registered + ", "
                } else {
                    // A period is added to the last guest instead of a comma
                    guestString = guestString + guest.name + ": " + guest.registered + "."
                }
            }
            // Instances the Result fragment
            view!!.findNavController().navigate(
                RegisterGuestDirections.actionNavRegisterToResults(
                    guestString,
                    viewModel.registered,
                    guestList.size
                )
            )
        }
    }
}
