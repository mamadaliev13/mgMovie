package uz.mamadaliev.mimovie.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.mamadaliev.mimovie.data.movie_detail.model.remote.response.ReviewResult
import uz.mamadaliev.mimovie.databinding.ItemReviewsBinding

class ReviewsAdapter : RecyclerView.Adapter<ReviewsAdapter.HomeMovieViewHolder>() {
    var _data = mutableListOf<ReviewResult>()
//
//    private var itemClickListener: ((key: String) -> Unit)? = null
//
//    fun setItemClickListener(f: (key: String) -> Unit) {
//        itemClickListener = f
//    }

    @SuppressLint("NotifyDataSetChanged")
    fun setReviews(newData: List<ReviewResult>) {
        _data.clear()
        _data.addAll(newData)
        notifyDataSetChanged()
    }

    inner class HomeMovieViewHolder(private val binding: ItemReviewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindView(data: ReviewResult) {
            binding.apply {
                review.text = data.content
                author.text = data.author
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HomeMovieViewHolder(
            ItemReviewsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun getItemCount(): Int {
        if (_data.size > 2) {
            return 2
        } else {
            return _data.size;
        }
    }

    override fun onBindViewHolder(holder: HomeMovieViewHolder, position: Int) =
        holder.bindView(_data[position])
}