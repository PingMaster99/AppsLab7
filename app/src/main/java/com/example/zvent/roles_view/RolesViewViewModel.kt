package com.example.zvent.roles_view

import androidx.lifecycle.ViewModel
import com.example.zvent.database.GuestTypeDatabaseDao
import com.example.zvent.database.ZventDatabase
import kotlinx.coroutines.*

class RolesViewViewModel(val database: GuestTypeDatabaseDao, val typeId: Long): ViewModel() {
    val guestType = database.getGuestType(typeId)

    val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun deleteQuestionType(){
        uiScope.launch {
            delete()
        }
    }

    private suspend fun delete(){
        withContext(Dispatchers.IO) {
            guestType.value?.let { database.delete(it) }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}