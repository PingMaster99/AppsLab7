package com.example.zvent.roles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zvent.database.GuestTypeDatabaseDao

/**
 * <h1>RolesViewModelFactory</h1>
 *<p>
 * ViewModelFactory used for the ViewModel
 *</p>
 *
 * @author Pablo Ruiz (PingMaster99)
 * @version 1.0
 * @since 2020-05-17
 **/
class RolesViewModelFactory(private val database: GuestTypeDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RolesViewModel::class.java)) {
            return RolesViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}