package studio.iskaldvind.demomovies.model

import studio.iskaldvind.demomovies.IMAGE_BASE_PATH
import java.io.Serializable

class Movie(movieDTO: MovieDTO, genres: HashMap<Int, String>) : Serializable {
    val title: String
    val overview: String
    val rating: Double
    val background: String
    val genres: String
    val picture: String
    val date: String
    val time: String

    init {
        this.title = movieDTO.title
        this.overview = movieDTO.overview
        this.rating = movieDTO.rating
        this.background = IMAGE_BASE_PATH + movieDTO.background
        this.genres = movieDTO
            .genreIds.mapNotNull { genres[it] }
            .joinToString(", ")
        this.picture = IMAGE_BASE_PATH + movieDTO.picture
        this.date = movieDTO.date
        this.time = movieDTO.time
    }
}