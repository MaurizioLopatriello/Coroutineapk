package com.android.example.coroutineapk.gameList.ui.usecases

sealed class GameListEvent {
    object GetGameList : GameListEvent()

}