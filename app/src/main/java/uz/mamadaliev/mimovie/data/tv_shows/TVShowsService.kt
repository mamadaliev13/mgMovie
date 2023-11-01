package uz.mamadaliev.mimovie.data.tv_shows

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.mamadaliev.mimovie.BuildConfig
import uz.mamadaliev.mimovie.data.tv_shows.models.TVShowsResponse

interface TVShowsService {
    @GET("tv/top_rated")
    suspend fun getAllTopRatedTVShows(
        @Query("api_key") closeReason: String = BuildConfig.TOKEN,
    ): Response<TVShowsResponse>

    @GET("tv/popular")
    suspend fun getAllPopularTVShows(
        @Query("api_key") closeReason: String = BuildConfig.TOKEN,
    ): Response<TVShowsResponse>

    @GET("tv/on_the_air")
    suspend fun getOnTheAirTVShows(
        @Query("api_key") closeReason: String = BuildConfig.TOKEN,
    ): Response<TVShowsResponse>

    @GET("tv/airing_today")
    suspend fun getAiringTodayTVShows(
        @Query("api_key") closeReason: String = BuildConfig.TOKEN,
    ): Response<TVShowsResponse>

    @GET("search/tv")
    suspend fun getSearchedTVShows(
        @Query("api_key") closeReason: String = BuildConfig.TOKEN,
        @Query("query") query: String
    ): Response<TVShowsResponse>
}