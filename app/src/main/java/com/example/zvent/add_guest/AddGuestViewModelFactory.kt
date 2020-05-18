package com.example.zvent.add_guest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zvent.database.GuestDatabaseDao
import com.example.zvent.database.GuestTypeDatabaseDao
import java.lang.IllegalArgumentException

/**
 * <h1>AddGuestViewModelFactory</h1>
 *<p>
 * Factory pattern for the ViewModel
 *</p>
 *
 * @author Pablo Ruiz (PingMaster99)
 * @version 1.0
 * @since 2020-05-17
 **/
class AddGuestViewModelFactory(private val database: GuestDatabaseDao,
                               private val databaseType: GuestTypeDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AddGuestViewModel::class.java)) {
            return AddGuestViewModel(database, databaseType) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}