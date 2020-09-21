package com.example.sensorbased.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.sensorbased.model.Pet
import com.example.sensorbased.model.UserPetDB

class PetModel(application: Application): AndroidViewModel(application) {
    private val pets: LiveData<List<Pet>> = UserPetDB.get(getApplication()).petDao().getAll()

    fun getPets() = pets
}