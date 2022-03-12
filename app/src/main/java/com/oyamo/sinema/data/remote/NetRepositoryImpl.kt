package com.oyamo.sinema.data.remote

import com.oyamo.sinema.data.entity.MovieWrapper
import io.ktor.client.request.*
import javax.inject.Inject

class NetRepositoryImpl @Inject constructor(
    val netclient: NetClient
) : NetRepository {

    override suspend fun searchExpression(query: String): Result<NetResponse<Any>> {
        return Result.success(NetResponse(10))
    }

    override suspend fun getPopularTvs(): Result<NetResponse<Any>> {
        return try {
            val popularMovies =
                netclient.CLIENT.get<MovieWrapper>("${NetClient.BASE_URL}/MostPopularTVs/${NetClient.API_KEY}")
            Result.success(NetResponse(200, null, popularMovies))
        } catch (e: NetException) {
            Result.failure(e)
        }
    }

    override suspend fun getPopularMovies(): Result<NetResponse<MovieWrapper>> {
        return try {
            val popularMovies =
                netclient.CLIENT.get<MovieWrapper>("${NetClient.BASE_URL}/MostPopularMovies/${NetClient.API_KEY}")
            Result.success(NetResponse(200, null, popularMovies))
        } catch (e: NetException) {
            Result.failure(e)
        }
    }

    override suspend fun getBoxOffice(): Result<NetResponse<Any>> {
        return Result.success(NetResponse(10))
    }

    override suspend fun getInTheaters(): Result<NetResponse<Any>> {
        return Result.success(NetResponse(10))
    }

    override suspend fun getMovieDetails(): Result<NetResponse<Any>> {
        return Result.success(NetResponse(10))
    }

    override suspend fun getMoviePosters(): Result<NetResponse<Any>> {
        return Result.success(NetResponse(10))
    }

    override suspend fun getMovieImages(): Result<NetResponse<Any>> {
        return Result.success(NetResponse(10))
    }
}