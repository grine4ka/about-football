package ru.bykov.footballteams.main

import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import ru.bykov.footballteams.R
import ru.bykov.footballteams.details.showTeamDetails
import ru.bykov.footballteams.di.TeamListInjection
import ru.bykov.footballteams.extensions.gone
import ru.bykov.footballteams.extensions.show
import ru.bykov.footballteams.extensions.toast
import ru.bykov.footballteams.models.FootballTeam
import ru.bykov.footballteams.ui.FootballTeamItem
import ru.bykov.footballteams.ui.OnTeamItemClickListener

class MainActivity : AppCompatActivity(), MainContract.View, OnTeamItemClickListener {

    private val injection: TeamListInjection by lazy(LazyThreadSafetyMode.NONE) {
        TeamListInjection(this, this)
    }

    private val adapter: TeamListAdapter by lazy(LazyThreadSafetyMode.NONE) {
        injection.adapter
    }

    private val teamsRecycler: RecyclerView by lazy(LazyThreadSafetyMode.NONE) {
        findViewById(R.id.teams_recycler)
    }

    private val swipeRefreshLayout: SwipeRefreshLayout by lazy(LazyThreadSafetyMode.NONE) {
        findViewById(R.id.swipe_refresh_layout)
    }

    private val progressBar: ProgressBar by lazy(LazyThreadSafetyMode.NONE) {
        findViewById(R.id.progress_bar)
    }

    private lateinit var presenter: MainContract.Presenter

    // region Activity Callbacks
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        teamsRecycler.adapter = adapter
        teamsRecycler.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        swipeRefreshLayout.setOnRefreshListener {
            presenter.refreshTeams()
        }

        presenter = injection.presenter
        presenter.loadTeams()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }
    // endregion

    // region MainContract.View
    override fun showLoading() {
        progressBar.show()
    }

    override fun showContent(teams: List<FootballTeamItem>) {
        swipeRefreshLayout.isRefreshing = false
        progressBar.gone()
        teamsRecycler.show()
        adapter.setItems(teams)
    }

    override fun showError() {
        progressBar.gone()
        swipeRefreshLayout.isRefreshing = false
        toast(getString(R.string.default_error))
    }
    // endregion

    // region OnTeamItemClickListener
    override fun onTeamItemClicked(team: FootballTeam) {
        showTeamDetails(team.id, team.name)
    }
    // endregion
}
