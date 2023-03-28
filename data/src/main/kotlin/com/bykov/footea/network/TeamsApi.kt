package com.bykov.footea.network

import com.bykov.footea.network.model.ApiEnvelope
import com.bykov.footea.network.model.ApiFullTeam
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TeamsApi {

    @GET("/teams")
    fun getTeams(
        @Query("league") leagueId: Int = 39,
        @Query("season") year: String = "2022"
    ): Single<ApiEnvelope<List<ApiFullTeam>>>

    @GET("/teams")
    fun getTeamDetails(
        @Query("id") id: Int
    ): Single<ApiEnvelope<List<ApiFullTeam>>>
}