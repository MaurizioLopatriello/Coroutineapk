package com.android.example.coroutineapk

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch

class GameListViewModel(private val gameListService: GameListProvider) :ViewModel() {
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
                _gameListNumber.value = gameListService.provide()
            } catch (error: Exception) {
                Log.e("GameList Fragment ", "Error retrieving games size : $error")
                _error.value = error


            }
        }
    }

}