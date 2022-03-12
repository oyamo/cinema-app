package com.oyamo.sinema.ui.fragment.state

import com.oyamo.sinema.data.entity.MovieWrapper

data class HomeUiState (
    val popularMovies: MovieWrapper? = null,
    val fetchingError: String = ""
)