package com.android.example.coroutineapk

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.fragment.findNavController
import com.android.example.coroutineapk.databinding.FragmentGameListBinding
import com.google.android.material.snackbar.Snackbar

class GameListFragment : Fragment( ) {
    private lateinit var viewModel: GameListViewModel
    private lateinit var gameListProvider: GameListProvider

    private var _binding: FragmentGameListBinding? = null
    private val binding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameListProvider = (parentFragment as MyAppArchitecture).gameListProvider
        viewModel=ViewModelProvider(this)[GameListViewModel::class.java]
        viewModel = GameListViewModel(gameListProvider)

        binding.reposBut.setOnClickListener {
            observeGameList()
            viewModel.retrieveGameList()
            binding.buttonBack.setOnClickListener {
                findNavController().navigate(R.id.action_gameListFragment2_to_home2)
            }
        }

    }

    private fun observeGameList() {
        viewModel.gameListNumber.observe(viewLifecycleOwner) {
            showGameList(it)

        }
        viewModel.error.observe(viewLifecycleOwner) {
            Snackbar.make(
                binding.gameListFragment,
                "error retrieving game list ",
                Snackbar.LENGTH_LONG
            ).setAction("Retry") { viewModel.gameListNumber }.show()

        }
    }



    private fun showGameList(gameList:List<ApiResponse.ApiResponseItem>) {
        Log.d("GameList Fragment", "List of games received $gameList")
        binding.rankList.adapter = ListAdapter(gameList)
    }
}



