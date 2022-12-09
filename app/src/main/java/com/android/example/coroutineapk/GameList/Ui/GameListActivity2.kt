package com.android.example.coroutineapk.GameList.Ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.example.coroutineapk.GameList.Network.DTO.ApiResponse
import com.android.example.coroutineapk.GameList.Ui.UseCases.GameListViewModel
import com.android.example.coroutineapk.ListAdapter
import com.android.example.coroutineapk.GameList.Ui.UseCases.MyAppArchitecture
import com.android.example.coroutineapk.databinding.ActivityGameList2Binding
import com.google.android.material.snackbar.Snackbar


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
            observeGameList(it)
            viewModel.retrieveGameList()

            binding.buttonBack.setOnClickListener {
                val intent2 = Intent(this, MainActivity::class.java)
                startActivity(intent2)
            }
        }
    }

    private fun observeGameList(view: View) {
        viewModel.gameListNumber.observe(this) {
            showGameList(it)

        }
        viewModel.error.observe(this) {
            Snackbar.make(
                binding.gameListActivity,
                "error retrieving gamelist ",
                Snackbar.LENGTH_LONG
            ).setAction("Retry") { viewModel.gameListNumber }.show()

        }
    }


    private fun showGameList(gameList: List<ApiResponse.ApiResponseItem>) {
        Log.d("GameList Fragment", "List of games received $gameList")
        binding.rankList.adapter = ListAdapter(gameList)
    }
}


