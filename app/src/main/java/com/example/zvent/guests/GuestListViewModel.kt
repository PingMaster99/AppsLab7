package com.example.zvent.guests

import androidx.lifecycle.ViewModel
import com.example.zvent.models.Guest
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
class GuestListViewModel: ViewModel() {
    // Companion object stores the main guest list and string format
    companion object {
        var guestList: MutableList<Guest> = mutableListOf(
        )
        var guestsInString = ""
    }

    /**
     * Returns the list that contains the guests
     * @return guestList with the guests
     */
    fun getList(): MutableList<Guest> {
        return guestList
    }

    /**
     * Adds a guest to the list
     * @param guest to be added
     */
    fun addGuest(guest: Guest) {
        // Updates the guest number according to the size of the list
        var guestNumber = guestList.size + 1
        // Added guest in string format
        var addedGuest = "Invitado " + guestNumber + "\nNombre: " + guest.name +
                "\nTeléfono: " + guest.phone + "\nCorreo: " + guest.email + "\n\n"
        guestsInString += addedGuest    // Adds it to the string
        guestList.add(guest)            // Adds it to the list
    }

    /**
     * Gets the string format of the guests
     * @return guestsInString
     */
    fun getString(): String {
        return guestsInString
    }
}