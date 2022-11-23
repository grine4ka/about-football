package ru.bykov.footballteams.details

import io.kotest.matchers.shouldBe
import io.reactivex.Single
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import ru.bykov.footballteams.RxSchedulersOverrideRule
import ru.bykov.footballteams.models.FootballTeam
import ru.bykov.footballteams.models.FootballTeamDetails
import ru.bykov.footballteams.repository.FootballTeamRepository

@ExtendWith(RxSchedulersOverrideRule::class)
internal class TeamDetailsPresenterTest {

    private val view: MockView = MockView()

    @AfterEach
    fun resetMocks() {
        view.state = DetailsState.UNDEFINED
    }

    @Nested
    @DisplayName("Given details loaded successfully")
    inner class DetailsLoadedSuccessfully {

        private val repository: FootballTeamRepository = SuccessRepository()
        private lateinit var presenter: TeamDetailsContract.Presenter

        @BeforeEach
        fun setup() {
            presenter = TeamDetailsPresenter(repository, view)
        }

        @Nested
        @DisplayName("When loadTeamDetails()")
        inner class OnDetailsLoad {

            @BeforeEach
            fun setup() {
                presenter.loadTeamDetails(0)
            }

            @Test
            @DisplayName("Then view shows content")
            internal fun viewShowsContent() {
                view.state shouldBe DetailsState.CONTENT
            }
        }
    }

    @Nested
    @DisplayName("Given details failed to load")
    inner class DetailsLoadFailed {

        private val repository: FootballTeamRepository = FailedRepository()
        private lateinit var presenter: TeamDetailsContract.Presenter

        @BeforeEach
        fun setup() {
            presenter = TeamDetailsPresenter(repository, view)
        }

        @Nested
        @DisplayName("When loadTeamDetails()")
        inner class OnDetailsLoad {

            @BeforeEach
            fun setup() {
                presenter.loadTeamDetails(0)
            }

            @Test
            @DisplayName("Then view shows error")
            internal fun viewShowsError() {
                view.state shouldBe DetailsState.ERROR
            }
        }
    }

}

private class MockView : TeamDetailsContract.View {

    var state: DetailsState = DetailsState.UNDEFINED

    override fun showDetails(details: FootballTeamDetails) {
        state = DetailsState.CONTENT
    }

    override fun showError(message: String?) {
        state = DetailsState.ERROR
    }

}


private class SuccessRepository : FootballTeamRepository {

    override fun teams(forceUpdate: Boolean): Single<List<FootballTeam>> {
        return Single.fromCallable {
            listOf(FootballTeam(1, "FC Barcelona"))
        }
    }

    override fun details(teamId: Int): Single<FootballTeamDetails> {
        return Single.fromCallable {
            FootballTeamDetails(1, "FC Barcelona", "male", false, "Mesque un club", "")
        }
    }
}

private class FailedRepository : FootballTeamRepository {

    override fun teams(forceUpdate: Boolean): Single<List<FootballTeam>> {
        return Single.error(Throwable("Data not loaded"))
    }

    override fun details(teamId: Int): Single<FootballTeamDetails> {
        return Single.error(Throwable("Data not loaded"))
    }
}

private enum class DetailsState {
    CONTENT, ERROR, UNDEFINED
}