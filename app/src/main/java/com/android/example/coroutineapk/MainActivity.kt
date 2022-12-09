package com.android.example.coroutineapk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.telecom.Call
import androidx.navigation.findNavController
import com.android.example.coroutineapk.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        }
    }
