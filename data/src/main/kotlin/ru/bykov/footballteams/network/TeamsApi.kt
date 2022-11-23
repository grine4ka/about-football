package ru.bykov.footballteams.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.bykov.footballteams.entity.FootballTeamDetailsEntity
import ru.bykov.footballteams.entity.FootballTeamEntity

interface TeamsApi {

    @GET("/teams/teams.json")
    fun getTeams(): Single<List<FootballTeamEntity>>

    @GET("/teams/{id}/team.json")
    fun getTeamDetails(@Path("id") id: Int): Single<FootballTeamDetailsEntity>
}