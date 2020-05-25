package com.example.zvent.guest_view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zvent.database.GuestDatabaseDao

class GuestViewViewModelFactory(private val database: GuestDatabaseDao, private val typeId: Long) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GuestViewViewModel::class.java)) {
            return GuestViewViewModel(database, typeId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}