package com.example.zvent.add_role

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zvent.database.GuestTypeDatabaseDao

class AddRoleViewModelFactory(private val database: GuestTypeDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddRoleViewModel::class.java)) {
            return AddRoleViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}