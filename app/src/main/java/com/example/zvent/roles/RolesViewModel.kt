package com.example.zvent.roles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.zvent.database.GuestType
import com.example.zvent.database.GuestTypeDatabaseDao
import java.lang.StringBuilder

/**
 * <h1>RolesViewModel</h1>
 *<p>
 * ViewModel used in RolesFragment
 *</p>
 *
 * @author Pablo Ruiz (PingMaster99)
 * @version 1.0
 * @since 2020-05-17
 **/
class RolesViewModel(val database: GuestTypeDatabaseDao) : ViewModel() {

    val types = database.getGuestTypes()

    val typesText = Transformations.map(types) {
        buildGuestText(it)
    }

    private fun buildGuestText(types: List<GuestType>) : String {
        val typesText = StringBuilder()
        for(type in types) {
            typesText.append("Rol ${type.typeId}\n Nombre: ${type.type}\n " +
                    "Descripcion: ${type.description}\n ${type.weight}\n\n")
        }
        return typesText.toString()
    }
    private val _guestTypeClicked = MutableLiveData<Long>()
    val guestTypeClicked: LiveData<Long>
        get() = _guestTypeClicked

    fun onGuestTypeClicked(typeId: Long) {
        _guestTypeClicked.value = typeId
    }

    fun onGuestTypeClickedCompleted(){
        _guestTypeClicked.value = null
    }
}