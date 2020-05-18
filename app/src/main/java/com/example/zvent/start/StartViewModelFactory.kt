package com.example.zvent.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zvent.database.GuestDatabaseDao

/**
 * <h1>StartViewModelFactory</h1>
 *<p>
 * ViewModelFactory used for the ViewModel
 *</p>
 *
 * @author Pablo Ruiz (PingMaster99)
 * @version 1.0
 * @since 2020-05-17
 **/
class StartViewModelFactory(private val database: GuestDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StartViewModel::class.java)) {
            return StartViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}