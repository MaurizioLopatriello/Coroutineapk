package com.android.example.coroutineapk

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.android.example.coroutineapk.databinding.FragmentGitHubBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.internal.threadFactory
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

data class Repo(val name: String, val id: String)

interface GitHubService {
    @GET("Ranks")
    suspend fun listRanks():List<Ranks>
}
val logging= HttpLoggingInterceptor()
val client = OkHttpClient.Builder()
    .addInterceptor(logging)
    .build()





val retrofit = Retrofit.Builder()
    .client(client)
    .baseUrl("https://rocket-league1.p.rapidapi.com/ranks/930226ec26174a988dff84898ee13ded")
    .addConverterFactory(GsonConverterFactory.create())
    .addHeader("User-Agent", "RapidAPI Playground")
    .addHeader("Accept-Encoding", "identity")
    .addHeader("X-RapidAPI-Key", "19741e6969msh20e36a4537859e6p181b91jsncff9edd55d1b")
    .addHeader("X-RapidAPI-Host", "rocket-league1.p.rapidapi.com")
    .build()
val gitHubService = retrofit.create(GitHubService::class.java)

class GitHubFragment : Fragment() {
    private var _binding: FragmentGitHubBinding? = null
    private val binding
        get() = _binding!!
    private var count = 0
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGitHubBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        binding.buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_gitHubFragment_to_home2)


        }

        binding.reposBut.setOnClickListener {
            retrievingRepos()
        }
    }


    fun retrievingRepos() {
        lifecycleScope.launch {
            try {
                val repos = gitHubService.listRepos("MaurizioLopatriello")
                showRepos(repos)
                Log.d("GitHub","$repos")
            } catch (e: Exception) {
                Log.e("GitHubFragment", "Error retrieving repos size :$e")
                Snackbar.make(
                    (binding.fragmentGit),
                    "Error retrieving repos",
                    Snackbar.LENGTH_LONG
                ).setAction("Retry") { retrievingRepos() }
                    .show()

            }
        }
    }





    fun showRepos(repos:List <Repo>){
        Log.d("GitHubFragment","List of repos received ${repos.size}")
        binding.repoList.adapter = ListAdapter(repos)



    }


    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GitHubFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}