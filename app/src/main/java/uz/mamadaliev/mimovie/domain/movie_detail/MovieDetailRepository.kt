package uz.mamadaliev.mimovie.domain.movie_detail

import kotlinx.coroutines.flow.Flow
import uz.mamadaliev.mimovie.data.base.BaseNetworkResult
import uz.mamadaliev.mimovie.data.movie_detail.model.remote.response.*
import uz.mamadaliev.mimovie.data.movie_detail.model.remote.similar.SimilarMovies

interface MovieDetailRepository {
    suspend fun getMovieDetailById(id: Long): Flow<BaseNetworkResult<MovieDetailResponse>>

    suspend fun getMovieTrailerListById(id: Long): Flow<BaseNetworkResult<MovieTrailerRes>>

    suspend fun getReviews(id: Long): Flow<BaseNetworkResult<Review>>

    suspend fun getSimilarFilms(id: Long): Flow<BaseNetworkResult<SimilarMovies>>

    suspend fun getCredits(id: Long): Flow<BaseNetworkResult<Credits>>
}