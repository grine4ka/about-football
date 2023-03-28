package com.bykov.footea.di

import android.content.Context
import com.bykov.footea.database.FooteaDatabase
import com.bykov.footea.network.TeamsApi
import com.bykov.footea.repository.FootballTeamRepository
import com.bykov.footea.repository.LocalFirstFootballTeamRepository
import com.bykov.footea.repository.RemoteFootballTeamRepository
import com.bykov.footea.repository.StringPreferencesRepository
import com.bykov.footea.repository.StringSharedPreferencesRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://v3.football.api-sports.io"

private const val STRING_PREFS_NAME = "rx_string_prefs"

class DataContainer(private val context: Context) {

    private val loggingInterceptor: Interceptor by lazy {
        HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    private fun authInterceptor(apkKey: String): Interceptor {
        return Interceptor { chain ->
            chain.proceed(
                chain.request()
                    .newBuilder()
                    .addHeader("x-apisports-key", apkKey)
                    .build()
            )
        }
    }

    private fun httpClient(apiKey: String): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor(apiKey))
            .build()
    }

    private val gson: Gson by lazy {
        GsonBuilder().create()
    }

    private fun retrofit(apiKey: String): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(
                RxJava2CallAdapterFactory.create()
            )
            .addConverterFactory(
                GsonConverterFactory.create(gson)
            )
            .client(httpClient(apiKey))
            .baseUrl(BASE_URL)
            .build()
    }

    private fun teamsApi(apiKey: String): TeamsApi {
        return retrofit(apiKey).create(TeamsApi::class.java)
    }

    fun stringPreferencesRepository(): StringPreferencesRepository {
        return StringSharedPreferencesRepository(
            context.getSharedPreferences(STRING_PREFS_NAME, Context.MODE_PRIVATE)
        )
    }

    fun remoteRepository(apiKey: String): FootballTeamRepository {
        return RemoteFootballTeamRepository(
            teamsApi(apiKey),
            FooteaDatabase.getInstance(context).teamsDao()
        )
    }

    fun localRepository(remote: FootballTeamRepository): FootballTeamRepository {
        return LocalFirstFootballTeamRepository(
            FooteaDatabase.getInstance(context).teamsDao(),
            remote
        )
    }
}
