package com.android.example.coroutineapk.GameList.Ui.UseCases

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.example.coroutineapk.GameList.Network.GameListDao
import com.android.example.coroutineapk.GameList.Network.GameListProvider

class GameListViewModelFactory(
    private val gameListProvider: GameListProvider,
    private val preferences: SharedPreferences,
    private val gameListDao: GameListDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameListViewModel::class.java))
            return GameListViewModel(gameListProvider, preferences,gameListDao) as T
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}