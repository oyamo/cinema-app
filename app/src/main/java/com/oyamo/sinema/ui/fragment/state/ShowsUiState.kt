package com.oyamo.sinema.ui.fragment.state

import com.oyamo.sinema.data.entity.MovieWrapper

data class ShowsUiState (
    val popularMovies: MovieWrapper? = null,
    val fetchingError: String = ""
)