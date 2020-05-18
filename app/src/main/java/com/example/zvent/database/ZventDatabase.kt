package com.example.zvent.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Guest::class, GuestType::class], version = 5, exportSchema = false)
abstract class ZventDatabase : RoomDatabase() {

    /**
     * Connection to the DAO
     */
    abstract val guestDatabaseDao: GuestDatabaseDao
    abstract val guestTypeDatabaseDao: GuestTypeDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: ZventDatabase? = null

        fun getInstance(context: Context): ZventDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ZventDatabase::class.java,
                        "zvent_app_database"
                    )

                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}