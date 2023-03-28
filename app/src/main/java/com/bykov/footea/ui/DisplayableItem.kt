package com.bykov.footea.ui

import com.bykov.footea.R
import com.bykov.footea.models.FootballTeam

interface DisplayableItem {
    val viewType: Int
}

class FootballTeamItem(val footballTeam: FootballTeam) : DisplayableItem {
    override val viewType: Int = R.layout.item_football_team
}