package studio.iskaldvind.demomovies.repository

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import studio.iskaldvind.demomovies.model.GenresDTO
import studio.iskaldvind.demomovies.model.MovieDTO
import studio.iskaldvind.demomovies.model.MoviesDTO
import java.util.concurrent.TimeUnit

interface IRepositoryAPI {

    companion object Factory{
        fun create(): IRepositoryAPI {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(ApiInterceptor())
                .retryOnConnectionFailure(true)
                .connectionPool(ConnectionPool(0, 5, TimeUnit.MINUTES))
                .protocols(listOf(Protocol.HTTP_1_1))
                .build()
            val gson = GsonBuilder().create()
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://api.themoviedb.org/3/")
                .client(client)
                .build()
            return retrofit.create(IRepositoryAPI::class.java)
        }
    }

    @GET("discover/movie")
    fun fetchMoviesAsync(@Query("page") page: Int): Deferred<MoviesDTO>

    @GET("genre/movie/list")
    fun fetchGenresAsync(): Deferred<GenresDTO>
}