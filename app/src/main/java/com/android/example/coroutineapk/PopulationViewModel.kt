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

class PopulationViewModel : ViewModel() {

    private val logging = HttpLoggingInterceptor()
    private val authorization = AuthorizationInterceptor()
    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor(authorization)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://rocket-league1.p.rapidapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val populationService: PopulationService =
        retrofit.create(PopulationService::class.java)

    private var _populationNumber = MutableLiveData<Population.Playlists>()
    val populationNumber: LiveData<Population.Playlists>
        get() = _populationNumber

    private var _error = MutableLiveData<Exception>()
    val error: LiveData<Exception>
        get() = _error

    init {
        logging.level = HttpLoggingInterceptor.Level.BASIC
    }

    fun retrievePopulation() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                _populationNumber.value = populationService.listPopulation()
            } catch (error: Exception) {
                Log.e("Population Fragment ", "Error retrieving ranks size : $error")
                _error.value = error

            }
        }
    }

}