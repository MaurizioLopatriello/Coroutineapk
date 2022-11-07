package com.android.example.coroutineapk

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater

import android.view.ViewGroup
import retrofit2.Call
import java.nio.file.Files.find


class RepoViewHolder(view: View):RecyclerView.ViewHolder(view){

val repoName : TextView
init {
    repoName = view.findViewById(R.id.list_Text_view)
}




}
data class Repo(val name : String , val id : String)
class ListAdapter(private val repos: List<Repo>) : RecyclerView.Adapter<RepoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val repoView = LayoutInflater.from(parent.context).inflate(R.layout.listitems,parent,false)
        return RepoViewHolder(repoView)

    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
       holder.repoName.text = "${repos[position].name}"
    }

    override fun getItemCount(): Int {
        return repos.size

    }
}