package com.android.example.coroutineapk.gameList.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.example.coroutineapk.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


class MainActivity : AppCompatActivity() {
    private var count = 0
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.navigationBut.setOnClickListener {
            val intent = Intent(this, GameListActivity2::class.java)
            startActivity(intent)
        }

        binding.Button.setOnClickListener {

            runBlocking {
                timeDelay()
                count += 1
                binding.textView.text = "${binding.editText.text.toString()} ${count.toString()}"
                Log.v("textview", "${binding.editText.text.toString()}")
            }
        }
    }

    private suspend fun timeDelay() {
        delay(2000)
    }
}
