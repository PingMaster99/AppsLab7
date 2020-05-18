package com.example.zvent.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zvent.database.GuestDatabaseDao

/**
 * <h1>StartViewModel</h1>
 *<p>
 * ViewModel used in the Start fragment
 *</p>
 *
 * @author Pablo Ruiz (PingMaster99)
 * @version 1.0
 * @since 2020-05-17
 **/
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