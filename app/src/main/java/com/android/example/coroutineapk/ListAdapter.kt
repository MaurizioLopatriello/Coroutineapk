package com.android.example.coroutineapk

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater

import android.view.ViewGroup
import android.widget.ImageView


class PopulationViewHolder(view: View):RecyclerView.ViewHolder(view){

val populationName : TextView
val gameImage : ImageView
init {
    populationName = view.findViewById(R.id.list_Text_view)
    gameImage = view.findViewById(R.id.imageView)
}




}

class ListAdapter(private val population: List<ApiResponse.ApiResponseItem>) : RecyclerView.Adapter<PopulationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopulationViewHolder {
        val rankView = LayoutInflater.from(parent.context).inflate(R.layout.listitems,parent,false)
        return PopulationViewHolder(rankView)

    }

    override fun onBindViewHolder(holder: PopulationViewHolder, position: Int) {
        val games =population[position]
       holder.populationName.text = population[position].title
        holder.gameImage.setImageDrawable(holder.itemView.context.getDrawable(games.thumbnail))
    }

    override fun getItemCount(): Int {
        return population.size

    }
}





