package com.oyamo.sinema.ui.fragment.adapter

import android.graphics.Movie
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oyamo.sinema.data.entity.MovieItem
import com.oyamo.sinema.databinding.LayoutMovieBinding
import com.oyamo.sinema.databinding.LayoutTrendingBinding

class ShowsAdapter(): RecyclerView.Adapter<ShowsViewHolder>() {

    private val movieItems: MutableList<MovieItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowsViewHolder {
        return ShowsViewHolder(
            LayoutMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ShowsViewHolder, position: Int) {
        holder.bind(movieItems[position])
    }

    override fun getItemCount(): Int {
        return movieItems.size
    }

    fun updateMovies(movies: List<MovieItem>) {
        if (movies.isEmpty()) return
        movieItems.clear()
        movieItems.addAll(movies)
        notifyDataSetChanged()
    }
}