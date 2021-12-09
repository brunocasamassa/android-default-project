package br.com.lea.apimanager

import android.app.Application
import br.com.lea.apimanager.di.ApiManagerModule
import br.com.lea.apimanager.session.interceptors.AuthInterceptor
import br.com.lea.apimanager.session.interceptors.ResponseInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class HttpClient(private val androidApplication: Application) {

    private companion object {
        private const val CACHE_OF_10_MB: Long = 10 * 1024 * 1024
    }

    private lateinit var httpClient: OkHttpClient.Builder


    fun <S> create(
        serviceClass: Class<S>
    ): S {

        setClient()
        setLogs()

        return Retrofit.Builder()
            .baseUrl(ApiManagerModule.baseUrl)
            //.baseUrl("https://randomuser.me/")
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            //.addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(serviceClass)
    }

    private fun setClient() {

        val cache = Cache(androidApplication.cacheDir, CACHE_OF_10_MB)

        httpClient = OkHttpClient.Builder()
            .cache(cache)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)

        with(httpClient) {
            let {
               addInterceptor(AuthInterceptor())
               addInterceptor(ResponseInterceptor(disableRetry = true))
            }
        }

    }

    private fun setLogs() {
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)
        }
    }
}