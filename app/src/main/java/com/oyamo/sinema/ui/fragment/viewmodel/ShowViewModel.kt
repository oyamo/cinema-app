package com.oyamo.sinema.ui.fragment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oyamo.sinema.data.remote.NetRepositoryImpl
import com.oyamo.sinema.ui.fragment.state.HomeUiState
import com.oyamo.sinema.ui.fragment.state.ShowsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowViewModel @Inject constructor(private val netRepo: NetRepositoryImpl): ViewModel() {
    private val _uiState = MutableStateFlow(ShowsUiState())
    val uiState: StateFlow<ShowsUiState> =_uiState.asStateFlow()

    private var fetchPopularJob: Job? = null

    fun fetchPopular() {
        fetchPopularJob?.cancel()
        fetchPopularJob = viewModelScope.launch {
            val moviesResult = netRepo.getPopularMovies()
            val movies = moviesResult.getOrNull()
            val error = moviesResult.exceptionOrNull()
            if(moviesResult.isSuccess) {
                _uiState.update {
                    it.copy(popularMovies = movies?.data)
                }
            } else {
                _uiState.update {
                    it.copy(popularMovies = null, fetchingError = error?.localizedMessage ?:"")
                }
            }
        }
    }
}