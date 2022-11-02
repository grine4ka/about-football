package ru.bykov.footballteams.models

import android.util.SparseArray
import io.reactivex.Single
import ru.bykov.footballteams.extensions.switchToSingleIfEmpty
import ru.bykov.footballteams.extensions.toMaybe

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

    override fun details(id: Int): Single<FootballTeamDetails> {
        return teamDetails[id].toMaybe()
            .switchToSingleIfEmpty {
                remote.details(id).doOnSuccess { details ->
                    teamDetails.append(details.id, details)
                }
            }
    }

    private fun cacheRemoteTeams() = remote.teams().doOnSuccess {
        teams.clear()
        teams.addAll(it)
    }
}