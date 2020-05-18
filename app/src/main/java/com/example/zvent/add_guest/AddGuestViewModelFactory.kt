package com.example.zvent.add_guest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zvent.database.GuestDatabaseDao
import com.example.zvent.database.GuestTypeDatabaseDao
import com.example.zvent.database.ZventDatabase
import java.lang.IllegalArgumentException

class AddGuestViewModelFactory(private val database: GuestDatabaseDao,
                               private val databaseType: GuestTypeDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AddGuestViewModel::class.java)) {
            return AddGuestViewModel(database, databaseType) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}