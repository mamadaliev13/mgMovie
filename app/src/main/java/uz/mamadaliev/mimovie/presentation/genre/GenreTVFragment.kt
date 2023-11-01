package uz.mamadaliev.mimovie.presentation.genre

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import uz.mamadaliev.mimovie.R
import uz.mamadaliev.mimovie.databinding.FragmentGenreTVBinding
import uz.mamadaliev.mimovie.presentation.BaseFragment
import uz.mamadaliev.mimovie.presentation.adapter.TVShowSearchAdapter

@AndroidEntryPoint
class GenreTVFragment : BaseFragment<FragmentGenreTVBinding>(FragmentGenreTVBinding::inflate) {
    val viewModel: GenreViewModel by viewModels()
    val adapter by lazy {
        TVShowSearchAdapter()
    }

    override fun onViewCreate() {
        val id = requireArguments().getInt("GENRE_ID", 0)
        val name = requireArguments().getString("GENRE_NAME", "Genre")

        (activity as AppCompatActivity).supportActionBar?.title = name

        binding.allTVShowsList.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL, false
        )
        binding.allTVShowsList.adapter = adapter

        adapter.setItemClickListener {
            val bundle = bundleOf("TV_ID" to it)
            navController.navigate(R.id.action_genreTVFragment_to_TVShowDetailFragment, bundle)
        }

        viewModel.tvShowsByGenreListLiveData.observe(this) { result ->
            if (result != null) {
                adapter.setTVShows(result)
            }
        }

        viewModel.isLoadingLiveData.observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = it
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        viewModel.getTVShowsByGenre(id)
    }
}