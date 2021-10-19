package ru.bykov.footballteams.main

import io.kotest.matchers.shouldBe
import io.reactivex.Single
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import ru.bykov.footballteams.RxSchedulersOverrideRule
import ru.bykov.footballteams.models.FootballTeam
import ru.bykov.footballteams.models.FootballTeamDetails
import ru.bykov.footballteams.models.FootballTeamRepository
import ru.bykov.footballteams.models.Gender
import ru.bykov.footballteams.ui.FootballTeamItem

@ExtendWith(RxSchedulersOverrideRule::class)
internal class MainPresenterTest {

    private val view: MockView = MockView()

    @AfterEach
    fun resetMocks() {
        view.state = MainState.UNDEFINED
    }

    @Nested
    @DisplayName("Given teams loaded successfully")
    inner class TeamsLoadedSuccessfully {

        private val repository: FootballTeamRepository = SuccessRepository()
        private lateinit var presenter: MainContract.Presenter

        @BeforeEach
        fun setup() {
            presenter = MainPresenter(repository, view)
        }

        @Nested
        @DisplayName("When loadTeams()")
        inner class OnTeamsLoad {

            @BeforeEach
            fun setup() {
                presenter.loadTeams()
            }

            @Test
            @DisplayName("Then view shows content")
            internal fun viewShowsContent() {
                view.state shouldBe MainState.CONTENT
            }
        }
    }

    @Nested
    @DisplayName("Given teams failed to load")
    inner class TeamsLoadFailed {

        private val repository: FootballTeamRepository = FailedRepository()
        private lateinit var presenter: MainContract.Presenter

        @BeforeEach
        fun setup() {
            presenter = MainPresenter(repository, view)
        }

        @Nested
        @DisplayName("When loadTeams()")
        inner class OnTeamsLoad {

            @BeforeEach
            fun setup() {
                presenter.loadTeams()
            }

            @Test
            @DisplayName("Then view shows error")
            internal fun viewShowsError() {
                view.state shouldBe MainState.ERROR
            }
        }
    }
}

internal class MockView : MainContract.View {

    var state: MainState = MainState.UNDEFINED

    override fun showLoading() {
        state = MainState.LOADING
    }

    override fun showContent(teams: List<FootballTeamItem>) {
        state = MainState.CONTENT
    }

    override fun showError() {
        state = MainState.ERROR
    }

}

class SuccessRepository : FootballTeamRepository {

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

class FailedRepository : FootballTeamRepository {

    override fun teams(): Single<List<FootballTeam>> {
        return Single.error(Throwable("Data not loaded"))
    }

    override fun details(id: Int): Single<FootballTeamDetails> {
        return Single.error(Throwable("Data not loaded"))
    }
}

internal enum class MainState {
    LOADING, CONTENT, ERROR, UNDEFINED
}