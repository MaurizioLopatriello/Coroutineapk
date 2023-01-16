package com.android.example.coroutineapk.gameList.network.dto

import okhttp3.Interceptor
import okhttp3.Response


const val API_AUTHORIZATION_HEADER = "x-rapidapi-key"

class AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val newRequest = request.newBuilder().addHeader(
            API_AUTHORIZATION_HEADER,
            "19741e6969msh20e36a4537859e6p181b91jsncff9edd55d1b"
        ).build()

        return chain.proceed(newRequest)
    }
}


