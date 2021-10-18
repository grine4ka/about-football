package ru.bykov.footballteams.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.bykov.footballteams.models.FootballTeamRepository
import ru.bykov.footballteams.network.TeamsApi

object Injection {

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
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
            .baseUrl("https://android-exam.s3-eu-west-1.amazonaws.com/")
            .build()
    }

    private val teamsApi: TeamsApi by lazy {
        retrofit.create(TeamsApi::class.java)
    }

    val repository: FootballTeamRepository by lazy {
        FootballTeamRepository.Mock()
    }
}