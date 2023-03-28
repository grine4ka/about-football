package com.bykov.footea.repository

import com.bykov.footea.models.FootballTeam
import com.bykov.footea.models.FootballTeamDetails
import io.reactivex.Single
import org.junit.jupiter.api.Test

internal class LocalFirstFootballTeamRepositoryTest {

    @Test
    internal fun `should load teams from database when database is not empty`() {
        // given
        val team = FootballTeam(
            33,
            "Manchester United",
            "https://example.com/football/teams/33.png"
        )
        val dao = StubDao(listOf(team.toTeamEntity()))
        val repository = LocalFirstFootballTeamRepository(
            dao,
            EmptyRemoteFootballTeamRepository()
        )

        // when
        val teams = repository.teams().test()

        // then
        teams.assertValue(listOf(team))
    }

    @Test
    internal fun `should load teams from api when database is empty`() {
        // given
        val team = FootballTeam(
            33,
            "Manchester United",
            "https://example.com/football/teams/33.png"
        )
        val api = StubApi(listOf(team.toTeamApi()))
        val dao = StubDao()
        val repository = LocalFirstFootballTeamRepository(
            dao,
            RemoteFootballTeamRepository(api, dao)
        )

        // when
        val teams = repository.teams().test()

        // then
        teams.assertValue(listOf(team))
    }

}

private class EmptyRemoteFootballTeamRepository : FootballTeamRepository {
    override fun teams(forceUpdate: Boolean): Single<List<FootballTeam>> {
        return Single.error(IllegalStateException("Should not be called"))
    }

    override fun details(teamId: Int): Single<FootballTeamDetails> {
        return Single.error(IllegalStateException("Should not be called"))
    }
}