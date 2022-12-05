package com.android.example.coroutineapk

import android.app.Application
import android.util.Log

class MyAppArchitecture : Application() {

    val gameListProvider = GameListProvider()
    override fun onCreate() {
        super.onCreate()
        Log.d("MyAppArchitecture", "It is started")


    }
}