package com.example.zvent.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zvent.database.GuestDatabaseDao
import com.example.zvent.database.ZventDatabase

class StartViewModel(val database: GuestDatabaseDao) : ViewModel() {

    val guestCount = database.getGuestCount()
    private val _startRegister = MutableLiveData<Boolean>()
    val startRegister: LiveData<Boolean>
        get() = _startRegister

    fun start() {
        _startRegister.value = guestCount.value ?: 0 > 0
    }

    fun startComplete() {
        _startRegister.value = false
    }
}