package com.android.example.coroutineapk

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.example.coroutineapk.databinding.FragmentHomeBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking




class Home : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!
    private var count = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)




        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.navigationBut.setOnClickListener {
            findNavController().navigate(R.id.action_home2_to_gameListFragment2)
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