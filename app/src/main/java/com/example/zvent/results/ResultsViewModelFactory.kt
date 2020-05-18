package com.example.zvent.results

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zvent.database.GuestDatabaseDao

/**
 * <h1>ResultsViewModelFactory</h1>
 *<p>
 * ViewModelFactory used for the ViewModel
 *</p>
 *
 * @author Pablo Ruiz (PingMaster99)
 * @version 1.0
 * @since 2020-05-17
 **/
class ResultsViewModelFactory(private val database: GuestDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultsViewModel::class.java)) {
            return ResultsViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}