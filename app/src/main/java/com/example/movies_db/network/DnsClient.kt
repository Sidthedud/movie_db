package com.example.movies_db.network

import okhttp3.OkHttpClient

object DnsClient {

    fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }
}