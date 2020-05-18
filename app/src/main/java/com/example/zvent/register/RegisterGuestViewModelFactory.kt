package com.example.zvent.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zvent.database.GuestDatabaseDao

/**
 * <h1>RegisterGuestViewModelFactory</h1>
 *<p>
 * ViewModelFactory used for the ViewModel
 *</p>
 *
 * @author Pablo Ruiz (PingMaster99)
 * @version 1.0
 * @since 2020-05-17
 **/
class RegisterGuestViewModelFactory(private val database: GuestDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterGuestViewModel::class.java)) {
            return RegisterGuestViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}