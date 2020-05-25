package com.example.zvent.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zvent.database.Guest
import com.example.zvent.database.GuestDatabaseDao
import com.example.zvent.database.GuestWithType
import com.example.zvent.guests.GuestListViewModel
import kotlinx.coroutines.*

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
class RegisterGuestViewModel(val database: GuestDatabaseDao): ViewModel() {
    // Number of guests and registered guests
    val guests = database.getGuestWithType()

    companion object {
        var registeredGuests = 0
        var guestResults = ""
    }
    private val _registerComplete = MutableLiveData<Boolean>()

    val registerComplete: LiveData<Boolean>
        get() = _registerComplete


    var guestNumber = 1
        private set

    var totalCount = 0
        private set

    val currentGuest = MutableLiveData<GuestWithType>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    fun initialize(guests: List<GuestWithType>) {
        totalCount = guests.size
        if(guests.isEmpty()) {
            _registerComplete.value = true
        } else {
            currentGuest.value = guests[guestNumber - 1]
        }
    }


    /**
     * Switches to the next guest and updates live data
     */
    fun nextGuestRegistered() {
        guestResults += currentGuest.value!!.guest.text + ": si  "
        registeredGuests ++
        val guestWithType = currentGuest.value
        // Guest is registered
        uiScope.launch {
            update(guestWithType?.let {
                Guest(guestId = it.guest.guestId,
                    text = it.guest.text,
                    order = it.guest.order,
                    type_id = it.guest.type_id,
                    email = it.guest.email,
                    phone = it.guest.phone,
                    registered = "si")
            })
        }

        // Updates the guest number
        guestNumber ++

        // Updates values only if the number is within the list size
        if(totalCount >= guestNumber) {
            // Updates current guest
            currentGuest.value = guests.value?.get(guestNumber - 1)
        } else {
            _registerComplete.value = true
        }
    }

    fun nextGuest() {
        val guestWithType = currentGuest.value
        uiScope.launch {
            update(guestWithType?.let {
                Guest(guestId = it.guest.guestId,
                    text = it.guest.text,
                    order = it.guest.order,
                    type_id = it.guest.type_id,
                    email = it.guest.email,
                    phone = it.guest.phone,
                    registered = "no")
            })
        }
        guestResults += currentGuest.value!!.guest.text + ": no  "
        // Updates the guest number
        guestNumber ++

        // Updates values only if the number is within the list size
        if(totalCount >= guestNumber) {
            // Updates current guest
            currentGuest.value = guests.value?.get(guestNumber - 1)
        } else {
            _registerComplete.value = true
        }
    }

    private suspend fun update(guest: Guest?) {
        withContext(Dispatchers.IO) {
            guest ?.let {
                database.update(it)
            }
        }
    }

    fun finishRegister() {
        _registerComplete.value = false
        guestNumber = 1
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}