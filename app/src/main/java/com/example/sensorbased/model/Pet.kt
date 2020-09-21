package com.example.sensorbased.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [(ForeignKey(
    entity = User::class,
    parentColumns = ["name"],
    childColumns = ["ownerName"]
))])
data class Pet(
    @PrimaryKey
    val name: String,
    val ownerName: String
)