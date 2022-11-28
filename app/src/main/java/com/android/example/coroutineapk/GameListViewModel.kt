package com.android.example.coroutineapk

import androidx.lifecycle.ViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GameListViewModel : ViewModel() {

    private val retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://free-to-play-games-database.p.rapidapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val gameListService: FreeGamesService = retrofit.create(FreeGamesService::class.java)
}