package studio.iskaldvind.demomovies.repository

import studio.iskaldvind.demomovies.model.GenresDTO
import studio.iskaldvind.demomovies.model.MoviesDTO

class Repository(
    private val api: IRepositoryAPI
) : IRepository {
    override suspend fun getMovies(page: Int): MoviesDTO =
        api.fetchMoviesAsync(page = page).await()
    override suspend fun getGenres(): GenresDTO =
        api.fetchGenresAsync().await()
}