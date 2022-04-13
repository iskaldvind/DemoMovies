package studio.iskaldvind.demomovies.view

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.fragment.app.Fragment
import studio.iskaldvind.demomovies.R
import studio.iskaldvind.demomovies.base.BaseActivity
import studio.iskaldvind.demomovies.R.layout.main_activity
import studio.iskaldvind.demomovies.model.Movie

class MainActivity: BaseActivity(main_activity) {

    private val list = "list"
    private val details = "details"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateToMovies()
    }

    private fun navigateToMovies() =
        navigate(ListFragment.newInstance(), list)

    fun navigateToDetails(movie: Movie) =
        navigate(DetailsFragment.newInstance(movie = movie), details)

    private fun navigate(fragment: Fragment, tag: String) =
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment, tag)
            .addToBackStack(tag)
            .commit()
}