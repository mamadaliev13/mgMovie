package uz.mamadaliev.mimovie.domain.genre

import kotlinx.coroutines.flow.Flow
import uz.mamadaliev.mimovie.data.base.BaseNetworkResult
import uz.mamadaliev.mimovie.data.home.model.local.MovieResultDto
import uz.mamadaliev.mimovie.data.genre.remote.genres.MoviesGenresResponse
import uz.mamadaliev.mimovie.data.tv_shows.models.TVShowsResponse

interface GenreRepository {
    suspend fun getAllMoviesGenres(): Flow<BaseNetworkResult<MoviesGenresResponse>>

    suspend fun getAllTVShowGenres(): Flow<BaseNetworkResult<MoviesGenresResponse>>

    suspend fun getMoviesByGenre(id: Int): Flow<BaseNetworkResult<List<MovieResultDto>>>

    suspend fun getTVShowsByGenre(id: Int): Flow<BaseNetworkResult<TVShowsResponse>>
}