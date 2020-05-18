package com.example.zvent.add_guest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zvent.database.Guest
import com.example.zvent.database.GuestDatabaseDao
import com.example.zvent.database.GuestType
import com.example.zvent.database.GuestTypeDatabaseDao
import kotlinx.coroutines.*

/**
 * <h1>AddGuestViewModel</h1>
 *<p>
 * ViewModel of the AddGuest framgent
 *</p>
 *
 * @author Pablo Ruiz (PingMaster99)
 * @version 1.0
 * @since 2020-05-10
 **/
class AddGuestViewModel(val database: GuestDatabaseDao,
                        val databaseType: GuestTypeDatabaseDao): ViewModel() {


    val guestInfo = MutableLiveData<String>()
    val guestEmail = MutableLiveData<String>()
    val guestPhone = MutableLiveData<String>()

    val typesList = databaseType.getGuestTypes()
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun insertGuest(type: Any) {
        uiScope.launch {
            insert(type as GuestType)
        }
    }

    private suspend fun insert(guestType: GuestType?) {
        withContext(Dispatchers.IO) {
            database.insert(Guest(text = guestInfo.value ?: "", type_id = guestType?.typeId ?: 0L,
                email = guestEmail.value ?: "", phone = guestPhone.value ?: "", registered = "no"))

        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}