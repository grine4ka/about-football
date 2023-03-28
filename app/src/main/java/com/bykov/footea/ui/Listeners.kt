package com.bykov.footea.ui

import com.bykov.footea.models.FootballTeam

interface OnTeamItemClickListener {

    fun onTeamItemClicked(team: FootballTeam)
}
