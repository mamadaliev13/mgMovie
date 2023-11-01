package uz.mamadaliev.mimovie.presentation.genre

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import uz.mamadaliev.mimovie.R
import uz.mamadaliev.mimovie.databinding.FragmentGenreBinding
import uz.mamadaliev.mimovie.presentation.BaseFragment
import uz.mamadaliev.mimovie.presentation.adapter.HomeMovieLatestAdapter

@AndroidEntryPoint
class GenreFragment :
    BaseFragment<FragmentGenreBinding>(FragmentGenreBinding::inflate) {
    val viewModel: GenreViewModel by viewModels()
    private val adapter by lazy {
        HomeMovieLatestAdapter()
    }

    override fun onViewCreate() {
        val id = requireArguments().getInt("GENRE_ID", 0)
        val name = requireArguments().getString("GENRE_NAME", "Genre")

        (activity as AppCompatActivity).supportActionBar?.title = name

        binding.allMoviesList.layoutManager = GridLayoutManager(
            requireContext(),
            2, GridLayoutManager.VERTICAL, false
        );
        binding.allMoviesList.adapter = adapter

        adapter.setItemClickListener {
            val bundle = bundleOf("MOVIE_ID" to it)
            navController.navigate(R.id.action_genre_to_movieDetailFragment, bundle)
        }

        viewModel.moviesByGenresListLiveData
            .observe(this) { result ->
                adapter.setMovies(result)
            }

        viewModel.isLoadingLiveData.observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = it
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getAllMoviesGenres()
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        viewModel.getMoviesByGenre(id)
    }
}