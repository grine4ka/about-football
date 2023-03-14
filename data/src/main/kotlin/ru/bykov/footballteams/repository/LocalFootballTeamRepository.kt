package ru.bykov.footballteams.repository

import io.reactivex.Single
import ru.bykov.footballteams.database.TeamsDao
import ru.bykov.footballteams.database.model.TeamEntity
import ru.bykov.footballteams.database.model.toTeam
import ru.bykov.footballteams.database.model.toTeamDetails
import ru.bykov.footballteams.models.FootballTeam
import ru.bykov.footballteams.models.FootballTeamDetails

class LocalFootballTeamRepository(
    private val teamsDao: TeamsDao
) : FootballTeamRepository {

    override fun teams(forceUpdate: Boolean): Single<List<FootballTeam>> {
        return teamsDao.getAll().map { entities -> entities.map(TeamEntity::toTeam) }
    }

    override fun details(teamId: Int): Single<FootballTeamDetails> {
        return teamsDao.getById(teamId).map { it.toTeamDetails() }
            .toSingle(FootballTeamDetails.EMPTY)
    }
}