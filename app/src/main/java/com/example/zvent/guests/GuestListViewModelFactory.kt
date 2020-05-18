package com.example.zvent.guests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zvent.database.GuestDatabaseDao
import java.lang.IllegalArgumentException

/**
 * <h1>GuestListViewModelFactory</h1>
 *<p>
 * ViewModelFactory used for the ViewModel
 *</p>
 *
 * @author Pablo Ruiz (PingMaster99)
 * @version 1.0
 * @since 2020-05-17
 **/
class GuestListViewModelFactory(private val database: GuestDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(GuestListViewModel::class.java)) {
            return GuestListViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}