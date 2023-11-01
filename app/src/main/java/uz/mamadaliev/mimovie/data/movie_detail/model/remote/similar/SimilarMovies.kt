package uz.mamadaliev.mimovie.data.movie_detail.model.remote.similar

data class SimilarMovies(
    val page: Int,
    val results: List<SimilarMoviesResult>,
    val total_pages: Int,
    val total_results: Int
)