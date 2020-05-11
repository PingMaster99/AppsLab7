package com.example.zvent.add_guest

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.zvent.R
import com.example.zvent.databinding.FragmentAddGuestBinding
import com.example.zvent.guests.GuestListViewModel

/**
 * A simple [Fragment] subclass.
 */
class AddGuest : Fragment() {

    private lateinit var viewModel: AddGuestViewModel
    private lateinit var binding: FragmentAddGuestBinding

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
        binding = DataBindingUtil.inflate<FragmentAddGuestBinding>(inflater,
            R.layout.fragment_add_guest, container, false)
        setHasOptionsMenu(true) // Menu contains register options
        viewModel = ViewModelProvider(this).get(AddGuestViewModel::class.java)

        //Changes header title
        (activity as AppCompatActivity).supportActionBar?.title = "Agregar invitado"

        // Inflate the layout for this fragment
        return binding.root
    }
    /**
     * Generates the menu
     * @param menu
     * @param inflater reads XML
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.save_guest, menu)
    }

    /**
     * Adds functionality to the different menu options
     * @param item from the menu
     * @return Boolean
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.guestList -> {
                // Guest attributes
                val name = binding.name.text.toString()
                val phone = binding.phone.text.toString()
                val email = binding.email.text.toString()

                // Only allows a guest to be registered if inputs are correct
                if(name != "" && phone.length <= 15 && phone.length >= 3 && email.contains("@")
                    && email.contains(".")) {
                    // Generates the guest
                    viewModel.addGuest(binding.name.text.toString(), binding.phone.text.toString(),
                        binding.email.text.toString())
                    // Adds the guest to the main list
                    GuestListViewModel().addGuest(viewModel.addedGuest)
                    // Hides the keyboard
                    view!!.hideKeyboard()
                // incorrect input displays error messages
                } else if (name == "") {
                    Toast.makeText(view!!.context,
                        "Por favor no deje el nombre vacío", Toast.LENGTH_SHORT).show()
                    return false    // Doesn't navigate
                } else if (!email.contains("@") || !email.contains(".")) {
                    Toast.makeText(view!!.context,
                        "Por favor introduzca un correo válido", Toast.LENGTH_SHORT).show()
                    return false
                } else {
                    Toast.makeText(view!!.context,
                        "Por favor introduzca un numero de teléfono entre 3 y 15 dígitos", Toast.LENGTH_SHORT).show()
                    return false
                }
            }
        }
        // Navigates to desired fragment
        return NavigationUI.onNavDestinationSelected(item!!,
            view!!.findNavController()) || super.onOptionsItemSelected(item)
    }

    // Hides the keyboard
    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}
