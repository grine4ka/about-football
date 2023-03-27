package com.bykov.footea.main

import com.bykov.footea.extensions.async
import com.bykov.footea.models.FootballTeam
import com.bykov.footea.ui.FootballTeamItem
import com.bykov.footea.usecase.SimpleUseCase
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class MainPresenter(
    private val getTeams: SimpleUseCase<Single<List<FootballTeam>>>,
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

    override fun destroy() {
        compositeDisposable.clear()
    }

    private fun loadFootballTeams(): Single<List<FootballTeamItem>> {
        return getTeams()
            .map { teams -> teams.map { FootballTeamItem(it) } }
            .async()
    }

}