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
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

data class Dog(val message: String, val status: String)

interface DogService {
    @GET("api/breeds/image/random")
    suspend fun getDog():Dog

}

val retrofit = Retrofit.Builder()
    .baseUrl("https://dog.ceo/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
val dogService = retrofit.create(DogService::class.java)

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
                val dog = dogService.getDog()
               Picasso.get()
                   .load(dog.message)
                   .into(binding.dogImage)
                Log.d("Dog","$dog")
            } catch (e: Exception) {
                Log.e("DogFragment", "Error retrieving repos size :$e")
                Snackbar.make(
                    (binding.fragmentGit),
                    "Error retrieving repos",
                    Snackbar.LENGTH_LONG
                ).setAction("Retry") { retrievingRepos() }
                    .show()

            }
        }
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