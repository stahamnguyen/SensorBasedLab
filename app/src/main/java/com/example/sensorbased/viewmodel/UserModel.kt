package com.example.sensorbased.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.sensorbased.model.User
import com.example.sensorbased.model.UserPetDB

class UserModel(application: Application): AndroidViewModel(application) {
    private val users: LiveData<List<User>> = UserPetDB.get(getApplication()).userDao().getAll()

    fun getUsers() = users
}