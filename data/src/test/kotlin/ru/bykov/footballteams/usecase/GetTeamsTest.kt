package ru.bykov.footballteams.usecase

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Completable
import io.reactivex.Single
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.junit.jupiter.api.Test
import ru.bykov.footballteams.models.FootballTeam
import ru.bykov.footballteams.repository.FootballTeamRepository
import ru.bykov.footballteams.repository.StringPreferencesRepository

internal class GetTeamsTest {

    private val localRepository = mockk<FootballTeamRepository>()
    private val remoteRepository = mockk<FootballTeamRepository>()

    @Test
    internal fun `should load remote teams when first time get teams`() {
        // given
        val expectedTeams = listOf(
            FootballTeam(1, "Team 1", "Logo 1"),
            FootballTeam(2, "Team 2", "Logo 2")
        )
        every { remoteRepository.teams() } returns Single.just(expectedTeams)
        val getTeams = GetTeams(
            DistantPastLastUpdateDateRepository(),
            localRepository,
            remoteRepository
        )

        // when
        val teams = getTeams.invoke().test()


        // then
        teams.assertValue(expectedTeams)

        verify(exactly = 0) { localRepository.teams() }
        verify(exactly = 1) { remoteRepository.teams() }
    }

    @Test
    internal fun `should load local teams when last update date is within one day`() {
        // given
        val expectedTeams = listOf(
            FootballTeam(1, "Team 1", "Logo 1"),
            FootballTeam(2, "Team 2", "Logo 2")
        )
        every { localRepository.teams() } returns Single.just(expectedTeams)
        val getTeams = GetTeams(
            WithinDayLastUpdateDateRepository(),
            localRepository,
            remoteRepository
        )

        // when
        val teams = getTeams().test()

        // then
        teams.assertValue(expectedTeams)
        verify(exactly = 1) { localRepository.teams() }
        verify(exactly = 0) { remoteRepository.teams() }
    }
}

private class WithinDayLastUpdateDateRepository : StringPreferencesRepository {

    override fun getPref(key: String, defaultValue: String): Single<String> {
        return Single.just(Clock.System.now().toString())
    }

    override fun setPref(key: String, value: String): Completable {
        return Completable.complete()
    }
}

private class DistantPastLastUpdateDateRepository : StringPreferencesRepository {
    override fun getPref(key: String, defaultValue: String): Single<String> {
        return Single.just(Instant.DISTANT_PAST.toString())
    }

    override fun setPref(key: String, value: String): Completable {
        return Completable.complete()
    }
}