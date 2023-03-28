package com.bykov.footea.usecase

import com.bykov.footea.models.FootballTeamDetails
import com.bykov.footea.repository.FootballTeamRepository
import io.reactivex.Single

class GetTeamById(
    private val repository: FootballTeamRepository
) : UseCase<Single<FootballTeamDetails>, Int> {

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(teamId: Int): Single<FootballTeamDetails> {
        return repository.details(teamId)
    }
}