package com.example.testmornhouse.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NumberFactDao {

    @Query("SELECT * FROM numberFactEntity")
    fun getAll(): List<NumberFactEntity>

    @Query("SELECT * FROM numberFactEntity WHERE number LIKE :number AND " +
            "fact LIKE :fact LIMIT 1")
    fun findByNumberAndFact(number: Int, fact: String): NumberFactEntity?

    @Insert
    fun insert(numberFactEntity: NumberFactEntity)
}