package com.android.example.coroutineapk.gameList.network

import com.android.example.coroutineapk.gameList.network.dto.AuthorizationInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val  PAGE_SIZE = 20
class GameListProvider {


    private val logging = HttpLoggingInterceptor()
    private val authorization = AuthorizationInterceptor()
    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor(authorization)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://free-to-play-games-database.p.rapidapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val gameListService: GameListService =
        retrofit.create(GameListService::class.java)

    init {
        logging.level = HttpLoggingInterceptor.Level.BASIC
    }

   suspend fun getGameList() =gameListService.getGameList(PAGE_SIZE)
}