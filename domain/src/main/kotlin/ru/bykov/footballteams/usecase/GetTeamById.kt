package ru.bykov.footballteams.usecase

import io.reactivex.Single
import ru.bykov.footballteams.models.FootballTeamDetails
import ru.bykov.footballteams.repository.FootballTeamRepository

class GetTeamById(
    private val repository: FootballTeamRepository
) : UseCase<Single<FootballTeamDetails>, Int>{

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(teamId: Int): Single<FootballTeamDetails> {
        return repository.details(teamId)
    }
}