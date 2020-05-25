package com.example.zvent.guest_view

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs

import com.example.zvent.R
import com.example.zvent.database.ZventDatabase
import com.example.zvent.databinding.FragmentGuestViewBinding

/**
 * A simple [Fragment] subclass.
 */
class GuestViewFragment : Fragment() {

    private lateinit var viewModelFactory: GuestViewViewModelFactory
    private lateinit var viewModel: GuestViewViewModel
    private lateinit var binding: FragmentGuestViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_guest_view,
            container,
            false
        )

        setHasOptionsMenu(true)
        // Title is updates
        (activity as AppCompatActivity).supportActionBar?.title = "Detalles de invitado"

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.lifecycleOwner = this

        val application = requireNotNull(this.activity).application
        val dataSource = ZventDatabase.getInstance(application).guestDatabaseDao

        // Navigation arguments (Guest ID)
        val guestViewFragmentArgs by navArgs<GuestViewFragmentArgs>()

        viewModelFactory = GuestViewViewModelFactory(dataSource, guestViewFragmentArgs.typeId)
        viewModel = ViewModelProvider(this, viewModelFactory).get(GuestViewViewModel::class.java)

        binding.viewModel = viewModel

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_button) {
            viewModel.deleteGuest()
            activity?.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
