package com.android.example.coroutineapk.GameList.Network

import com.android.example.coroutineapk.GameList.Network.DTO.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GameListService {
    @GET("api/games")
    suspend fun getGameList(@Query("per_page")pageSize: Int):List<ApiResponse.ApiResponseItem>

}