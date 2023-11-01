package uz.mamadaliev.mimovie.presentation.movie_detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import uz.mamadaliev.mimovie.data.base.BaseNetworkResult
import uz.mamadaliev.mimovie.data.home.model.local.MovieResultDto
import uz.mamadaliev.mimovie.data.movie_detail.model.remote.response.Credits
import uz.mamadaliev.mimovie.data.movie_detail.model.remote.response.MovieDetailResponse
import uz.mamadaliev.mimovie.data.movie_detail.model.remote.response.MovieTrailerRes
import uz.mamadaliev.mimovie.data.movie_detail.model.remote.response.ReviewResult
import uz.mamadaliev.mimovie.data.movie_detail.model.remote.similar.SimilarMoviesResult
import uz.mamadaliev.mimovie.domain.movie_detail.MovieDetailUseCase
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val mainUseCase: MovieDetailUseCase,
) : ViewModel() {

    private val movieDetail = MutableLiveData<MovieDetailResponse>()
    val movieDetailLiveData: LiveData<MovieDetailResponse> get() = movieDetail

    private val reviews = MutableLiveData<List<ReviewResult>>()
    val reviewsLiveData: LiveData<List<ReviewResult>> get() = reviews

    private val movieTrailer = MutableLiveData<MovieTrailerRes>()
    val movieTrailerLiveData: LiveData<MovieTrailerRes> get() = movieTrailer

    private val _isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoadingLiveData: LiveData<Boolean> get() = _isLoadingLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorLiveData

    private val similarMoviesList = MutableLiveData<List<SimilarMoviesResult>>()
    val similarMoviesListLiveData: LiveData<List<SimilarMoviesResult>> get() = similarMoviesList

    private val credits = MutableLiveData<Credits>()
    val creditsLiveData: LiveData<Credits> get() = credits


    fun getMovieDetailById(id: Long) {
        viewModelScope.launch {
            mainUseCase.getMovieDetailById(id).catch {
                //xatolik beriladi
                Log.d("DDDD", "getServicesResponse: $this")
            }.collect { result ->
                when (result) {
                    is BaseNetworkResult.Success -> {
                        result.data?.let { item ->
                            movieDetail.value = item
                        }
                    }

                    is BaseNetworkResult.Error -> {
                        result.message.let {
                            _errorLiveData.value = it
                        }
                    }

                    is BaseNetworkResult.Loading -> {
                        //loading
                        result.isLoading?.let {
                            _isLoadingLiveData.value = it
                        }
                    }
                }
            }
        }
    }

    fun getMovieTrailerListById(id: Long) {
        viewModelScope.launch {
            mainUseCase.getMovieTrailerListById(id).catch {
                Log.d("DDDD", "getMovieTrailerListById: $this")
            }.collect { result ->
                when (result) {
                    is BaseNetworkResult.Success -> {
                        result.data?.let { item ->
                            movieTrailer.value = item
                        }
                    }

                    is BaseNetworkResult.Error -> {
                        result.message.let {
                            _errorLiveData.value = it
                        }
                    }

                    is BaseNetworkResult.Loading -> {
                        //loading
                        result.isLoading?.let {
                            _isLoadingLiveData.value = it
                        }
                    }
                }
            }
        }
    }

    fun getReviews(id: Long) {
        viewModelScope.launch {
            mainUseCase.getReviews(id).catch {
                //xatolik beriladi
                Log.d("DDDD", "getReviews: $this")
            }.collect { result ->
                when (result) {
                    is BaseNetworkResult.Success -> {
                        result.data?.let { item ->
                            reviews.value = item.results
                        }
                    }

                    is BaseNetworkResult.Error -> {
                        result.message.let {
                            _errorLiveData.value = "ERROR"
                        }
                    }

                    is BaseNetworkResult.Loading -> {
                        //loading
                        result.isLoading?.let {
                            _isLoadingLiveData.value = it
                        }
                    }
                }
            }
        }
    }


    fun getCredits(id: Long) {
        viewModelScope.launch {
            mainUseCase.getCredits(id).catch {
                Log.d("DDDD", "getServicesResponse: $this")
            }.collect { result ->
                when (result) {
                    is BaseNetworkResult.Success -> {
                        result.data?.let { item ->
                            credits.value = item
                        }
                    }

                    is BaseNetworkResult.Error -> {
                        result.message.let {
                            _errorLiveData.value = "ERROR"
                        }
                    }

                    is BaseNetworkResult.Loading -> {
                        //loading
                        result.isLoading?.let {
                            _isLoadingLiveData.value = it
                        }
                    }
                }
            }
        }
    }

    fun getSimilarFilms(id: Long) {
        viewModelScope.launch {
            mainUseCase.getSimilarFilms(id).catch {
                Log.d("DDDD", "getServicesResponse: $this")
            }.collect { result ->
                when (result) {
                    is BaseNetworkResult.Success -> {
                        result.data?.let { item ->
                            similarMoviesList.value = item.results
                        }
                    }

                    is BaseNetworkResult.Error -> {
                        result.message.let {
                            _errorLiveData.value = "ERROR"
                        }
                    }

                    is BaseNetworkResult.Loading -> {
                        //loading
                        result.isLoading?.let {
                            _isLoadingLiveData.value = it
                        }
                    }
                }
            }
        }
    }
}