package com.example.zvent.guests

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.zvent.R
import com.example.zvent.database.ZventDatabase
import com.example.zvent.databinding.FragmentGuestListBinding

/**
 * A simple [Fragment] subclass.
 */
class GuestList : Fragment() {

    private lateinit var binding: FragmentGuestListBinding
    private lateinit var viewModel: GuestListViewModel
    private lateinit var viewModelFactory: GuestListViewModelFactory

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

        // Changes action bar title
        (activity as AppCompatActivity).supportActionBar?.title = "Lista de invitados"

        // Click listener toadd a guest (floating action button)
        binding.addGuest.setOnClickListener{view: View ->
            view.findNavController().navigate(GuestListDirections.actionGuestListToAddGuest())
        }


        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.lifecycleOwner = this

        val application = requireNotNull(this.activity).application
        val dataSource = ZventDatabase.getInstance(application).guestDatabaseDao
        viewModelFactory = GuestListViewModelFactory(dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory).get(GuestListViewModel::class.java)

        binding.viewModel = viewModel

        viewModel.guestClicked.observe(viewLifecycleOwner, Observer {
            if(it != null) {
                requireView().findNavController().navigate(GuestListDirections.actionGuestListToGuestViewFragment(it))
                viewModel.onGuestClickedCompleted()
            }
        })

        val adapter = GuestAdapter(GuestClickListener {
            viewModel.onGuestClicked(it)
        })

        binding.guestList.adapter = adapter

        viewModel.guestList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

    }
}
