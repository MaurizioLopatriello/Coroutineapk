package com.android.example.coroutineapk

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater

import android.view.ViewGroup
import com.android.example.coroutineapk.gameList.network.dto.ApiResponse


class PopulationViewHolder(view: View):RecyclerView.ViewHolder(view){

val gameListName : TextView

init {
    gameListName = view.findViewById(R.id.list_Text_view)

}




}

class ListAdapter(private val gameList:List <ApiResponse.ApiResponseItem>) : RecyclerView.Adapter<PopulationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopulationViewHolder {
        val rankView = LayoutInflater.from(parent.context).inflate(R.layout.listitems,parent,false)
        return PopulationViewHolder(rankView)

    }

    override fun onBindViewHolder(holder: PopulationViewHolder, position: Int) {
       holder.gameListName.text = gameList[position].title

    }

    override fun getItemCount(): Int {
        return gameList.size

    }
}





