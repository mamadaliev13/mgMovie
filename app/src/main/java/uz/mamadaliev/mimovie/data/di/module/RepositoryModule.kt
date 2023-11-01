package uz.mamadaliev.mimovie.data.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.mamadaliev.mimovie.data.actors.ActorsRepositoryImpl
import uz.mamadaliev.mimovie.data.actors.model.ActorsService
import uz.mamadaliev.mimovie.data.actors_list.ActorsListRepositoryImpl
import uz.mamadaliev.mimovie.data.genre.GenreRepositoryImpl
import uz.mamadaliev.mimovie.data.genre.GenreService
import uz.mamadaliev.mimovie.data.home.HomeRepositoryImpl
import uz.mamadaliev.mimovie.data.home.HomeService
import uz.mamadaliev.mimovie.domain.home.HomeRepository
import uz.mamadaliev.mimovie.domain.movie_detail.MovieDetailRepository
import uz.mamadaliev.mimovie.data.movie_detail.MovieDetailRepositoryImpl
import uz.mamadaliev.mimovie.data.movie_detail.model.MovieDetailService
import uz.mamadaliev.mimovie.domain.actors.ActorsListRepository
import uz.mamadaliev.mimovie.domain.actors.ActorsRepository
import uz.mamadaliev.mimovie.domain.genre.GenreRepository
import uz.mamadaliev.mimovie.data.actors_list.model.ActorsListService
import uz.mamadaliev.mimovie.data.search.SearchRepositoryImpl
import uz.mamadaliev.mimovie.data.search.SearchService
import uz.mamadaliev.mimovie.data.season_details.SeasonDetailRepoImpl
import uz.mamadaliev.mimovie.data.season_details.SeasonDetailService
import uz.mamadaliev.mimovie.data.tv_shows.TVShowsRepositoryImpl
import uz.mamadaliev.mimovie.data.tv_shows.TVShowsService
import uz.mamadaliev.mimovie.data.tv_show_details.TVShowDetailRepositoryImpl
import uz.mamadaliev.mimovie.data.tv_show_details.TVShowDetailService
import uz.mamadaliev.mimovie.domain.search.SearchRepository
import uz.mamadaliev.mimovie.domain.season_details.SeasonDetailRepository
import uz.mamadaliev.mimovie.domain.tv_shows.TVShowsRepository
import uz.mamadaliev.mimovie.domain.tv_show_details.TVShowDetailRepository

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideHomeRepository(mainService: HomeService): HomeRepository {
        return HomeRepositoryImpl(mainService)
    }

    @Provides
    fun provideMovieDetailRepository(mainService: MovieDetailService): MovieDetailRepository {
        return MovieDetailRepositoryImpl(mainService)
    }

    @Provides
    fun provideGenreRepository(mainService: GenreService): GenreRepository {
        return GenreRepositoryImpl(mainService)
    }

    @Provides
    fun provideActorRepository(mainService: ActorsService): ActorsRepository {
        return ActorsRepositoryImpl(mainService)
    }

    @Provides
    fun provideActorListRepository(mainService: ActorsListService): ActorsListRepository {
        return ActorsListRepositoryImpl(mainService)
    }

    @Provides
    fun provideSearchRepository(mainService: SearchService): SearchRepository {
        return SearchRepositoryImpl(mainService)
    }

    @Provides
    fun provideTVShowsRepository(mainService: TVShowsService): TVShowsRepository {
        return TVShowsRepositoryImpl(mainService)
    }

    @Provides
    fun provideTVShowsDetailsRepository(mainService: TVShowDetailService): TVShowDetailRepository {
        return TVShowDetailRepositoryImpl(mainService)
    }

    @Provides
    fun provideSeasonDetailRepository(mainService: SeasonDetailService): SeasonDetailRepository {
        return SeasonDetailRepoImpl(mainService)
    }
}