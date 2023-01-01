package com.example.testmornhouse.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NumberFactEntity(
    @PrimaryKey val id: Int,
    val number: Int,
    val fact: String
)