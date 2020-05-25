package com.example.zvent.guests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.zvent.database.GuestDatabaseDao
import com.example.zvent.database.GuestWithType

/**
 * <h1>GuestListViewModel</h1>
 *<p>
 * ViewModel of the GuestList fragment.
 * Includes the main list of guests and string format.
 *</p>
 *
 * @author Pablo Ruiz (PingMaster99)
 * @version 1.0
 * @since 2020-05-10
 **/
class GuestListViewModel(val database: GuestDatabaseDao): ViewModel() {
    // Companion object stores the main guest list and string format

    private val guests = database.getGuestWithType()

    val guestsText = Transformations.map(guests) {
        buildGuestsText(it)
    }

    private fun buildGuestsText(guestsWithType: List<GuestWithType>) : String {
        val guestsText = StringBuilder()
        for(guest in guestsWithType) {
            guestsText.append("Guest: ${guest.guest.guestId}\n" +
                    "Nombre: ${guest.guest.text} \n " +
                    "Telefono: ${guest.guest.phone} \n" +
                    "Email: ${guest.guest.email} \n" +
                    "Rol: ${guest.type}\n \n"
            )
        }
        return guestsText.toString()
    }

    private val _guestClicked = MutableLiveData<Long>()
    val guestClicked: LiveData<Long>
        get() = _guestClicked

    fun onGuestClicked(typeId: Long) {
        _guestClicked.value = typeId
    }

    fun onGuestClickedCompleted(){
        _guestClicked.value = null
    }
}