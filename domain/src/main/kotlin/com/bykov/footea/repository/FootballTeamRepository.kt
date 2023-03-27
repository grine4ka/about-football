package com.bykov.footea.repository

import com.bykov.footea.models.FootballTeam
import com.bykov.footea.models.FootballTeamDetails
import io.reactivex.Single

interface FootballTeamRepository {

    fun teams(forceUpdate: Boolean = false): Single<List<FootballTeam>>

    fun details(teamId: Int): Single<FootballTeamDetails>

}
