package ru.bykov.footballteams.di

import android.content.Context
import ru.bykov.footballteams.main.MainActivity
import ru.bykov.footballteams.main.MainContract
import ru.bykov.footballteams.main.MainPresenter
import ru.bykov.footballteams.main.TeamListAdapter
import ru.bykov.footballteams.repository.FootballTeamRepository
import ru.bykov.footballteams.repository.StringPreferencesRepository
import ru.bykov.footballteams.ui.DisplayableItem
import ru.bykov.footballteams.ui.FootballTeamItem
import ru.bykov.footballteams.ui.FootballTeamsViewHolderFactory
import ru.bykov.footballteams.ui.OnTeamItemClickListener
import ru.bykov.footballteams.usecase.GetTeams

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