package ru.bykov.footballteams.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.bykov.footballteams.BuildConfig
import ru.bykov.footballteams.Secrets
import ru.bykov.footballteams.network.TeamsApi
import ru.bykov.footballteams.repository.FootballTeamRepository
import ru.bykov.footballteams.repository.InMemoryCachedFootballTeamRepository
import ru.bykov.footballteams.repository.RemoteFootballTeamRepository

private const val BASE_URL = "https://v3.football.api-sports.io"

object Injection {

    private val loggingInterceptor: Interceptor by lazy {
        HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    private val secrets: Secrets = Secrets()

    private val authInterceptor: Interceptor by lazy {
        Interceptor { chain ->
            chain.proceed(
                chain.request()
                    .newBuilder()
                    .addHeader("x-apisports-key", secrets.getApiFootballKey(BuildConfig.APPLICATION_ID))
                    .build()
            )
        }
    }

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addNetworkInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .build()
    }

    private val gson: Gson by lazy {
        GsonBuilder().create()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addCallAdapterFactory(
                RxJava2CallAdapterFactory.create()
            )
            .addConverterFactory(
                GsonConverterFactory.create(gson)
            )
            .client(httpClient)
            .baseUrl(BASE_URL)
            .build()
    }

    private val teamsApi: TeamsApi by lazy {
        retrofit.create(TeamsApi::class.java)
    }

    val repository: FootballTeamRepository by lazy {
        InMemoryCachedFootballTeamRepository(
            RemoteFootballTeamRepository(teamsApi)
        )
    }
}