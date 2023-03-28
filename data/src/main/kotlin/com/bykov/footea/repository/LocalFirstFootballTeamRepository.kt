package com.bykov.footea.repository

import com.bykov.footea.database.TeamsDao
import com.bykov.footea.database.model.TeamEntity
import com.bykov.footea.database.model.toTeam
import com.bykov.footea.database.model.toTeamDetails
import com.bykov.footea.models.FootballTeam
import com.bykov.footea.models.FootballTeamDetails
import io.reactivex.Single

class LocalFirstFootballTeamRepository(
    private val dao: TeamsDao,
    private val remote: FootballTeamRepository,
) : FootballTeamRepository {

    override fun teams(forceUpdate: Boolean): Single<List<FootballTeam>> {
        return dao.getAll().flatMap { teams ->
            if (teams.isEmpty()) {
                remote.teams()
            } else {
                Single.just(teams.map(TeamEntity::toTeam))
            }
        }
    }

    override fun details(teamId: Int): Single<FootballTeamDetails> {
        return dao.getById(teamId).map(TeamEntity::toTeamDetails)
            .switchIfEmpty(remote.details(teamId))
    }
}
