package uz.mamadaliev.mimovie.domain.genre

import kotlinx.coroutines.flow.Flow
import uz.mamadaliev.mimovie.data.base.BaseNetworkResult
import uz.mamadaliev.mimovie.data.home.model.local.MovieResultDto
import uz.mamadaliev.mimovie.data.genre.remote.genres.MoviesGenresResponse
import uz.mamadaliev.mimovie.data.tv_shows.models.TVShowsResponse
import javax.inject.Inject

class GenreUseCase @Inject constructor(private val homeRepository: GenreRepository) {
    suspend fun getAllMoviesGenres(): Flow<BaseNetworkResult<MoviesGenresResponse>> {
        return homeRepository.getAllMoviesGenres()
    }

    suspend fun getAllTVShowGenres(): Flow<BaseNetworkResult<MoviesGenresResponse>> {
        return homeRepository.getAllTVShowGenres()
    }

    suspend fun getMoviesByGenre(id: Int): Flow<BaseNetworkResult<List<MovieResultDto>>> {
        return homeRepository.getMoviesByGenre(id)
    }

    suspend fun getTVShowsByGenre(id: Int): Flow<BaseNetworkResult<TVShowsResponse>> {
        return homeRepository.getTVShowsByGenre(id)
    }
}