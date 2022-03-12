package com.oyamo.sinema.ui.fragment.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oyamo.sinema.R
import com.oyamo.sinema.data.entity.MovieItem
import com.oyamo.sinema.databinding.LayoutMovieBinding
import com.oyamo.sinema.databinding.LayoutTrendingBinding

class ShowsViewHolder(private val binding: LayoutMovieBinding) : RecyclerView.ViewHolder(binding.root){
    @SuppressLint("SetTextI18n")
    fun bind(movie: MovieItem) {
        binding.tvMovieName.text = movie.title
        binding.tvYear.text = "(${movie.year})"
        Glide.with(binding.root)
            .load(movie.image)
            .centerCrop()
            .placeholder(R.drawable.starwars)
            .into(binding.ivMovie)
    }
}