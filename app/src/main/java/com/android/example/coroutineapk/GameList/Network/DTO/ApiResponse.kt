package com.android.example.coroutineapk.GameList.Network.DTO

import com.android.example.coroutineapk.GameList.Ui.Model.GameListRepo

class ApiResponse : ArrayList<ApiResponse.ApiResponseItem>(){
    data class ApiResponseItem(
        val id: Int,
        val title: String,
        val thumbnail: String,
        val short_description: String,
        val game_url: String,
        val genre: String,
        val platform: String,
        val publisher: String,
        val developer: String,
        val release_date: String,
        val freetogame_profile_url: String
    )

}
