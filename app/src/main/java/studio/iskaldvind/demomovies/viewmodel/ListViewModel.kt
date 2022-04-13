package studio.iskaldvind.demomovies.viewmodel

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import studio.iskaldvind.demomovies.base.BaseViewModel
import studio.iskaldvind.demomovies.model.Movie
import studio.iskaldvind.demomovies.repository.MoviesPageSource

class ListViewModel(
    private val pagingSource: MoviesPageSource
) : BaseViewModel() {

    val movies: StateFlow<PagingData<Movie>> = Pager(PagingConfig(pageSize = 5)) {
        pagingSource
    }
        .flow
        .cachedIn(viewModelCoroutineScope)
        .stateIn(viewModelCoroutineScope, SharingStarted.Lazily, PagingData.empty())

    override fun handleError(error: Throwable) {
        Log.e("Error", error.stackTraceToString())
    }
}