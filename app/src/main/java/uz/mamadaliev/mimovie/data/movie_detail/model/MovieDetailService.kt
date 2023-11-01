package uz.mamadaliev.mimovie.data.movie_detail.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uz.mamadaliev.mimovie.BuildConfig
import uz.mamadaliev.mimovie.data.movie_detail.model.remote.response.Credits
import uz.mamadaliev.mimovie.data.movie_detail.model.remote.response.MovieDetailResponse
import uz.mamadaliev.mimovie.data.movie_detail.model.remote.response.MovieTrailerRes
import uz.mamadaliev.mimovie.data.movie_detail.model.remote.response.Review
import uz.mamadaliev.mimovie.data.movie_detail.model.remote.similar.SimilarMovies

interface MovieDetailService {
    @GET("movie/{movie_id}")
    suspend fun getMovieDetailById(
        @Path("movie_id") movie_id: Long,
        @Query("api_key") closeReason: String = BuildConfig.TOKEN,
    ): Response<MovieDetailResponse>

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieTrailerListById(
        @Path("movie_id") movie_id: Long,
        @Query("api_key") closeReason: String = BuildConfig.TOKEN,
    ): Response<MovieTrailerRes>

    @GET("movie/{movie_id}/credits")
    suspend fun getCredits(
        @Path("movie_id") movie_id: Long,
        @Query("api_key") closeReason: String = BuildConfig.TOKEN,
    ): Response<Credits>

    @GET("movie/{movie_id}/reviews")
    suspend fun getReviews(
        @Path("movie_id") movie_id: Long,
        @Query("api_key") closeReason: String = BuildConfig.TOKEN,
    ): Response<Review>

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarFilms(
        @Path("movie_id") movie_id: Long,
        @Query("api_key") closeReason: String = BuildConfig.TOKEN,
    ): Response<SimilarMovies>
}