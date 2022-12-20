package com.android.example.coroutineapk.GameList.Ui.UseCases

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import com.android.example.coroutineapk.GameList.Network.GameListProvider

class MyAppArchitecture : Application() {
    private val gameListProvider = GameListProvider()
    lateinit var preferences: SharedPreferences
   lateinit var gameListViewModelFactory :GameListViewModelFactory

    override fun onCreate() {
        super.onCreate()
        Log.d("MyAppArchitecture ", "Started")
        preferences = getSharedPreferences("This App", MODE_PRIVATE)
        gameListViewModelFactory= GameListViewModelFactory(gameListProvider,preferences)



    }
}