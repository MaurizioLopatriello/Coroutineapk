package com.android.example.coroutineapk

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.E

class GameListViewModel : ViewModel() {


        private val logging = HttpLoggingInterceptor()
        private val authorization = AuthorizationInterceptor()
        private val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(authorization)
            .build()

        private val retrofit: Retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl("https://free-to-play-games-database.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        private val gameListService: GameListService =
            retrofit.create(GameListService::class.java)

        private var _gameListNumber = MutableLiveData<List<ApiResponse.ApiResponseItem>>()
        val gameListNumber:
                LiveData<List<ApiResponse.ApiResponseItem>>
            get() = _gameListNumber

        private var _error = MutableLiveData<Exception>()
        val error: LiveData<Exception>
            get() = _error

        init {
            logging.level = HttpLoggingInterceptor.Level.BASIC
        }

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