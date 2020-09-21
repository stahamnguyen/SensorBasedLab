package com.example.sensorbased.model

import androidx.room.Embedded
import androidx.room.Relation

class UserAndAllPets {
    @Embedded
    var user: User? = null
    @Relation(parentColumn = "name", entityColumn = "ownerName")
    var pets: List<Pet>? = null
}