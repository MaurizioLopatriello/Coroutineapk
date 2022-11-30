package com.android.example.coroutineapk

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.android.example.coroutineapk.databinding.FragmentGameListBinding
import com.google.android.material.snackbar.Snackbar

class GameListFragment : Fragment() {

    private var _binding: FragmentGameListBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var viewModel: GameListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[GameListViewModel::class.java]
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
                "error retrieving population ",
                Snackbar.LENGTH_LONG
            ).setAction("Retry") { viewModel.gameListNumber }.show()

        }
    }

    private fun showGameList(gameList :List<ApiResponse.ApiResponseItem>) {
        Log.d("Population Fragment", "List of population received $gameList")
        binding.rankList.adapter = ListAdapter(gameList)
    }
}

    }

}