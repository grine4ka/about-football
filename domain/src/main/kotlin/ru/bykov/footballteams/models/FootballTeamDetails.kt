package ru.bykov.footballteams.models

data class FootballTeamDetails(
    val id: Int,
    val name: String,
    val country: String,
    val venue: String,
    val badgeUrl: String
) {

    companion object {

        private const val EMPTY_ID = -1

        val EMPTY: FootballTeamDetails = FootballTeamDetails(
            EMPTY_ID, "", "", "", ""
        )
    }
}
