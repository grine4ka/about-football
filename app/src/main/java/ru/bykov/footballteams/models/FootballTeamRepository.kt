package ru.bykov.footballteams.models

import io.reactivex.Single

interface FootballTeamRepository {

    fun teams(): Single<List<FootballTeam>>

    fun details(id: Int): Single<FootballTeamDetails>

    class Mock : FootballTeamRepository {

        override fun teams(): Single<List<FootballTeam>> {
            return Single.fromCallable {
                listOf(FootballTeam(1, "FC Barcelona"))
            }
        }

        override fun details(id: Int): Single<FootballTeamDetails> {
            return Single.fromCallable {
                FootballTeamDetails(1, "FC Barcelona", Gender.MALE, false, "Mesque un club", "")
            }
        }

    }
}