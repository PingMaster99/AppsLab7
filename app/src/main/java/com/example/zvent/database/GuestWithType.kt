package com.example.zvent.database

import androidx.room.Embedded

data class GuestWithType(
    @Embedded
    val guest: Guest,
    val type: String
)