package com.example.zvent.guests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zvent.database.GuestDatabaseDao
import com.example.zvent.database.ZventDatabase
import java.lang.IllegalArgumentException

class GuestListViewModelFactory(private val database: GuestDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(GuestListViewModel::class.java)) {
            return GuestListViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}