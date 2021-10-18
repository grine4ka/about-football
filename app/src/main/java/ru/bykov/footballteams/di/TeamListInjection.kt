package ru.bykov.footballteams.di

import ru.bykov.footballteams.MainActivity
import ru.bykov.footballteams.TeamListAdapter
import ru.bykov.footballteams.extensions.toast
import ru.bykov.footballteams.models.FootballTeamRepository
import ru.bykov.footballteams.ui.DisplayableItem
import ru.bykov.footballteams.ui.FootballTeamItem
import ru.bykov.footballteams.ui.FootballTeamsViewHolderFactory

class TeamListInjection(
    activity: MainActivity
) {

    private val items: MutableList<DisplayableItem> = mutableListOf()

    private val holderFactory: FootballTeamsViewHolderFactory by lazy(LazyThreadSafetyMode.NONE) {
        FootballTeamsViewHolderFactory.Impl(
            context = activity,
            itemClickListener = {
                val footballTeamItem = items[it] as FootballTeamItem
                activity.toast("Team ${footballTeamItem.footballTeam.name} clicked")
            }
        )
    }

    val adapter: TeamListAdapter by lazy(LazyThreadSafetyMode.NONE) {
        TeamListAdapter(items, holderFactory)
    }

    val repository: FootballTeamRepository = Injection.repository

}