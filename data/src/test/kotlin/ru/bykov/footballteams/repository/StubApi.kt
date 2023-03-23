package ru.bykov.footballteams.repository

import io.reactivex.Single
import ru.bykov.footballteams.network.TeamsApi
import ru.bykov.footballteams.network.model.ApiEnvelope
import ru.bykov.footballteams.network.model.ApiFullTeam

internal class StubApi(
    private val teams: List<ApiFullTeam> = emptyList()
) : TeamsApi {
    override fun getTeams(leagueId: Int, year: String): Single<ApiEnvelope<List<ApiFullTeam>>> {
        return Single.fromCallable {
            ApiEnvelope(
                emptyList(),
                teams,
                teams.size
            )
        }
    }


    override fun getTeamDetails(id: Int): Single<ApiEnvelope<List<ApiFullTeam>>> {
        return Single.fromCallable {
            ApiEnvelope(
                emptyList(),
                listOf(teams.first { it.team.id == id }),
                1
            )
        }
    }
}