package ru.bykov.footballteams.repository

import io.reactivex.Maybe
import io.reactivex.Single
import ru.bykov.footballteams.database.TeamsDao
import ru.bykov.footballteams.database.model.TeamEntity

internal class StubDao(
    private val teams: List<TeamEntity> = emptyList()
) : TeamsDao {
    override fun getAll(): Single<List<TeamEntity>> {
        return Single.fromCallable { teams }
    }

    override fun getById(teamId: Int): Maybe<TeamEntity> {
        return Maybe.fromCallable { teams.firstOrNull { it.id == teamId } }
    }

    override fun insertOrIgnoreTeams(teams: List<TeamEntity>) {
        // no-op. successfully inserted into db
    }
}
