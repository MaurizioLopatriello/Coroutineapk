package com.android.example.coroutineapk

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater

import android.view.ViewGroup
import retrofit2.Call
import java.nio.file.Files.find


class RankViewHolder(view: View):RecyclerView.ViewHolder(view){

val rankName : TextView
init {
    rankName = view.findViewById(R.id.list_Text_view)
}




}

class ListAdapter(private val ranks: List<Ranks.Rank>) : RecyclerView.Adapter<RankViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankViewHolder {
        val rankView = LayoutInflater.from(parent.context).inflate(R.layout.listitems,parent,false)
        return RankViewHolder(rankView)

    }

    override fun onBindViewHolder(holder: RankViewHolder, position: Int) {
       //holder.rankName.text = "${ranks}"
    }

    override fun getItemCount(): Int {
        return ranks.size

    }
}


