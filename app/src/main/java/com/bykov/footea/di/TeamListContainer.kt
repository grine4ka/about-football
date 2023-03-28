package com.bykov.footea.di

import android.content.Context
import com.bykov.footea.main.MainActivity
import com.bykov.footea.main.MainContract
import com.bykov.footea.main.MainPresenter
import com.bykov.footea.main.TeamListAdapter
import com.bykov.footea.repository.FootballTeamRepository
import com.bykov.footea.repository.StringPreferencesRepository
import com.bykov.footea.ui.DisplayableItem
import com.bykov.footea.ui.FootballTeamItem
import com.bykov.footea.ui.FootballTeamsViewHolderFactory
import com.bykov.footea.ui.OnTeamItemClickListener
import com.bykov.footea.usecase.GetTeams

class TeamListContainer(
    private val preferencesRepository: StringPreferencesRepository,
    private val localRepository: FootballTeamRepository,
    private val remoteRepository: FootballTeamRepository,
) {

    private val items: MutableList<DisplayableItem> = mutableListOf()

    private fun holderFactory(
        context: Context,
        onTeamItemClickListener: OnTeamItemClickListener
    ): FootballTeamsViewHolderFactory {
        return FootballTeamsViewHolderFactory.Impl(
            context = context,
            itemClickListener = {
                val footballTeamItem = items[it] as FootballTeamItem
                onTeamItemClickListener.onTeamItemClicked(footballTeamItem.footballTeam)
            }
        )
    }

    fun presenter(view: MainContract.View): MainContract.Presenter {
        return MainPresenter(
            GetTeams(
                preferencesRepository,
                localRepository,
                remoteRepository
            ),
            view
        )
    }

    fun adapter(
        activity: MainActivity,
        onTeamItemClickListener: OnTeamItemClickListener,
    ): TeamListAdapter {
        return TeamListAdapter(items, holderFactory(activity, onTeamItemClickListener))
    }
}