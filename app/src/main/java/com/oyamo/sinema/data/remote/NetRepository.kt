package com.oyamo.sinema.data.remote

import com.oyamo.sinema.data.entity.MovieWrapper

interface NetRepository {
    // Search
    suspend fun searchExpression(query: String): Result<NetResponse<Any>>

    // Popular
    suspend fun getPopularTvs(): Result<NetResponse<Any>>
    suspend fun getPopularMovies(): Result<NetResponse<MovieWrapper>>
    suspend fun getBoxOffice(): Result<NetResponse<Any>>
    suspend fun getInTheaters(): Result<NetResponse<Any>>

    // Single Movie Details
    suspend fun getMovieDetails(): Result<NetResponse<Any>>
    suspend fun getMoviePosters(): Result<NetResponse<Any>>
    suspend fun getMovieImages(): Result<NetResponse<Any>>
}