package com.example.zvent.guest_view

import androidx.lifecycle.ViewModel
import com.example.zvent.database.GuestDatabaseDao
import kotlinx.coroutines.*

class GuestViewViewModel(val database: GuestDatabaseDao, val typeId: Long) : ViewModel() {

    val guestFound = database.getGuest(typeId)
    val guestFoundType = database.getCurrentGuestWithType(typeId)
    val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun deleteGuest(){
        uiScope.launch {
            delete()
        }
    }

    private suspend fun delete(){
        withContext(Dispatchers.IO) {
            guestFound.value?.let { database.delete(it) }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}