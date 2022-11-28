package com.android.example.coroutineapk

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater

import android.view.ViewGroup


class PopulationViewHolder(view: View):RecyclerView.ViewHolder(view){

val populationName : TextView
init {
    populationName = view.findViewById(R.id.list_Text_view)
}




}

class ListAdapter(private val population:Population.Playlists) : RecyclerView.Adapter<PopulationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopulationViewHolder {
        val rankView = LayoutInflater.from(parent.context).inflate(R.layout.listitems,parent,false)
        return PopulationViewHolder(rankView)

    }

    override fun onBindViewHolder(holder: PopulationViewHolder, position: Int) {
       holder.populationName.text = population.name
    }

    override fun getItemCount(): Int {
        return population.population

    }
}


