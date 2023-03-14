package ru.bykov.footballteams.repository

import io.kotest.matchers.string.shouldStartWith
import io.reactivex.Maybe
import io.reactivex.Single
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import ru.bykov.footballteams.database.TeamsDao
import ru.bykov.footballteams.database.model.TeamEntity
import ru.bykov.footballteams.models.FootballTeamDetails
import ru.bykov.footballteams.network.TeamsApi
import ru.bykov.footballteams.network.model.ApiEnvelope
import ru.bykov.footballteams.network.model.ApiFullTeam
import ru.bykov.footballteams.network.model.ApiTeam
import ru.bykov.footballteams.network.model.ApiVenue

// TODO refactor this test
internal class RemoteFootballTeamRepositoryTest {

    @Nested
    @DisplayName("Given details loaded successfully")
    inner class DetailsLoadedSuccessfully {

        private lateinit var repository: FootballTeamRepository
        private val imageBaseUrl = "https://example.com"

        @BeforeEach
        fun setup() {
            repository = RemoteFootballTeamRepository(
                SuccessApi(),
                SuccessDao()
            )
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

private class SuccessDao : TeamsDao {
    override fun getAll(): Single<List<TeamEntity>> {
        throw NotImplementedError("Doesn't need in this test")
    }

    override fun getById(teamId: Int): Maybe<TeamEntity> {
        throw NotImplementedError("Doesn't need in this test")
    }

    override fun insertOrIgnoreTeams(teams: List<TeamEntity>) {
        // no-op. successfully inserted into db
    }

}
private class SuccessApi : TeamsApi {
    override fun getTeams(leagueId: Int, year: String): Single<ApiEnvelope<List<ApiFullTeam>>> {
        throw NotImplementedError("Doesn't need in this test")
    }

    override fun getTeamDetails(id: Int): Single<ApiEnvelope<List<ApiFullTeam>>> {
        return Single.fromCallable {
            ApiEnvelope(
                emptyList(),
                listOf(
                    ApiFullTeam(
                        ApiTeam(
                            id = 33,
                            name = "Manchester United",
                            code = "MUN",
                            country = "England",
                            founded = 1878,
                            national = false,
                            logo = "https://example.com/football/teams/33.png"
                        ),
                        ApiVenue(
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