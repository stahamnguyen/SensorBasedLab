package com.example.sensorbased.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User): Long

    @Update
    fun update(user: User)

    @Query()
}