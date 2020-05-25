package com.example.zvent.add_guest

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isEmpty
import androidx.core.view.isNotEmpty
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.zvent.R
import com.example.zvent.database.Guest
import com.example.zvent.database.GuestType
import com.example.zvent.database.ZventDatabase
import com.example.zvent.databinding.FragmentAddGuestBinding
import com.example.zvent.guests.GuestListViewModel
import kotlinx.android.synthetic.main.fragment_register_guest.*

/**
 * A simple [Fragment] subclass.
 */
class AddGuest : Fragment() {
    private lateinit var viewModelFactory: AddGuestViewModelFactory
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



        //Changes header title
        (activity as AppCompatActivity).supportActionBar?.title = "Agregar invitado"

        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.lifecycleOwner = this

        val application = requireNotNull(this.activity).application
        val dataSource = ZventDatabase.getInstance(application).guestDatabaseDao
        val dataSourceType = ZventDatabase.getInstance(application).guestTypeDatabaseDao
        viewModelFactory = AddGuestViewModelFactory(dataSource, dataSourceType)
        viewModel = ViewModelProvider(this, viewModelFactory).get(AddGuestViewModel::class.java)

        binding.viewModel = viewModel

        val items = arrayListOf<GuestType>()
        val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items)
        binding.roleList.adapter = arrayAdapter

        viewModel.typesList.observe(viewLifecycleOwner, Observer {
            items.clear()
            items.addAll(it)
            if(it.isNotEmpty()) binding.roleList.setSelection(0)
            arrayAdapter.notifyDataSetChanged()
        })
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
                    && email.contains(".") && binding.roleList.isNotEmpty()) {
                    // Generates the guest
                    requireView().hideKeyboard()
                    viewModel.insertGuest(binding.roleList.selectedItem)

                    // Adds the guest to the main list
                    // Hides the keyboard

                    activity?.onBackPressed()
                // incorrect input displays error messages
                } else if (name == "") {
                    Toast.makeText(requireView().context,
                        "Por favor no deje el nombre vacío", Toast.LENGTH_SHORT).show()
                    return false    // Doesn't navigate
                } else if (!email.contains("@") || !email.contains(".")) {
                    Toast.makeText(requireView().context,
                        "Por favor introduzca un correo válido", Toast.LENGTH_SHORT).show()
                    return false
                } else if(binding.roleList.isEmpty())  {
                    Toast.makeText(requireView().context,
                        "Debe definir roles antes de tener invitados", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireView().context,
                        "Por favor introduzca un numero de teléfono entre 3 y 15 dígitos", Toast.LENGTH_SHORT).show()
                    return false
                }
            }
        }
        // Navigates to desired fragment
        return NavigationUI.onNavDestinationSelected(item!!,
            requireView().findNavController()) || super.onOptionsItemSelected(item)
    }


    // Hides the keyboard
    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    override fun onPause() {
        super.onPause()
        requireView().hideKeyboard()
    }
}
