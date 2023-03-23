package ru.bykov.footballteams.repository

import io.reactivex.Single
import ru.bykov.footballteams.database.TeamsDao
import ru.bykov.footballteams.database.model.TeamEntity
import ru.bykov.footballteams.database.model.toTeam
import ru.bykov.footballteams.database.model.toTeamDetails
import ru.bykov.footballteams.models.FootballTeam
import ru.bykov.footballteams.models.FootballTeamDetails

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
