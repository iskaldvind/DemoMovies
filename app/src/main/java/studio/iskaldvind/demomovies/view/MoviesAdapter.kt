package studio.iskaldvind.demomovies.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import studio.iskaldvind.demomovies.R
import studio.iskaldvind.demomovies.databinding.MoviesItemBinding
import studio.iskaldvind.demomovies.model.Movie

class MoviesAdapter(
    val context: Context,
    val onItemClick: (Movie?) -> Unit
) : PagingDataAdapter<Movie, MoviesViewHolder>(MovieDiffItemCallback) {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            context = context,
            itemView = layoutInflater.inflate(R.layout.movies_item, parent, false),
            onItemClick = onItemClick
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class MoviesViewHolder(
    val context: Context,
    itemView: View,
    val onItemClick: (Movie?) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val viewBinding by viewBinding(MoviesItemBinding::bind)

    fun bind(movie: Movie?) {
        with(viewBinding) {
            root.setOnClickListener { onItemClick.invoke(movie) }
            Glide.with(context)
                .load(movie?.picture)
                .into(image)
            title.text = movie?.title
            date.text = movie?.date
            genres.text = movie?.genres
            val rating = ((movie?.rating ?: 0.0)/100).toInt()
            val ratingText = "$rating%"
            ratingValue.text = ratingText
            ratingBar.progress = rating
        }
    }
}

private object MovieDiffItemCallback : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.title == newItem.title
    }
}