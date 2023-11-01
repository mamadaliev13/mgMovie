package uz.mamadaliev.mimovie.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.mamadaliev.mimovie.BuildConfig
import uz.mamadaliev.mimovie.data.home.model.local.MovieResultDto
import uz.mamadaliev.mimovie.data.movie_detail.model.remote.similar.SimilarMoviesResult
import uz.mamadaliev.mimovie.databinding.ItemMovieLatestBinding

class SimilarMoviesAdapter : RecyclerView.Adapter<SimilarMoviesAdapter.HomeMovieViewHolder>() {
    var _data = mutableListOf<SimilarMoviesResult>()

    private var itemClickListener: ((id: Long) -> Unit)? = null

    fun setItemClickListener(f: (id: Long) -> Unit) {
        itemClickListener = f
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setMovies(newData: List<SimilarMoviesResult>) {
        _data.clear()
        _data.addAll(newData)
        notifyDataSetChanged()
    }

    inner class HomeMovieViewHolder(private val binding: ItemMovieLatestBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindView(data: SimilarMoviesResult) {
            binding.apply {
//                name.text = data.originalTitle
                Glide.with(binding.root.context)
                    .load("${BuildConfig.BASE_IMAGE_URL}${data.poster_path}")
                    .into(binding.image)

                itemView.setOnClickListener {
                    itemClickListener?.invoke(data.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HomeMovieViewHolder(
            ItemMovieLatestBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun getItemCount() = _data.size

    override fun onBindViewHolder(holder: HomeMovieViewHolder, position: Int) =
        holder.bindView(_data[position])
}