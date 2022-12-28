package com.example.testmornhouse.model

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class OkHttpClientHandler {

    private val client = OkHttpClient()

    fun getRequest() : String? {

        val request = Request.Builder()
            .url(Api.BASE_URL + "3")
            .build()

        return client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            for ((name, value) in response.headers) {
                println("$name: $value")
            }

            response.body?.string()
        }
    }

}