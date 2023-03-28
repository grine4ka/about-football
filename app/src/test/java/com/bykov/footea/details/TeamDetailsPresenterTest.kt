@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")

package com.bykov.footea.details

import com.bykov.footea.RxSchedulersOverrideRule
import com.bykov.footea.models.FootballTeamDetails
import com.bykov.footea.usecase.UseCase
import io.kotest.matchers.shouldBe
import io.reactivex.Single
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith

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

        private val getTeamById = SuccessGetTeamById()
        private lateinit var presenter: TeamDetailsContract.Presenter

        @BeforeEach
        fun setup() {
            presenter = TeamDetailsPresenter(getTeamById, view)
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

        private val getTeamById = FailedGetTeamById()
        private lateinit var presenter: TeamDetailsContract.Presenter

        @BeforeEach
        fun setup() {
            presenter = TeamDetailsPresenter(getTeamById, view)
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


private class SuccessGetTeamById : UseCase<Single<FootballTeamDetails>, Int> {

    override fun invoke(teamId: Int): Single<FootballTeamDetails> {
        return Single.just(FootballTeamDetails(1, "FC Barcelona", "Spain", "Mesque un club", ""))
    }
}

private class FailedGetTeamById : UseCase<Single<FootballTeamDetails>, Int> {

    override fun invoke(teamId: Int): Single<FootballTeamDetails> {
        return Single.error(Throwable("Data not loaded"))
    }
}

private enum class DetailsState {
    CONTENT, ERROR, UNDEFINED
}