package com.example.testmornhouse.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NumberFactDao {

    @Query("SELECT * FROM numberFactEntity")
    fun getAll(): List<NumberFactEntity>

    @Insert
    fun insert(numberFactEntity: NumberFactEntity)
}