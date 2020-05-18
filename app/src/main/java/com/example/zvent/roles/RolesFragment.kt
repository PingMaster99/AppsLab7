package com.example.zvent.roles

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
import com.example.zvent.database.ZventDatabase
import com.example.zvent.databinding.FragmentRolesBinding
import com.example.zvent.databinding.FragmentStartBinding

/**
 * A simple [Fragment] subclass.
 */
class RolesFragment : Fragment() {

    private lateinit var viewModelFactory: RolesViewModelFactory
    private lateinit var viewModel: RolesViewModel
    private lateinit var binding: FragmentRolesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = "Roles"

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_roles, container, false)

        binding.addRole.setOnClickListener{
            requireView().findNavController().navigate(RolesFragmentDirections.actionNavRolesToAddRoleFragment())
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.lifecycleOwner = this

        val application = requireNotNull(this.activity).application
        val dataSource = ZventDatabase.getInstance(application).guestTypeDatabaseDao
        viewModelFactory = RolesViewModelFactory(dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory).get(RolesViewModel::class.java)

        binding.viewModel = viewModel

    }



}
