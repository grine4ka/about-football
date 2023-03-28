package com.bykov.footea.repository

import com.bykov.footea.network.TeamsApi
import com.bykov.footea.network.model.ApiEnvelope
import com.bykov.footea.network.model.ApiFullTeam
import io.reactivex.Single

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