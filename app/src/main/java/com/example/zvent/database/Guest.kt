package com.example.zvent.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.SET_DEFAULT
import androidx.room.PrimaryKey

@Entity(tableName = "guest_table",
    foreignKeys = [
        ForeignKey(entity = GuestType::class,
        parentColumns = ["id"],
        childColumns = ["type_id"],
        onDelete = SET_DEFAULT)
    ])

data class Guest (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var guestId: Long = 0L,

    @NonNull
    val text: String,
    var order: String = "",
    var type_id: Long = 0L,
    var email: String = "",
    var phone: String = "",
    var registered: String = ""
)