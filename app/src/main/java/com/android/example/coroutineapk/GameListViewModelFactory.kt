package com.android.example.coroutineapk

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GameListViewModelFactory(private val gameListService: GameListService):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameListViewModel::class.java))
            return GameListViewModel(gameListService) as T
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}