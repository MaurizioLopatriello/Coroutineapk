package com.android.example.coroutineapk

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GameListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val gameListName: TextView
    init {
        gameListName = view.findViewById(R.id.list_Text_view)
    }
}

class ListAdapter(private val gameList: List<ApiResponse.ApiResponseItem>) :
    RecyclerView.Adapter<GameListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameListViewHolder {
        val gameListView =
            LayoutInflater.from(parent.context).inflate(R.layout.listitems, parent, false)
        return GameListViewHolder(gameListView)
    }

    override fun onBindViewHolder(holder: GameListViewHolder, position: Int) {
        holder.gameListName.text = gameList[position].title
    }

    override fun getItemCount(): Int {
        return gameList.size
    }
}





