package ru.bykov.footballteams.repository

import io.kotest.matchers.string.shouldStartWith
import io.reactivex.Single
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import ru.bykov.footballteams.entity.ApiEnvelope
import ru.bykov.footballteams.entity.FullTeamEntity
import ru.bykov.footballteams.entity.TeamEntity
import ru.bykov.footballteams.entity.VenueEntity
import ru.bykov.footballteams.models.FootballTeamDetails
import ru.bykov.footballteams.network.TeamsApi

// TODO remove this test
internal class FootballTeamRepositoryImplTest {

    @Nested
    @DisplayName("Given details loaded successfully")
    inner class DetailsLoadedSuccessfully {

        private lateinit var repository: FootballTeamRepository
        private val imageBaseUrl = "https://example.com"

        @BeforeEach
        fun setup() {
            repository = RemoteFootballTeamRepository(SuccessApi())
        }

        @Nested
        @DisplayName("When details()")
        inner class OnDetailsLoad {

            private lateinit var details: Single<FootballTeamDetails>

            @BeforeEach
            fun setup() {
                details = repository.details(0)
            }

            @Test
            @DisplayName("Then badge_url concatenated with BASE_URL")
            internal fun viewShowsContent() {
                val observer = details.test()
                val teamDetails = observer.values().first()
                teamDetails.badgeUrl shouldStartWith imageBaseUrl
            }
        }
    }
}

private class SuccessApi : TeamsApi {
    override fun getTeams(leagueId: Int, year: String): Single<ApiEnvelope<List<FullTeamEntity>>> {
        throw NotImplementedError("Doesn't need in this test")
    }

    override fun getTeamDetails(id: Int): Single<ApiEnvelope<List<FullTeamEntity>>> {
        return Single.fromCallable {
            ApiEnvelope(
                emptyList(),
                listOf(
                    FullTeamEntity(
                        TeamEntity(
                            id = 33,
                            name = "Manchester United",
                            code = "MUN",
                            country = "England",
                            founded = 1878,
                            national = false,
                            logo = "https://example.com/football/teams/33.png"
                        ),
                        VenueEntity(
                            id = 556,
                            name = "Old Trafford",
                            address = "Sir Matt Busby Way",
                            city = "Manchester",
                            capacity = 76212,
                            surface = "grass",
                            image = "https://example.com/football/venues/556.png"
                        )
                    )
                ),
                1
            )
        }
    }
}