package ru.bykov.footballteams.models

import android.util.SparseArray
import io.reactivex.Single
import ru.bykov.footballteams.di.BASE_URL
import ru.bykov.footballteams.extensions.retryExponential
import ru.bykov.footballteams.extensions.switchToSingleIfEmpty
import ru.bykov.footballteams.extensions.toMaybe
import ru.bykov.footballteams.network.TeamsApi

interface FootballTeamRepository {

    fun teams(forceUpdate: Boolean = false): Single<List<FootballTeam>>

    fun details(id: Int): Single<FootballTeamDetails>

    class Impl(private val api: TeamsApi) : FootballTeamRepository {

        override fun teams(forceUpdate: Boolean): Single<List<FootballTeam>> {
            return api.getTeams().retryExponential()
        }

        override fun details(id: Int): Single<FootballTeamDetails> {
            return api.getTeamDetails(id).retryExponential()
                .map {
                    it.copy(badgeUrl = BASE_URL + it.badgeUrl)
                }
        }
    }
}

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