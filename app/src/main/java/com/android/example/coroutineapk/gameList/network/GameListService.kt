package com.android.example.coroutineapk.gameList.network

import com.android.example.coroutineapk.gameList.network.dto.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GameListService {
    @GET("api/games")
    suspend fun getGameList(@Query("per_page")pageSize: Int):List<ApiResponse.ApiResponseItem>

}