package com.bykov.footea.repository

import com.bykov.footea.database.TeamsDao
import com.bykov.footea.database.model.TeamEntity
import io.reactivex.Maybe
import io.reactivex.Single

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
