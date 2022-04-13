package studio.iskaldvind.demomovies.repository

import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url().newBuilder()
            .addQueryParameter("api_key", "274f828ad283bd634ef4fc1ee4af255f").build()
        val newRequest = request.newBuilder().url(url).build()
        return chain.proceed(newRequest)
    }
}