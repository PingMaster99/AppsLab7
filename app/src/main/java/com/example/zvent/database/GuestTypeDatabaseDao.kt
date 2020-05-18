package com.example.zvent.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface GuestTypeDatabaseDao {

    @Insert
    fun insert(guestType: GuestType)

    @Update
    fun update(guest: GuestType)

    @Query("SELECT * FROM guest_type_table WHERE id = :key")
    fun getGuestType(key: Long): GuestType?

    @Query("SELECT * FROM guest_type_table")
    fun getGuestTypes(): LiveData<List<GuestType>>

    @Query("SELECT COUNT(*) FROM guest_type_table")
    fun getGuestTypeCount():LiveData<Int>
}