package ru.bykov.footballteams.usecase

import io.reactivex.Single
import ru.bykov.footballteams.models.FootballTeam
import ru.bykov.footballteams.repository.FootballTeamRepository

// TODO add pref to get teams from remote only once a day
class GetTeams(
    private val localRepository: FootballTeamRepository,
    private val remoteRepository: FootballTeamRepository
) : UseCase<Single<List<FootballTeam>>, Boolean>{

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(forceUpdate: Boolean): Single<List<FootballTeam>> {
        return if (forceUpdate) {
            remoteRepository.teams()
        } else {
            localRepository.teams()
        }
    }
}
