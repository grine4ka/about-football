package ru.bykov.footballteams.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.bykov.footballteams.models.FootballTeamRepository
import ru.bykov.footballteams.models.InMemoryCachedFootballTeamRepository
import ru.bykov.footballteams.models.RemoteFootballTeamRepository
import ru.bykov.footballteams.network.TeamsApi

const val BASE_URL = "https://android-exam.s3-eu-west-1.amazonaws.com"

object Injection {

    private val interceptor: Interceptor by lazy {
        HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
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