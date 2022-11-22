package com.android.example.coroutineapk

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
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




interface FreeGamesService {
    @GET("api/games")
   // @GET("ranks/{player}/ranks")
    suspend fun getGameList():ApiResponse
}


const val API_AUTHORIZATION_HEADER = "x-rapidapi-key"

class RankFragment : Fragment() {
    private var _binding: FragmentRankBinding? = null
    private val binding
        get() = _binding!!
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

    private val gameListService: FreeGamesService = retrofit.create(FreeGamesService::class.java)

    class AuthorizationInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()

            val newRequest = request.newBuilder().addHeader(
                API_AUTHORIZATION_HEADER,
                "19741e6969msh20e36a4537859e6p181b91jsncff9edd55d1b"
            ).build()

            return chain.proceed(newRequest)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRankBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logging.level = HttpLoggingInterceptor.Level.BASIC
        binding.buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_rankFragment_to_home2)


        }
        binding.reposBut.setOnClickListener {
            retrieveGameList()
        }
    }

    private fun retrieveGameList() {
        lifecycleScope.launch {
            try {
                val gameList = gameListService.getGameList()
                showGameList(gameList)
                Log.d("RankFragment", "$gameList")
            } catch (e: Exception) {
                Log.e("RankFragment", "Error retrieving ranks size : $e")
                Snackbar.make(
                    (binding.RankFragment),
                    "Error retrieving ranks",
                    Snackbar.LENGTH_LONG
                ).setAction("Retry") { retrieveGameList() }.show()

            }

        }
    }

    private fun showGameList(population:List<ApiResponse.ApiResponseItem>) {
        Log.d("RankFragment", "List of population received $population")
        binding.rankList.adapter = ListAdapter(population)
    }


}
