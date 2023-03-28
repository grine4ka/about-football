package com.bykov.footea.details

import com.bykov.footea.extensions.async
import com.bykov.footea.models.FootballTeamDetails
import com.bykov.footea.usecase.UseCase
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class TeamDetailsPresenter(
    private val getTeamById: UseCase<Single<FootballTeamDetails>, Int>,
    private val view: TeamDetailsContract.View,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : TeamDetailsContract.Presenter {

    override fun loadTeamDetails(teamId: Int) {
        compositeDisposable.add(
            getTeamById(teamId)
                .async()
                .subscribe(
                    { view.showDetails(it) },
                    { view.showError(it.message) }
                )
        )
    }

    override fun destroy() {
        compositeDisposable.clear()
    }
}