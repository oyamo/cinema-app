package com.oyamo.sinema.ui.fragment.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oyamo.sinema.R
import com.oyamo.sinema.data.entity.MovieItem
import com.oyamo.sinema.databinding.LayoutTrendingBinding

class TrendingViewHolder(private val binding: LayoutTrendingBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(movie: MovieItem) {
        binding.tvMovieName.text = movie.title
        binding.tvMovieRating.text = "${movie.imDbRating}"
        Glide.with(binding.root)
            .load(movie.image)
            .centerCrop()
            .placeholder(R.drawable.starwars)
            .into(binding.ivTrending)
    }
}