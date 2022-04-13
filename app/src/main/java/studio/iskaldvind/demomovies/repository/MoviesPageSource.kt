package studio.iskaldvind.demomovies.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import studio.iskaldvind.demomovies.model.Movie

class MoviesPageSource(
    private val repository: IRepository
) : PagingSource<Int, Movie>() {

    private var genres: HashMap<Int, String> = hashMapOf()

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page: Int = params.key ?: 1
        return try {
            val response = repository.getMovies(page = page)
            if (genres.isEmpty()) repository.getGenres()
                .genres
                .forEach { genres[it.id] = it.name }
            val results = response.results.map { Movie(it, genres) }
            val nextKey = if (response.page < response.pages) response.page + 1 else null
            val prevKey = if (response.page > 1) response.page - 1 else null
            LoadResult.Page(results, prevKey, nextKey)
        } catch (e: RuntimeException) {
            LoadResult.Error(e)
        }
    }
}