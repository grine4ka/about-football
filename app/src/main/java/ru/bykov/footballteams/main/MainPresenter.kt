package ru.bykov.footballteams.main

import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import ru.bykov.footballteams.extensions.async
import ru.bykov.footballteams.ui.FootballTeamItem
import ru.bykov.footballteams.usecase.GetTeams

class MainPresenter(
    private val getTeams: GetTeams,
    private val view: MainContract.View,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : MainContract.Presenter {

    override fun loadTeams() {
        compositeDisposable.add(
            loadFootballTeams()
                .doOnSubscribe { view.showLoading() }
                .subscribe(
                    {
                        view.showContent(it)
                    },
                    {
                        view.showError()
                    }
                )
        )

    }

    override fun refreshTeams() {
        compositeDisposable.add(
            loadFootballTeams(forceUpdate = true)
                .subscribe(
                    { view.showContent(it) },
                    { view.showError() }
                )
        )
    }

    override fun destroy() {
        compositeDisposable.clear()
    }

    private fun loadFootballTeams(forceUpdate: Boolean = false): Single<List<FootballTeamItem>> {
        return getTeams(forceUpdate)
            .map { teams -> teams.map { FootballTeamItem(it) } }
            .async()
    }

}