package com.example.sensorbased.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [(User::class), (Pet::class)], version = 1)
abstract class UserPetDB: RoomDatabase() {
    abstract fun userDao()
    abstract fun petDao()

    companion object {
        private var instance: UserPetDB? = null
        @Synchronized
        fun get(context: Context): UserPetDB {
            if (instance == null ) {
                instance = Room.databaseBuilder(context.applicationContext, UserPetDB::class.java, "userPet.db").build()
            }
            return instance!!
        }
    }
}