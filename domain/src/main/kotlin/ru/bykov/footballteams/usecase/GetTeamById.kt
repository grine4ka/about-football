package ru.bykov.footballteams.usecase

import io.reactivex.Single
import ru.bykov.footballteams.models.FootballTeamDetails
import ru.bykov.footballteams.repository.FootballTeamRepository

class GetTeamById(
    private val localRepository: FootballTeamRepository,
    private val remoteRepository: FootballTeamRepository
) : UseCase<Single<FootballTeamDetails>, Int>{

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(teamId: Int): Single<FootballTeamDetails> {
        return localRepository.details(teamId).flatMap {
            if (it == FootballTeamDetails.EMPTY) {
                remoteRepository.details(teamId)
            } else {
                Single.just(it)
            }
        }
    }
}