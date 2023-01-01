package com.example.testmornhouse.model

import com.example.testmornhouse.model.room.NumberFactDatabase
import com.example.testmornhouse.model.room.NumberFactEntity
import javax.inject.Inject

class NumbersRepository @Inject constructor(
    private val okHttpClientHandler: OkHttpClientHandler,
    private val numberFactDatabase: NumberFactDatabase
    ) {

    fun getRemoteNumberFact(givenNumber: Int): String {
        okHttpClientHandler.endpointString.append(givenNumber.toString())

        return okHttpClientHandler.getRequest()
    }

    fun getNumberFactHistoryList() : List<NumberFact> {
        val dao = numberFactDatabase.numberFactDao()

        return dao.getAll().map { NumberFact(it.number, it.fact) }
    }

    fun saveFactNumberToHistoryList(numberFact: NumberFact) {
        val dao = numberFactDatabase.numberFactDao()

        val entity = NumberFactEntity(numberFact.number, numberFact.number, numberFact.fact)

        dao.insert(entity)
    }
}