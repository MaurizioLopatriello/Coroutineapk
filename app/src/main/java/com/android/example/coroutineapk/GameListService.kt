package com.android.example.coroutineapk

import retrofit2.http.GET

interface GameListService : List<ApiResponse.ApiResponseItem> {
    @GET("api/games")
    suspend fun getGameList():List<ApiResponse.ApiResponseItem>
}