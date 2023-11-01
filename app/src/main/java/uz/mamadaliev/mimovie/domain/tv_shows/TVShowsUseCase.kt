package uz.mamadaliev.mimovie.domain.tv_shows

import kotlinx.coroutines.flow.Flow
import uz.mamadaliev.mimovie.data.base.BaseNetworkResult
import uz.mamadaliev.mimovie.data.tv_shows.models.TVShowsResponse
import javax.inject.Inject

class TVShowsUseCase @Inject constructor(private val homeRepository: TVShowsRepository) {
    suspend fun getAllTopRatedTVShows(): Flow<BaseNetworkResult<TVShowsResponse>> {
        return homeRepository.getAllTopRatedTVShows()
    }

    suspend fun getPopularTVShows(): Flow<BaseNetworkResult<TVShowsResponse>> {
        return homeRepository.getPopularTVShows()
    }

    suspend fun getOnTheAirTVShows(): Flow<BaseNetworkResult<TVShowsResponse>> {
        return homeRepository.getOnTheAirTVShows()
    }

    suspend fun getAiringTodayTVShows(): Flow<BaseNetworkResult<TVShowsResponse>> {
        return homeRepository.getAiringTodayTVShows()
    }

    suspend fun getSearchedTVShows(query: String): Flow<BaseNetworkResult<TVShowsResponse>> {
        return homeRepository.getSearchedTVShows(query)
    }
}