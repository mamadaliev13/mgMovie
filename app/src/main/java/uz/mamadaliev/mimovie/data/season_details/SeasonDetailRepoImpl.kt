package uz.mamadaliev.mimovie.data.season_details

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.mamadaliev.mimovie.data.base.BaseNetworkResult
import uz.mamadaliev.mimovie.data.season_details.models.SeasonDetails
import uz.mamadaliev.mimovie.domain.season_details.SeasonDetailRepository
import javax.inject.Inject

class SeasonDetailRepoImpl @Inject constructor(private val service: SeasonDetailService) :
    SeasonDetailRepository {

    override suspend fun getSeasonDetailById(
        tv_id: Long,
        season_number: Int,
    ): Flow<BaseNetworkResult<SeasonDetails>> {
        return flow {
            emit(BaseNetworkResult.Loading(true))
            val response = service.getSeasonDetails(tv_id, season_number)
            emit(BaseNetworkResult.Loading(false))
            if (response.code() == 200) {
                emit(BaseNetworkResult.Success(response.body()!!))
            } else {
                emit(BaseNetworkResult.Error("Xatolik"))
            }
        }
    }
}