package com.example.sensorbased.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sensorbased.model.GlobalModel
import com.example.sensorbased.model.President
import com.example.sensorbased.repository.WikiServiceRepo

class MainViewModel : ViewModel() {
  private val repo = WikiServiceRepo()

  val selectedPresident = MutableLiveData<President>(GlobalModel.presidents[0])
}