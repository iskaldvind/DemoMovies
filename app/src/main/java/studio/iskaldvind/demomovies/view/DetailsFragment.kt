package studio.iskaldvind.demomovies.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import studio.iskaldvind.demomovies.base.BaseFragment
import studio.iskaldvind.demomovies.R.layout.details_fragment
import studio.iskaldvind.demomovies.arguments
import studio.iskaldvind.demomovies.databinding.DetailsFragmentBinding
import studio.iskaldvind.demomovies.model.Movie
import studio.iskaldvind.demomovies.utils.formatDate

class DetailsFragment : BaseFragment(details_fragment) {

    companion object {
        private const val MOVIE = "DetailsFragment.params.movie"
        fun newInstance(movie: Movie): Fragment = DetailsFragment().arguments(
            MOVIE to movie
        )
    }

    private val binding: DetailsFragmentBinding by viewBinding()
    private val movie: Movie by lazy { arguments?.getSerializable(MOVIE) as Movie }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            Glide.with(requireContext())
                .load(movie.background)
                .into(backPicture)
            Glide.with(requireContext())
                .load(movie.picture)
                .into(picture)
            title.text = movie.title
            val dateText = "Released: ${formatDate(movie.date)}"
            date.text = dateText
            genres.text = movie.genres
            val rating = (movie.rating/100).toInt()
            val ratingText = "$rating%"
            ratingValue.text = ratingText
            ratingBar.progress = rating
            overview.text = movie.overview
        }
    }
}