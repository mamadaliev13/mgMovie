package uz.mamadaliev.mimovie.domain.movie_detail

import kotlinx.coroutines.flow.Flow
import uz.mamadaliev.mimovie.data.base.BaseNetworkResult
import uz.mamadaliev.mimovie.data.movie_detail.model.remote.response.*
import uz.mamadaliev.mimovie.data.movie_detail.model.remote.similar.SimilarMovies
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(private val homeRepository: MovieDetailRepository) {
    suspend fun getMovieDetailById(id: Long): Flow<BaseNetworkResult<MovieDetailResponse>> {
        return homeRepository.getMovieDetailById(id)
    }

    suspend fun getMovieTrailerListById(id: Long): Flow<BaseNetworkResult<MovieTrailerRes>> {
        return homeRepository.getMovieTrailerListById(id)
    }

    suspend fun getReviews(id: Long): Flow<BaseNetworkResult<Review>> {
        return homeRepository.getReviews(id)
    }

    suspend fun getSimilarFilms(id: Long): Flow<BaseNetworkResult<SimilarMovies>> {
        return homeRepository.getSimilarFilms(id)
    }

    suspend fun getCredits(id: Long): Flow<BaseNetworkResult<Credits>> {
        return homeRepository.getCredits(id)
    }
}