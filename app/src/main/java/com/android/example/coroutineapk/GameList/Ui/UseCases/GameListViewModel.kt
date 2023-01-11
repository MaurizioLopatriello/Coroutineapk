package com.android.example.coroutineapk.GameList.Ui.UseCases

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.*
import com.android.example.coroutineapk.GameList.Network.DTO.ApiResponse
import com.android.example.coroutineapk.GameList.Network.GameListDao
import com.android.example.coroutineapk.GameList.Network.GameListProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

const val KEY_FIRST_TIME_USER = "first_time_user"

sealed class GameLIstState {
    data class GameList(val list: List<ApiResponse.ApiResponseItem>) : GameLIstState()
    data class Error(val error: Exception) : GameLIstState()
    object FirstTimeUser : GameLIstState()
}

sealed class GameListEvent {
    object GetGameList : GameListEvent()

}

class GameListViewModel(
    private val gameListProvider: GameListProvider,
    preferences: SharedPreferences,
    gameListDao: GameListDao
) : ViewModel() {

    /* private var _gameListNumber = MutableLiveData<List<ApiResponse.ApiResponseItem>>()
     val gameListNumber:
             LiveData<List<ApiResponse.ApiResponseItem>>
         get() = _gameListNumber

   /*  private var _error = MutableLiveData<Exception>()
     //val error: LiveData<Exception>
         get() = _error

    */

     */

    fun send(event: GameListEvent) {
        when (event) {
            GameListEvent.GetGameList -> retrieveGameList()
        }
    }

     val gameLIstState = MutableSharedFlow<GameLIstState>()


    init {
        checkFirstTimeUser(preferences)
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
