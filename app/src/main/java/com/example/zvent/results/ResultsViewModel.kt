package com.example.zvent.results

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.zvent.database.Guest
import com.example.zvent.database.GuestDatabaseDao
import com.example.zvent.register.RegisterGuestViewModel
import java.lang.StringBuilder

/**
 * <h1>ResultsViewModel</h1>
 *<p>
 * ViewModel of the Results fragment
 *</p>
 *
 * @author Pablo Ruiz (PingMaster99)
 * @version 1.0
 * @since 2020-05-10
 **/
class ResultsViewModel(val database: GuestDatabaseDao): ViewModel() {
    private val guests = database.getGuests()
    val guestCount = Transformations.map(guests) {it.size}

    val resultsText = Transformations.map(guests) {
        buildResultsText(it)
    }

    val numberRegistered = Transformations.map(guests) {
        getRegistered(it)
    }

    private val _restartRegister = MutableLiveData<Boolean>()
    val restartRegister: LiveData<Boolean>
        get() = _restartRegister

    fun restartComplete() {
        _restartRegister.value = false
    }

    private fun buildResultsText(guests: List<Guest>) : String {
        var resultsText = StringBuilder()
        for (guest in guests) {
            resultsText.append("Invitado ${guest.guestId}: ${guest.text} : ${guest.order} \n")
            if(guest.registered == "si") {
            }
        }
        return resultsText.toString()
    }

    private fun getRegistered(guests: List<Guest>) : String {
        return RegisterGuestViewModel.registeredGuests.toString()
    }
}