package uz.mamadaliev.mimovie.data.home.model.remote.response.movie


import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("results")
    var results: List<MovieResult>,
)