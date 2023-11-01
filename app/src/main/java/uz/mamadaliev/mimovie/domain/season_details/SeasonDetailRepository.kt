package uz.mamadaliev.mimovie.domain.season_details

import kotlinx.coroutines.flow.Flow
import uz.mamadaliev.mimovie.data.base.BaseNetworkResult
import uz.mamadaliev.mimovie.data.season_details.models.SeasonDetails

interface SeasonDetailRepository {
    suspend fun getSeasonDetailById(
        tv_id: Long,
        season_number: Int
    ): Flow<BaseNetworkResult<SeasonDetails>>
}