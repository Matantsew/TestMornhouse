package com.example.testmornhouse.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NumberFactEntity(
    @ColumnInfo(name = "number") val number: Int,
    @ColumnInfo(name = "fact") val fact: String
){

    @PrimaryKey(autoGenerate = true) var id: Int? = null
}