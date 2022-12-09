package com.android.example.coroutineapk

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameListViewModel(private val gameListService: GameListService) : ViewModel() {
    private var _gameListNumber = MutableLiveData<List<ApiResponse.ApiResponseItem>>()
    val gameListNumber:
            LiveData<List<ApiResponse.ApiResponseItem>>
        get() = _gameListNumber

    private var _error = MutableLiveData<Exception>()
    val error: LiveData<Exception>
        get() = _error


    fun retrieveGameList() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                _gameListNumber.value = gameListService.getGameList()
            } catch (error: Exception) {
                Log.e("GameList Fragment ", "Error retrieving games size : $error")
                _error.value = error


            }
        }
    }

}