package com.android.example.coroutineapk.gameList.ui.usecases

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import androidx.room.Room
import com.android.example.coroutineapk.gameList.network.dto.AppDatabase
import com.android.example.coroutineapk.gameList.network.GameListProvider

class MyAppArchitecture : Application() {
    private val gameListProvider = GameListProvider()
    lateinit var preferences: SharedPreferences
   lateinit var gameListViewModelFactory :GameListViewModelFactory
   lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()
        Log.d("MyAppArchitecture ", "Started")
        preferences = getSharedPreferences("This App", MODE_PRIVATE)
        database = Room.databaseBuilder(
          applicationContext,
            AppDatabase::class.java,
            "app-database"
        ).build()
        gameListViewModelFactory= GameListViewModelFactory(gameListProvider,preferences,database)



    }
}