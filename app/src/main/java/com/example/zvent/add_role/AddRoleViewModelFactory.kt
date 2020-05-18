package com.example.zvent.add_role

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zvent.database.GuestTypeDatabaseDao

/**
 * <h1>AddRoleViewModelFactory</h1>
 *<p>
 * Factory pattern for the ViewModel
 *</p>
 *
 * @author Pablo Ruiz (PingMaster99)
 * @version 1.0
 * @since 2020-05-17
 **/
class AddRoleViewModelFactory(private val database: GuestTypeDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddRoleViewModel::class.java)) {
            return AddRoleViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}