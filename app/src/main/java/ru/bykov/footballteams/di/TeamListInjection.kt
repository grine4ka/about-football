package ru.bykov.footballteams.di

import ru.bykov.footballteams.main.MainActivity
import ru.bykov.footballteams.main.MainContract
import ru.bykov.footballteams.main.MainPresenter
import ru.bykov.footballteams.main.TeamListAdapter
import ru.bykov.footballteams.ui.DisplayableItem
import ru.bykov.footballteams.ui.FootballTeamItem
import ru.bykov.footballteams.ui.FootballTeamsViewHolderFactory
import ru.bykov.footballteams.ui.OnTeamItemClickListener

class TeamListInjection(
    activity: MainActivity,
    onTeamItemClickListener: OnTeamItemClickListener
) {

    private val items: MutableList<DisplayableItem> = mutableListOf()

    private val holderFactory: FootballTeamsViewHolderFactory by lazy(LazyThreadSafetyMode.NONE) {
        FootballTeamsViewHolderFactory.Impl(
            context = activity,
            itemClickListener = {
                val footballTeamItem = items[it] as FootballTeamItem
                onTeamItemClickListener.onTeamItemClicked(footballTeamItem.footballTeam)
            }
        )
    }

    val presenter: MainContract.Presenter by lazy(LazyThreadSafetyMode.NONE) {
        MainPresenter(Injection.repository, activity)
    }

    val adapter: TeamListAdapter by lazy(LazyThreadSafetyMode.NONE) {
        TeamListAdapter(items, holderFactory)
    }
}