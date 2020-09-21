package com.example.sensorbased.model

import androidx.room.Embedded
import androidx.room.Relation

data class UserAndAllPets(
    @Embedded
    var user: User,
    @Relation(parentColumn = "name", entityColumn = "ownerName")
    var pets: MutableList<Pet>,
)