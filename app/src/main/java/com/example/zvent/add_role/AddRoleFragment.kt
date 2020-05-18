package com.example.zvent.add_role

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.zvent.R
import com.example.zvent.database.ZventDatabase
import com.example.zvent.databinding.FragmentAddRoleBinding

/**
 * A simple [Fragment] subclass.
 */
class AddRoleFragment : Fragment() {

    private lateinit var viewModelFactory: AddRoleViewModelFactory
    private lateinit var viewModel: AddRoleViewModel
    private lateinit var binding: FragmentAddRoleBinding

    private lateinit var seekBar: SeekBar

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_role, container, false)
        binding.progress.text = "Orden: " + "1"
        (activity as AppCompatActivity).supportActionBar?.title = "Agregar Roles"
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.lifecycleOwner = this


        val application = requireNotNull(this.activity).application
        val dataSource = ZventDatabase.getInstance(application).guestTypeDatabaseDao

        viewModelFactory = AddRoleViewModelFactory(dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory).get(AddRoleViewModel::class.java)


        seekBar = binding.seekBar2
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.progress.text = "Orden: " + (seekBar!!.progress + 1)

            }

            @SuppressLint("SetTextI18n")
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            @SuppressLint("SetTextI18n")
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })


        binding.viewModel = viewModel
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.save_guest, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.guestList) {
            viewModel.insertGuestType()
            activity?.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}
