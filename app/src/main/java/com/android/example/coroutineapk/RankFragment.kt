package com.android.example.coroutineapk

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.android.example.coroutineapk.databinding.FragmentRankBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


class RankFragment : Fragment() {
    private var _binding: FragmentRankBinding? = null
    private val binding
        get() = _binding!!





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRankBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }}

 /*   private fun retrievePopulation2() {
        lifecycleScope.launch {
            try {
                val population = populationService.listPopulation()

                Log.d("RankFragment", "")
            } catch (e: Exception) {
                Log.e("RankFragment", "Error retrieving ranks size : $e")
                Snackbar.make(
                    (binding.RankFragment),
                    "Error retrieving ranks",
                    Snackbar.LENGTH_LONG
                ).setAction("Retry") { retrievePopulation2() }.show()

            }

        }
    }

    private fun showPopulation2(population:List<Population.Playlists>) {
        Log.d("RankFragment", "List of population received $population")

    }*/


