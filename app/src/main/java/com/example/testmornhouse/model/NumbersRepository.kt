package com.example.testmornhouse.model

import com.example.testmornhouse.model.okhttp.OkHttpClientHandler
import com.example.testmornhouse.model.room.NumberFactDao
import com.example.testmornhouse.model.room.NumberFactDatabase
import com.example.testmornhouse.model.room.NumberFactEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NumbersRepository @Inject constructor(
    private val okHttpClientHandler: OkHttpClientHandler,
    numberFactDatabase: NumberFactDatabase
    ) {

    private val dao: NumberFactDao = numberFactDatabase.numberFactDao()

    fun getRemoteNumberFact(givenNumber: Int): String {
        okHttpClientHandler.endpointString = StringBuffer()
        okHttpClientHandler.endpointString.append(givenNumber.toString())

        return okHttpClientHandler.getRequest()
    }

    fun getNumberFactHistoryList() : List<NumberFact> {
        return dao.getAll().map { NumberFact(it.number, it.fact) }.asReversed()
    }

    fun checkParametersInDatabaseExist(number: Int, fact: String): Boolean {
        val obtained = dao.findByNumberAndFact(number, fact)
        return obtained != null
    }

    fun saveFactNumberToHistoryListIfNotExists(numberFact: NumberFact) {

        if(dao.findByNumberAndFact(numberFact.number, numberFact.fact) == null) {
            val entity = NumberFactEntity(numberFact.number, numberFact.fact)
            dao.insert(entity)
        }
    }
}