package com.example.zvent.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zvent.guests.GuestListViewModel
/**
 * <h1>RegisterGuestViewModel</h1>
 *<p>
 * ViewModel of the RegisterGuest framgent
 *</p>
 *
 * @author Pablo Ruiz (PingMaster99)
 * @version 1.0
 * @since 2020-05-10
 **/
class RegisterGuestViewModel: ViewModel() {
    // Number of guests and registered guests
    var guestNumber = 1
    var registered = 0
    // Current guest that's being checked
    var currentGuest = GuestListViewModel.guestList[guestNumber - 1]

    // Live data for guest's attributes
    val currentName = MutableLiveData<String>()
    val currentPhone = MutableLiveData<String>()
    val currentEmail = MutableLiveData<String>()


    /**
     * Constructor for the ViewModel
     */
    init {
        // Sets the initial guest attributes
        currentName.value = currentGuest.name
        currentPhone.value = currentGuest.phone
        currentEmail.value = currentGuest.email
    }


    /**
     * Switches to the next guest and updates live data
     */
    fun nextGuest() {
        // Updates the guest number
        guestNumber ++
        // Updates values only if the number is within the list size
        if(guestNumber <= GuestListViewModel.guestList.size) {
            // Updates current guest
            currentGuest  = GuestListViewModel.guestList[guestNumber - 1]
            // Updates LiveData attributes
            currentName.value = currentGuest.name
            currentPhone.value = currentGuest.phone
            currentEmail.value = currentGuest.email
        }
    }
}