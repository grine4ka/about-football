package ru.bykov.footballteams.ui

import ru.bykov.footballteams.models.FootballTeam

interface OnTeamItemClickListener {

    fun onTeamItemClicked(team: FootballTeam)
}
