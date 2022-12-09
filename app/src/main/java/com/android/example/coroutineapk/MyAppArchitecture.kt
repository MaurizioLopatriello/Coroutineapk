package com.android.example.coroutineapk

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class MyAppArchitecture: Application() {
   private  val gameListProvider=GameListProvider()
    var gameListViewModelFactory=GameListViewModelFactory(gameListProvider.provide())

    override fun onCreate() {
        super.onCreate()
        Log.d("MyAppArchitecture ", "Started")

    }


    }



