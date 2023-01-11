package ru.bykov.footballteams.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.bykov.footballteams.entity.ApiEnvelope
import ru.bykov.footballteams.entity.FullTeamEntity

interface TeamsApi {

    @GET("/teams")
    fun getTeams(
        @Query("league") leagueId: Int = 39,
        @Query("season") year: String = "2022"
    ): Single<ApiEnvelope<List<FullTeamEntity>>>

    @GET("/teams")
    fun getTeamDetails(
        @Query("id") id: Int
    ): Single<ApiEnvelope<List<FullTeamEntity>>>
}