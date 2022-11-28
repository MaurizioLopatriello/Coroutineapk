package com.android.example.coroutineapk

import retrofit2.http.GET

interface PopulationService {
    @GET("population")
    suspend fun listPopulation(): Population.Playlists
}
