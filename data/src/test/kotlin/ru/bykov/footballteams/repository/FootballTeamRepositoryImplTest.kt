package ru.bykov.footballteams.repository

import io.kotest.matchers.string.shouldStartWith
import io.reactivex.Single
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import ru.bykov.footballteams.entity.FootballTeamDetailsEntity
import ru.bykov.footballteams.entity.FootballTeamEntity
import ru.bykov.footballteams.entity.Gender
import ru.bykov.footballteams.models.FootballTeamDetails
import ru.bykov.footballteams.network.TeamsApi

internal class FootballTeamRepositoryImplTest {

    @Nested
    @DisplayName("Given details loaded successfully")
    inner class DetailsLoadedSuccessfully {

        private lateinit var repository: FootballTeamRepository
        private val baseUrl = "https://example.com"

        @BeforeEach
        fun setup() {
            repository = RemoteFootballTeamRepository(baseUrl, SuccessApi())
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
                teamDetails.badgeUrl shouldStartWith baseUrl
            }
        }
    }
}

private class SuccessApi : TeamsApi {
    override fun getTeams(): Single<List<FootballTeamEntity>> {
        throw NotImplementedError("Doesn't need in this test")
    }

    override fun getTeamDetails(id: Int): Single<FootballTeamDetailsEntity> {
        return Single.fromCallable {
            FootballTeamDetailsEntity(1, "FC Barcelona", Gender.MALE, false, "Mesque un club", "/teams/1/badge.png")
        }
    }
}