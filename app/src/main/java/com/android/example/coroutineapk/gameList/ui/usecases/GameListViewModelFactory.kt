package com.android.example.coroutineapk.gameList.ui.usecases

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.example.coroutineapk.gameList.network.GameListDao
import com.android.example.coroutineapk.gameList.network.GameListProvider
import com.android.example.coroutineapk.gameList.network.dto.AppDatabase

class GameListViewModelFactory(
    private val gameListProvider: GameListProvider,
    private val preferences: SharedPreferences,
    private val database: AppDatabase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameListViewModel::class.java))
            return GameListViewModel(gameListProvider, preferences,  database.getGamesDao()) as T
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}