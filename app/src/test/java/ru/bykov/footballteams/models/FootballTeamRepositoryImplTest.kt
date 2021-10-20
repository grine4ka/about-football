package ru.bykov.footballteams.models

import io.kotest.matchers.string.shouldStartWith
import io.reactivex.Single
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import ru.bykov.footballteams.di.BASE_URL
import ru.bykov.footballteams.network.TeamsApi

internal class FootballTeamRepositoryImplTest {

    @Nested
    @DisplayName("Given details loaded successfully")
    inner class DetailsLoadedSuccessfully {

        private lateinit var repository: FootballTeamRepository

        @BeforeEach
        fun setup() {
            repository = FootballTeamRepository.Impl(SuccessApi())
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
                teamDetails.badgeUrl shouldStartWith BASE_URL
            }
        }
    }
}

private class SuccessApi : TeamsApi {
    override fun getTeams(): Single<List<FootballTeam>> {
        throw NotImplementedError("Doesn't need in this test")
    }

    override fun getTeamDetails(id: Int): Single<FootballTeamDetails> {
        return Single.fromCallable {
            FootballTeamDetails(1, "FC Barcelona", Gender.MALE, false, "Mesque un club", "/teams/1/badge.png")
        }
    }
}