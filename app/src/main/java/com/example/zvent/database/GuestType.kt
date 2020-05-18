package com.example.zvent.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "guest_type_table")
data class GuestType(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var typeId: Long = 0L,

    @NonNull
    val type: String,
    val description: String,
    val weight: String
) {
    override fun toString(): String {
        return type
    }
}