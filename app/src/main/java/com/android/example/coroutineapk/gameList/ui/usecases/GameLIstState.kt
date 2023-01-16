package com.android.example.coroutineapk.gameList.ui.usecases

import com.android.example.coroutineapk.gameList.network.dto.ApiResponse


sealed class GameLIstState {
    data class GameList(val list: List<ApiResponse.ApiResponseItem>) : GameLIstState()
    data class Error(val error: Exception) : GameLIstState()
    object FirstTimeUser : GameLIstState()
}