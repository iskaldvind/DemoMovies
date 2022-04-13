package studio.iskaldvind.demomovies.repository

import studio.iskaldvind.demomovies.model.GenresDTO
import studio.iskaldvind.demomovies.model.MoviesDTO

interface IRepository {
    suspend fun getMovies(page: Int): MoviesDTO
    suspend fun getGenres(): GenresDTO
}