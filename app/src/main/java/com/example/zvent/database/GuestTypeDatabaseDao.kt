package com.example.zvent.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GuestTypeDatabaseDao {

    @Insert
    fun insert(guestType: GuestType)

    @Update
    fun update(guest: GuestType)

    @Delete
    fun delete(guestType: GuestType)

    @Query("SELECT * FROM guest_type_table WHERE id = :key")
    fun getGuestType(key: Long): LiveData<GuestType>

    @Query("SELECT * FROM guest_type_table")
    fun getGuestTypes(): LiveData<List<GuestType>>

    @Query("SELECT COUNT(*) FROM guest_type_table")
    fun getGuestTypeCount():LiveData<Int>
}