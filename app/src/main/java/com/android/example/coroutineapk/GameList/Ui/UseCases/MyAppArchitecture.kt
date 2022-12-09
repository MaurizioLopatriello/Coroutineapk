package com.android.example.coroutineapk.GameList.Ui.UseCases

import android.app.Application
import android.util.Log
import com.android.example.coroutineapk.GameList.Network.GameListProvider
import com.android.example.coroutineapk.GameList.Ui.UseCases.GameListViewModelFactory

class MyAppArchitecture: Application() {
   private  val gameListProvider= GameListProvider()
    var gameListViewModelFactory= GameListViewModelFactory(gameListProvider)

    override fun onCreate() {
        super.onCreate()
        Log.d("MyAppArchitecture ", "Started")

    }


    }



