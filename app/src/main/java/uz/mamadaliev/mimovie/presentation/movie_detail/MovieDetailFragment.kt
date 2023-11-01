package uz.mamadaliev.mimovie.presentation.movie_detail

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import uz.mamadaliev.mimovie.BuildConfig
import uz.mamadaliev.mimovie.R
import uz.mamadaliev.mimovie.data.changeMoneyType
import uz.mamadaliev.mimovie.data.formatDate
import uz.mamadaliev.mimovie.data.runtimeToHM
import uz.mamadaliev.mimovie.databinding.FragmentMovieDetailThirdBinding
import uz.mamadaliev.mimovie.presentation.BaseFragment
import uz.mamadaliev.mimovie.presentation.YoutubeActivity
import uz.mamadaliev.mimovie.presentation.adapter.CastAdapter
import uz.mamadaliev.mimovie.presentation.adapter.GenreListMiniAdapter
import uz.mamadaliev.mimovie.presentation.adapter.HomeMovieLatestAdapter
import uz.mamadaliev.mimovie.presentation.adapter.MovieTrailerAdapter
import uz.mamadaliev.mimovie.presentation.adapter.ReviewsAdapter
import uz.mamadaliev.mimovie.presentation.adapter.SimilarMoviesAdapter


@AndroidEntryPoint
class MovieDetailFragment :
    BaseFragment<FragmentMovieDetailThirdBinding>(FragmentMovieDetailThirdBinding::inflate) {
    private val viewModel: MovieDetailViewModel by viewModels()

    private val adapter by lazy {
        GenreListMiniAdapter()
    }

    private val adapterSimilarMovies by lazy {
        SimilarMoviesAdapter()
    }

    private val adapterReviews by lazy {
        ReviewsAdapter()
    }

    private val adapterCast by lazy {
        CastAdapter()
    }

    private val adapterTrailer by lazy {
        MovieTrailerAdapter()
    }

    @SuppressLint("SetTextI18n", "ClickableViewAccessibility")
    override fun onViewCreate() {
        binding.apply {
            allGenresList.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL, false
            )

            allGenresList.adapter = adapter

            castList.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL, false
            )

            castList.adapter = adapterCast

            reviewsList.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL, false
            )

            reviewsList.adapter = adapterReviews

            trailersList.layoutManager = GridLayoutManager(
                requireContext(),
                2, GridLayoutManager.VERTICAL, false
            )

            trailersList.adapter = adapterTrailer

            similarsList.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL, false
            )

            similarsList.adapter = adapterSimilarMovies
        }

        val id = requireArguments().getLong("MOVIE_ID", 0)

        viewModel.isLoadingLiveData.observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = it
            if (it == true) {
                binding.main.visibility = View.GONE
            } else {
                binding.main.visibility = View.VISIBLE
            }
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getMovieDetailById(id)
        }

        viewModel.getMovieTrailerListById(id)
        viewModel.movieTrailerLiveData.observe(viewLifecycleOwner) {
            it.results.let { item ->
                if (item != null) {
                    adapterTrailer.setTrailers(item)
                }
            }
        }

        viewModel.getCredits(id)
        viewModel.creditsLiveData.observe(viewLifecycleOwner) {
            it.cast?.let { it1 -> adapterCast.setPersons(it1) }
        }

        viewModel.getReviews(id)
        viewModel.reviewsLiveData.observe(viewLifecycleOwner) {
            it.let { item ->
                if (item != null) {
                    adapterReviews.setReviews(item)
                }
            }
        }

        viewModel.getMovieTrailerListById(id)
        viewModel.movieTrailerLiveData.observe(viewLifecycleOwner) {
            it.results.let { item ->
                if (item != null) {
                    adapterTrailer.setTrailers(item)
                }
            }
        }

        binding.btnVideo.setOnClickListener {
            val bundle = bundleOf("MOVIE_ID" to id)
            navController.navigate(R.id.action_movieDetailFragment_to_movieTrailersFragment, bundle)
        }

        binding.btnCast.setOnClickListener {
            val bundle = bundleOf("MOVIE_ID" to id)
            navController.navigate(R.id.action_movieDetailFragment_to_movieCastFragment, bundle)
        }

        viewModel.getSimilarFilms(id)
        viewModel.similarMoviesListLiveData.observe(viewLifecycleOwner) {
            it.let { item ->
                if (item != null) {
                    adapterSimilarMovies.setMovies(item)
                }
            }
        }

        viewModel.getMovieDetailById(id)
        viewModel.movieDetailLiveData.observe(viewLifecycleOwner) { result ->
            (activity as AppCompatActivity).supportActionBar?.title = result.originalTitle
            binding.apply {
                content.text = result.overview
                name.text = result.originalTitle
                ratingNumber.text = "${result.voteAverage}"
                runtime.text = result.runtime.runtimeToHM()
                budget.text = "\$${result.budget.toString().changeMoneyType()}"
                status.text = result.status
                revenue.text = "\$${result.revenue.toString().changeMoneyType()}"
                voteCount.text = "${result.voteCount} total votes"

                Glide.with(binding.root.context)
                    .load("${BuildConfig.BASE_IMAGE_URL}${result.backdropPath}")
                    .into(binding.image)

                Glide.with(binding.root.context)
                    .load("${BuildConfig.BASE_IMAGE_URL}${result.posterPath}")
                    .into(binding.poster)

                poster.setOnClickListener {
                    val bundle =
                        bundleOf("IMAGE_URL" to "${BuildConfig.BASE_IMAGE_URL}${result.posterPath}")
                    navController.navigate(
                        R.id.action_movieDetailFragment_to_imageViewerFragment,
                        bundle
                    )
                }

                image.setOnClickListener {
                    val bundle =
                        bundleOf("IMAGE_URL" to "${BuildConfig.BASE_IMAGE_URL}${result.backdropPath}")
                    navController.navigate(
                        R.id.action_movieDetailFragment_to_imageViewerFragment,
                        bundle
                    )
                }
            }

            if (result.releaseDate.isNotEmpty()) {
                result.releaseDate.let {
                    binding.releaseDate.text = it.formatDate()
                }
            }

            adapter.setGenres(result.genres)

            adapter.setItemClickListener { id, name ->
                val bundle = bundleOf("GENRE_ID" to id, "GENRE_NAME" to name)
                navController.navigate(R.id.action_movieDetailFragment_to_genreFragment, bundle)
            }

            adapterTrailer.setItemClickListener {
                val intent = Intent(requireContext(), YoutubeActivity::class.java)
                intent.putExtra("YOUTUBE_VIDEO_ID", it)
                startActivity(intent)
            }

            adapterCast.setItemClickListener {
                val bundle = bundleOf("ACTOR_ID" to it)
                navController.navigate(R.id.action_movieDetailFragment_to_actorsFragment, bundle)
            }

            adapterSimilarMovies.setItemClickListener {
                val bundle = bundleOf("MOVIE_ID" to it)
                navController.navigate(R.id.action_movieDetailFragment_self, bundle)
            }
        }
    }
}