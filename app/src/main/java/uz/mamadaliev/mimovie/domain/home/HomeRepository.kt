package uz.mamadaliev.mimovie.domain.home

import kotlinx.coroutines.flow.Flow
import uz.mamadaliev.mimovie.data.base.BaseNetworkResult
import uz.mamadaliev.mimovie.data.home.model.local.MovieResultDto

interface HomeRepository {
    suspend fun getAllNewMovies(): Flow<BaseNetworkResult<List<MovieResultDto>>>

    suspend fun getAllPopularMovies(): Flow<BaseNetworkResult<List<MovieResultDto>>>

    suspend fun getAllTopRatedMovies(): Flow<BaseNetworkResult<List<MovieResultDto>>>

    suspend fun getAllUpcomingMovies(): Flow<BaseNetworkResult<List<MovieResultDto>>>
}