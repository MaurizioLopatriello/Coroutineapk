package com.android.example.coroutineapk.GameList.Ui.UseCases

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.example.coroutineapk.GameList.Network.GameListProvider

class GameListViewModelFactory(private val gameListProvider: GameListProvider):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameListViewModel::class.java))
            return GameListViewModel(gameListProvider) as T
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}