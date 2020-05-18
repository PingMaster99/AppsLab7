package com.example.zvent.add_role

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zvent.database.GuestType
import com.example.zvent.database.GuestTypeDatabaseDao
import kotlinx.coroutines.*

/**
 * <h1>AddRoleViewModel</h1>
 *<p>
 * ViewModel used for AddRoleFragment
 *</p>
 *
 * @author Pablo Ruiz (PingMaster99)
 * @version 1.0
 * @since 2020-05-17
 **/
class AddRoleViewModel(val database: GuestTypeDatabaseDao) : ViewModel() {
    val type = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val weight = MutableLiveData<String>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun insertGuestType() {
        uiScope.launch {
            insert()
        }
    }

    private suspend fun insert() {
        withContext(Dispatchers.IO) {
            database.insert(GuestType(type = type.value ?: "",
                description = description.value ?: "", weight = weight.value ?: ""))
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}