package uz.mamadaliev.mimovie.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.mamadaliev.mimovie.BuildConfig
import uz.mamadaliev.mimovie.R
import uz.mamadaliev.mimovie.data.movie_detail.model.remote.response.Cast
import uz.mamadaliev.mimovie.databinding.ItemActorBinding

class CastAdapter : RecyclerView.Adapter<CastAdapter.MovieCardViewHolder>() {
    private val limit = 4

    private var itemClickListener: ((id: Long) -> Unit)? = null

    fun setItemClickListener(f: (id: Long) -> Unit) {
        itemClickListener = f
    }

    var data = mutableListOf<Cast>()

    @SuppressLint("NotifyDataSetChanged")
    fun setPersons(nData: List<Cast>) {
        this.data.clear()
        this.data.addAll(nData)
        notifyDataSetChanged()
    }

    inner class MovieCardViewHolder(private val binding: ItemActorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: Cast) {
            binding.apply {
                name.text = data.original_name
                character.text = data.character
                Glide.with(binding.root.context)
                    .load("${BuildConfig.BASE_IMAGE_URL}${data.profilePath}")
                    .placeholder(R.drawable.ic_actor)
                    .into(binding.image)

                itemView.setOnClickListener {
                    itemClickListener?.invoke(data.id.toLong())
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieCardViewHolder(
        ItemActorBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun getItemCount(): Int {
        if (data.size > limit) {
            return limit
        } else {
            return data.size;
        }
    }

    override fun onBindViewHolder(holder: MovieCardViewHolder, position: Int) =
        holder.bindData(data[position])
}