package uz.mamadaliev.mimovie.data.movie_detail

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.mamadaliev.mimovie.data.base.BaseNetworkResult
import uz.mamadaliev.mimovie.data.movie_detail.model.MovieDetailService
import uz.mamadaliev.mimovie.data.movie_detail.model.remote.response.Credits
import uz.mamadaliev.mimovie.data.movie_detail.model.remote.response.MovieDetailResponse
import uz.mamadaliev.mimovie.data.movie_detail.model.remote.response.MovieTrailerRes
import uz.mamadaliev.mimovie.data.movie_detail.model.remote.response.Review
import uz.mamadaliev.mimovie.data.movie_detail.model.remote.similar.SimilarMovies
import uz.mamadaliev.mimovie.domain.movie_detail.MovieDetailRepository
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(private val service: MovieDetailService) :
    MovieDetailRepository {

    override suspend fun getMovieDetailById(id: Long): Flow<BaseNetworkResult<MovieDetailResponse>> {
        return flow {
            emit(BaseNetworkResult.Loading(true))
            val response = service.getMovieDetailById(id)
            emit(BaseNetworkResult.Loading(false))
            if (response.code() == 200) {
                emit(BaseNetworkResult.Success(response.body()!!))
            } else {
                emit(BaseNetworkResult.Error("Xatolik"))
            }
        }
    }

    override suspend fun getMovieTrailerListById(id: Long): Flow<BaseNetworkResult<MovieTrailerRes>> {
        return flow {
            emit(BaseNetworkResult.Loading(true))
            val response = service.getMovieTrailerListById(id)
            emit(BaseNetworkResult.Loading(false))
            if (response.code() == 200) {
                emit(BaseNetworkResult.Success(response.body() ?: MovieTrailerRes(0, null)))
            } else {
                emit(BaseNetworkResult.Error("Xatolik"))
            }
        }
    }

    override suspend fun getReviews(id: Long): Flow<BaseNetworkResult<Review>> {
        return flow {
            emit(BaseNetworkResult.Loading(true))
            val response = service.getReviews(id)
            emit(BaseNetworkResult.Loading(false))
            if (response.code() == 200) {
                emit(BaseNetworkResult.Success(response.body()!!))
            } else {
                emit(BaseNetworkResult.Error("Xatolik"))
            }
        }
    }

    override suspend fun getCredits(id: Long): Flow<BaseNetworkResult<Credits>> {
        return flow {
            emit(BaseNetworkResult.Loading(true))
            val response = service.getCredits(id)
            emit(BaseNetworkResult.Loading(false))
            if (response.code() == 200) {
                emit(BaseNetworkResult.Success(response.body() ?: Credits(0, null, null)))
            } else {
                emit(BaseNetworkResult.Error("Xatolik"))
            }
        }
    }

    override suspend fun getSimilarFilms(id: Long): Flow<BaseNetworkResult<SimilarMovies>> {
        return flow {
            emit(BaseNetworkResult.Loading(true))
            val response = service.getSimilarFilms(id)
            emit(BaseNetworkResult.Loading(false))
            if (response.code() == 200) {
                emit(BaseNetworkResult.Success(response.body() ?: SimilarMovies(0, emptyList(),0,0)))
            } else {
                emit(BaseNetworkResult.Error("Xatolik"))
            }
        }
    }

}