package ru.bykov.footballteams.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.bykov.footballteams.models.FootballTeam
import ru.bykov.footballteams.models.FootballTeamDetails

interface TeamsApi {

    @GET("/teams/teams.json")
    fun getTeams(): Single<List<FootballTeam>>

    @GET("/teams/{id}/team.json")
    fun getTeamDetails(@Path("id") id: Int): Single<FootballTeamDetails>
}