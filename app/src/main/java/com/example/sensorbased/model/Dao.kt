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

//    @Query("SELECT * FROM user WHERE user.name = :username")
//    fun getAllPetsOfUser(username: String): UserAndAllPets
}

@Dao
interface PetDao {
    @Query("SELECT * FROM pet")
    fun getAll(): LiveData<List<Pet>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pet: Pet): Long

    @Update
    fun update(pet: Pet)

//    @Query("SELECT * FROM user WHERE user.name = :username")
//    fun getAllPetsOfUser(username: String): UserAndAllPets
}