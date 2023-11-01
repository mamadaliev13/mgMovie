package uz.mamadaliev.mimovie.data.season_details

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uz.mamadaliev.mimovie.BuildConfig
import uz.mamadaliev.mimovie.data.season_details.models.SeasonDetails

interface SeasonDetailService {

    @GET("tv/{tv_id}/season/{season_number}")
    suspend fun getSeasonDetails(
        @Path("tv_id") tv_id: Long,
        @Path("season_number") season_number: Int,
        @Query("api_key") closeReason: String = BuildConfig.TOKEN,
    ): Response<SeasonDetails>
}