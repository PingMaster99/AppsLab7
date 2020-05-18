package com.example.zvent.roles

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.zvent.database.GuestDatabaseDao
import com.example.zvent.database.GuestType
import com.example.zvent.database.GuestTypeDatabaseDao
import com.example.zvent.database.ZventDatabase
import java.lang.StringBuilder

class RolesViewModel(val database: GuestTypeDatabaseDao) : ViewModel() {

    private val types = database.getGuestTypes()

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
}