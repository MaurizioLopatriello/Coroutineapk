package com.android.example.coroutineapk.gameList.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.android.example.coroutineapk.gameList.network.dto.ApiResponse
import com.android.example.coroutineapk.gameList.ui.usecases.GameLIstState
import com.android.example.coroutineapk.gameList.ui.usecases.GameListEvent
import com.android.example.coroutineapk.gameList.ui.usecases.GameListViewModel
import com.android.example.coroutineapk.gameList.ui.usecases.MyAppArchitecture
import com.android.example.coroutineapk.ListAdapter
import com.android.example.coroutineapk.databinding.ActivityGameList2Binding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch


class GameListActivity2 : AppCompatActivity() {
    private lateinit var viewModel: GameListViewModel
    private lateinit var binding: ActivityGameList2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameList2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel =
            (application as MyAppArchitecture).gameListViewModelFactory.create(GameListViewModel::class.java)

        binding.reposBut.setOnClickListener {
            setupObserver(it)
            viewModel.send(GameListEvent.GetGameList)

            binding.buttonBack.setOnClickListener {
                val intent2 = Intent(this, MainActivity::class.java)
                startActivity(intent2)
            }
        }
    }

    private fun setupObserver(view: View) {
        lifecycleScope.launch {
            viewModel.gameLIstState.collect { state ->
                when (state) {
                    is GameLIstState.GameList -> {
                        showGameList(state.list)
                    }
                    GameLIstState.FirstTimeUser -> {
                    }
                    is GameLIstState.Error -> {
                        Snackbar.make(
                            binding.gameListActivity,
                            "error retrieving gamelist ",
                            Snackbar.LENGTH_LONG
                        ).setAction("Retry") { viewModel.send(GameListEvent.GetGameList) }.show()
                    }
                }
            }
        }
    }

    private fun showGameList(gameList: List<ApiResponse.ApiResponseItem>) {
        Log.d("GameList Fragment", "List of games received $gameList")
        binding.rankList.adapter = ListAdapter(gameList)
    }
}



