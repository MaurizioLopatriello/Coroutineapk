package com.android.example.coroutineapk.GameList.Ui.UseCases

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.example.coroutineapk.GameList.Network.DTO.ApiResponse
import com.android.example.coroutineapk.GameList.Network.GameListProvider
import com.android.example.coroutineapk.GameList.Network.GameListService
import com.android.example.coroutineapk.GameList.Ui.Model.GameListRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameListViewModel(private val gameListProvider: GameListProvider) : ViewModel() {
    private var _gameListNumber = MutableLiveData <List<ApiResponse.ApiResponseItem>>()
    val gameListNumber:
            LiveData<List<ApiResponse.ApiResponseItem>>
        get() = _gameListNumber

    private var _error = MutableLiveData<Exception>()
    val error: LiveData<Exception>
        get() = _error


    fun retrieveGameList() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                _gameListNumber.value = gameListProvider.provide()
            } catch (error: Exception) {
                Log.e("GameList Fragment ", "Error retrieving games size : $error")
                _error.value = error


            }
        }
    }

}