package ru.bykov.footballteams.repository

import android.util.SparseArray
import io.reactivex.Single
import ru.bykov.footballteams.extensions.switchToSingleIfEmpty
import ru.bykov.footballteams.extensions.toMaybe
import ru.bykov.footballteams.models.FootballTeam
import ru.bykov.footballteams.models.FootballTeamDetails

class InMemoryCachedFootballTeamRepository(
    private val remote: FootballTeamRepository,
    private val teams: MutableList<FootballTeam> = mutableListOf(),
    private val teamDetails: SparseArray<FootballTeamDetails> = SparseArray(5)
) : FootballTeamRepository {

    override fun teams(forceUpdate: Boolean): Single<List<FootballTeam>> {
        if (forceUpdate) {
            return cacheRemoteTeams()
        }
        return teams.toMaybe()
            .switchToSingleIfEmpty {
                cacheRemoteTeams()
            }
    }

    override fun details(teamId: Int): Single<FootballTeamDetails> {
        return teamDetails[teamId].toMaybe()
            .switchToSingleIfEmpty {
                remote.details(teamId).doOnSuccess { details ->
                    teamDetails.append(details.id, details)
                }
            }
    }

    private fun cacheRemoteTeams() = remote.teams().doOnSuccess {
        teams.clear()
        teams.addAll(it)
    }
}