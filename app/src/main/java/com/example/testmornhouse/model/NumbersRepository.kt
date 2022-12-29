package com.example.testmornhouse.model

import javax.inject.Inject

class NumbersRepository @Inject constructor(
    private val okHttpClientHandler: OkHttpClientHandler) {

    fun getRemoteNumberFact(givenNumber: Int): String? {
        okHttpClientHandler.endpointString.append(givenNumber.toString())

        return okHttpClientHandler.getRequest()
    }
}