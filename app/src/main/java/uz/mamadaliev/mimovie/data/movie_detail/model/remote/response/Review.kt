package uz.mamadaliev.mimovie.data.movie_detail.model.remote.response

import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("id")
    var id: Int,
    @SerializedName("page")
    var page: Int,
    @SerializedName("results")
    var results: List<ReviewResult>,
    @SerializedName("total_pages")
    var totalPages: Int,
    @SerializedName("total_result")
    var totalResult: Int,
)