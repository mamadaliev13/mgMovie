package uz.mamadaliev.mimovie.presentation.trailers

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import uz.mamadaliev.mimovie.R
import uz.mamadaliev.mimovie.databinding.FragmentTrailersBinding
import uz.mamadaliev.mimovie.presentation.BaseFragment
import uz.mamadaliev.mimovie.presentation.adapter.MovieTrailerLimitlessAdapter
import uz.mamadaliev.mimovie.presentation.movie_detail.MovieDetailViewModel

@AndroidEntryPoint
class MovieTrailersFragment :
    BaseFragment<FragmentTrailersBinding>(FragmentTrailersBinding::inflate) {
    val viewModel: MovieDetailViewModel by viewModels()
    private val adapterTrailer by lazy {
        MovieTrailerLimitlessAdapter()
    }

    override fun onViewCreate() {
        val id = requireArguments().getLong("MOVIE_ID", 0)

        binding.allTrailersList.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL, false)
        binding.allTrailersList.adapter = adapterTrailer

        viewModel.getMovieTrailerListById(id)
        viewModel.movieTrailerLiveData.observe(viewLifecycleOwner) {
            it.results.let { item ->
                if (item != null) {
                    adapterTrailer.setTrailers(item)
                }
            }
        }

        adapterTrailer.setItemClickListener {
            val bundle = bundleOf("VIDEO_URL" to it)
            navController.navigate(
                R.id.action_movieTrailersFragment_to_playerFragment,
                bundle)
        }
    }
}