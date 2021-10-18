package ru.bykov.footballteams.ui

import ru.bykov.footballteams.R
import ru.bykov.footballteams.models.FootballTeam

interface DisplayableItem {
    val viewType: Int
}

class FootballTeamItem(val footballTeam: FootballTeam) : DisplayableItem {
    override val viewType: Int = R.layout.item_football_team
}