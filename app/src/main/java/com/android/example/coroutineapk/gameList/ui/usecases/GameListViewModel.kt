package com.android.example.coroutineapk.gameList.ui.usecases

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.example.coroutineapk.gameList.network.GameListDao
import com.android.example.coroutineapk.gameList.network.GameListProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

const val KEY_FIRST_TIME_USER = "first_time_user"

class GameListViewModel(
    private val gameListProvider: GameListProvider,
    preferences: SharedPreferences,
    private val dao: GameListDao
) : ViewModel() {

    fun send(event: GameListEvent) {
        when (event) {
            GameListEvent.GetGameList -> retrieveGameList()
        }
    }

    val gameLIstState = MutableSharedFlow<GameLIstState>()


    init {
        checkFirstTimeUser(preferences)
        setupDatabaseObserver()
    }

    private fun checkFirstTimeUser(preferences: SharedPreferences) {
        val checkUser = preferences.getBoolean(KEY_FIRST_TIME_USER, true)
        if (checkUser) {
            preferences.edit().putBoolean(KEY_FIRST_TIME_USER, false).apply()
            viewModelScope.launch {
                gameLIstState.emit(GameLIstState.FirstTimeUser)
            }
        }
    }

    private fun setupDatabaseObserver() {
        viewModelScope.launch {
            dao.getAll().collect {
                GameLIstState.GameList(
                    it.map { entity -> entity.toModel() }
                    )

            }
        }
    }

    private fun retrieveGameList() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                gameLIstState.emit(GameLIstState.GameList(gameListProvider.getGameList()))
            } catch (error: Exception) {
                Log.e("GameList Fragment ", "Error retrieving games size : $error")
                gameLIstState.emit(GameLIstState.Error(error))
            }
        }
    }
}
