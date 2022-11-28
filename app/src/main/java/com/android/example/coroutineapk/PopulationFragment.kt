package com.android.example.coroutineapk

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.example.coroutineapk.databinding.FragmentPopulationBinding
import com.google.android.material.snackbar.Snackbar

data class Population(
    val online: Int,
    val playlists: List<Playlists>
) {
    data class Playlists(
        val population: Int,
        val name: String
    )
}

class PopulationFragment : Fragment() {
    private var _binding: FragmentPopulationBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var viewModel: PopulationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopulationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[PopulationViewModel::class.java]
        binding.reposBut.setOnClickListener {
            observePopulation()
            viewModel.retrievePopulation()
            binding.buttonBack.setOnClickListener {
                findNavController().navigate(R.id.action_populationFragment_to_home2)
            }
        }

    }

    private fun observePopulation() {
        viewModel.populationNumber.observe(viewLifecycleOwner) {
            showPopulation(it)
        }
        viewModel.error.observe(viewLifecycleOwner) {
            Snackbar.make(
                binding.populationFragment,
                "error retrieving population ",
                Snackbar.LENGTH_LONG
            ).setAction("Retry") { viewModel.retrievePopulation() }.show()

        }
    }

    private fun showPopulation(population: Population.Playlists) {
        Log.d("Population Fragment", "List of population received $population")
        binding.rankList.adapter = ListAdapter(population)
    }
}