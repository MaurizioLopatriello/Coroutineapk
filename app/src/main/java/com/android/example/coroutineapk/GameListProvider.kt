package com.android.example.coroutineapk

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GameListProvider {

    private val logging = HttpLoggingInterceptor()
    private val authorization = AuthorizationInterceptor()
    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor(authorization)
        .build()

    init {
        logging.level = HttpLoggingInterceptor.Level.BASIC
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://free-to-play-games-database.p.rapidapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val gameListService: GameListService =
        retrofit.create(GameListService::class.java)

    fun provide():GameListService=gameListService

    }

