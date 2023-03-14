@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")

package ru.bykov.footballteams.main

import io.kotest.matchers.shouldBe
import io.reactivex.Single
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import ru.bykov.footballteams.RxSchedulersOverrideRule
import ru.bykov.footballteams.models.FootballTeam
import ru.bykov.footballteams.ui.FootballTeamItem
import ru.bykov.footballteams.usecase.UseCase

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

        private val getTeams = SuccessGetTeams()
        private lateinit var presenter: MainContract.Presenter

        @BeforeEach
        fun setup() {
            presenter = MainPresenter(getTeams, view)
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

        private val getTeams = FailedGetTeams()
        private lateinit var presenter: MainContract.Presenter

        @BeforeEach
        fun setup() {
            presenter = MainPresenter(getTeams, view)
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

private class MockView : MainContract.View {

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

private class SuccessGetTeams : UseCase<Single<List<FootballTeam>>, Boolean> {

    override fun invoke(forceUpdate: Boolean): Single<List<FootballTeam>> {
        return Single.just(listOf(FootballTeam(1, "FC Barcelona", "")))
    }
}

private class FailedGetTeams : UseCase<Single<List<FootballTeam>>, Boolean> {

    override fun invoke(forceUpdate: Boolean): Single<List<FootballTeam>> {
        return Single.error(Throwable("Data not loaded"))
    }
}

private enum class MainState {
    LOADING, CONTENT, ERROR, UNDEFINED
}