package com.example.zvent.roles_view

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs

import com.example.zvent.R
import com.example.zvent.database.ZventDatabase
import com.example.zvent.databinding.FragmentRolesViewBinding
import com.example.zvent.roles.RolesFragmentDirections

/**
 * A simple [Fragment] subclass.
 */
class RolesViewFragment : Fragment() {

    private lateinit var viewModelFactory: RolesViewViewModelFactory
    private lateinit var viewModel: RolesViewViewModel
    private lateinit var binding: FragmentRolesViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_roles_view,
            container,
            false
        )

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.lifecycleOwner = this

        val application = requireNotNull(this.activity).application
        val dataSource = ZventDatabase.getInstance(application).guestTypeDatabaseDao

        val RolesViewFragmentArgs by navArgs<RolesViewFragmentArgs>()

        viewModelFactory = RolesViewViewModelFactory(dataSource, RolesViewFragmentArgs.typeId)
        viewModel = ViewModelProvider(this, viewModelFactory).get(RolesViewViewModel::class.java)

        binding.viewModel = viewModel

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_button) {
            viewModel.deleteQuestionType()
            activity?.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
