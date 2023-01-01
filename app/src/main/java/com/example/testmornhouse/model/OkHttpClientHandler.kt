package com.example.testmornhouse.model

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import javax.inject.Inject

class OkHttpClientHandler @Inject constructor() {

    private val client = OkHttpClient()

    var endpointString = StringBuffer()

    fun getRequest() : String {

        val request = Request.Builder()
            .url(Api.BASE_URL + endpointString)
            .build()

        return client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            response.body?.string().toString()
        }
    }

}