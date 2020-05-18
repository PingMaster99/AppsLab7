package com.example.zvent.results

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.zvent.R
import com.example.zvent.database.ZventDatabase
import com.example.zvent.databinding.FragmentResultsBinding
import com.example.zvent.register.RegisterGuestViewModel

/**
 * A simple [Fragment] subclass.
 */
class Results : Fragment() {

    private lateinit var viewModel: ResultsViewModel
    private lateinit var binding: FragmentResultsBinding
    private lateinit var viewModelFactory: ResultsViewModelFactory

    @SuppressLint("SetTextI18n")
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
        // Changes title to its appropriate name
        (activity as AppCompatActivity).supportActionBar?.title = "Resultado"

        // Data binding
        binding = DataBindingUtil.inflate<FragmentResultsBinding>(inflater,
            R.layout.fragment_results, container, false)


        // Navigates to guest registration if clicked
        binding.restart.setOnClickListener{
            requireView().findNavController().navigate(ResultsDirections.actionResultsToNavRegister())
        }


        // Shows the state of guests if clicked
        binding.viewGuests.setOnClickListener{
            Toast.makeText(this.context, RegisterGuestViewModel.guestResults, Toast.LENGTH_SHORT).show()
        }

        // Share menu
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.lifecycleOwner = this

        val application = requireNotNull(this.activity).application
        val dataSource = ZventDatabase.getInstance(application).guestDatabaseDao
        viewModelFactory = ResultsViewModelFactory(dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ResultsViewModel::class.java)

        binding.viewModel = viewModel

        viewModel.restartRegister.observe(viewLifecycleOwner, Observer { isRestarted ->
            if (isRestarted) {
                requireView().findNavController().navigate(ResultsDirections.actionResultsToNavRegister())
                viewModel.restartComplete()
            }
        })
    }

    /**
     * Generates the menu
     * @param menu
     * @param inflater reads XML
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.share, menu)

        // If the intent doesn't resolve, it makes the share button invisible
        if(null == getShareIntent().resolveActivity(requireActivity().packageManager)) {
            menu.findItem(R.id.share)?.setVisible(false)
        }
    }

    /**
     * Adds functionality to the different menu options
     * @param item from the menu
     * @return Boolean
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> shareSuccess()    // When share is clicked
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Gets the elements that are going to be exported
     * @return Intent with elements
     */
    private fun getShareIntent() : Intent {

        val shareIntent = Intent(Intent.ACTION_SEND)    // To be exported

        // Sets the content of export
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT,
            RegisterGuestViewModel.guestResults)

        return shareIntent
    }

    /**
     * Shares the items prepared for export
     */
    private fun shareSuccess() {
        startActivity(getShareIntent())
    }
}
