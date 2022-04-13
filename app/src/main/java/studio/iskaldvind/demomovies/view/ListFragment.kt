package studio.iskaldvind.demomovies.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import studio.iskaldvind.demomovies.base.BaseFragment
import studio.iskaldvind.demomovies.R.layout.list_fragment
import studio.iskaldvind.demomovies.databinding.ListFragmentBinding
import studio.iskaldvind.demomovies.model.Movie
import studio.iskaldvind.demomovies.viewmodel.ListViewModel

class ListFragment : BaseFragment(list_fragment) {

    companion object {
        fun newInstance(): Fragment = ListFragment()
    }

    private val viewModel: ListViewModel by viewModel()
    private val binding: ListFragmentBinding by viewBinding()
    private val adapter by lazy {
        MoviesAdapter(context = requireContext()) { movie -> onItemClick(movie = movie) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.adapter = adapter
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.movies.collectLatest(adapter::submitData)
            }
        }
    }

    private fun onItemClick(movie: Movie?) {
        movie?.let {
            (requireActivity() as MainActivity).navigateToDetails(movie = movie)
        }
    }
}