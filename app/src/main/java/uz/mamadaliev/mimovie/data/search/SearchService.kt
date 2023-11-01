package uz.mamadaliev.mimovie.data.search

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.mamadaliev.mimovie.BuildConfig
import uz.mamadaliev.mimovie.data.home.model.remote.response.movie.MoviesResponse

interface SearchService {
    @GET("search/movie")
    suspend fun getSearchMovies(
        @Query("api_key") closeReason: String = BuildConfig.TOKEN,
        @Query("query") query: String
    ): Response<MoviesResponse>
}