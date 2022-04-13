package studio.iskaldvind.demomovies.model

import com.google.gson.annotations.SerializedName

data class MoviesDTO (
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<MovieDTO>,
    @SerializedName("total_pages") val pages: Int
)

data class MovieDTO (
    @SerializedName("original_title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val rating: Double,
    @SerializedName("backdrop_path") val background: String,
    @SerializedName("genre_ids") val genreIds: List<Int>,
    @SerializedName("poster_path") val picture: String,
    @SerializedName("release_date") val date: String,
    @SerializedName("runtime") val time: String
)

data class GenresDTO(
    @SerializedName("genres") val genres: List<GenreDTO>
)

data class GenreDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)