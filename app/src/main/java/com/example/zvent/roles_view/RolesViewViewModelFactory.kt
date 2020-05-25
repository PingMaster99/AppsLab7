package com.example.zvent.roles_view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zvent.database.GuestTypeDatabaseDao

class RolesViewViewModelFactory(private val database: GuestTypeDatabaseDao, private val typeId: Long) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RolesViewViewModel::class.java)) {
            return RolesViewViewModel(database, typeId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}