package com.example.testmornhouse.model.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NumberFactEntity::class], version = 1)
abstract class NumberFactDatabase : RoomDatabase() {
    abstract fun numberFactDao(): NumberFactDao
}