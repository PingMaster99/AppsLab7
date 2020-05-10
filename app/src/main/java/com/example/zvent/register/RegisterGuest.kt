package com.example.zvent.register

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController

import com.example.zvent.R
import com.example.zvent.register.RegisterGuestDirections
import com.example.zvent.databinding.FragmentRegisterGuestBinding
import com.example.zvent.models.Guest

/**
 * A simple [Fragment] subclass.
 */
class RegisterGuest : Fragment() {
    // Guests that are invited
    private val guestList: MutableList<Guest> = mutableListOf(
        Guest(name = "Elmer Curio", phone = "2200-0000", email = "mercurio@tablaperiodica.com"),
        Guest(name = "Zoila Rosa", phone = "1730-1730", email = "rosa@rojas.com"),
        Guest(name = "Michael Scott", phone = "1000-1000", email = "mscott@dunder-mifflin.com"),
        Guest(name = "Bob Vance", phone = "1725-1725", email = "bobvance@vancerefrigeration.com"),
        Guest(name = "Estela Garto", phone = "5432-1000", email = "estela@caiman.com"),
        Guest(name = "Mara Villa", phone = "1111-1111", email = "maravilloso@maravillas.com"),
        Guest(name = "Mr. Bean", phone = "0123-4567", email = "bean@jhonnyenglish.com"),
        Guest(name = "Paco Mer", phone = "1777-1777", email = "comida@comer.com"),
        Guest(name = "Kelly Kapoor", phone = "5678-1234", email = "kapoor@talks.com"),
        Guest(name = "Wanda Maximoff", phone = "1010-1010", email = "scarlet@avengers.com")
    )

    // Data binding
    private lateinit var binding: FragmentRegisterGuestBinding
    // Guests registered and total amount of guests
    private var guestNumber: Int = 1
    private var registered: Int = 0

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

        // Clears variables
        guestNumber = 1
        registered = 0

        // Data binding initialization
        binding = DataBindingUtil.inflate<FragmentRegisterGuestBinding>(inflater,
            R.layout.fragment_register_guest, container, false)


        setDescription()        // Sets the first guest
        setHasOptionsMenu(true) // Menu contains register options

        // Refresh
        binding.invalidateAll()

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
                guestList[guestNumber - 1].registered = "si"    // Guest is registered
                registered++    // Adds one more registered guest
                guestNumber++   // Adds one to the number of checked guests
                // Displays that guest was registered
                Toast.makeText(view!!.context, "Registrado", Toast.LENGTH_SHORT).show()
                setDescription()    // Changes displayed guest
            }

            // Do not register
            R.id.clear -> {
                guestNumber++   // Adds one to the number of checked guests
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
            (activity as AppCompatActivity).supportActionBar?.title = "Registrando ($guestNumber/10)"

            // Changes guest info.
            binding.guestName.text = guestList[guestNumber - 1].name.toString()
            binding.guestPhone.text = guestList[guestNumber - 1].phone.toString()
            binding.guestEmail.text = guestList[guestNumber - 1].email.toString()
        // Once all guests have been checked
        } else {
            // Daves the state of all guests in a string
            var guestString = ""
            for(guest in guestList) {
                if(guest.phone!= guestList[guestList.size -1].phone) {
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
                    registered,
                    guestList.size
                )
            )
        }
    }
}
