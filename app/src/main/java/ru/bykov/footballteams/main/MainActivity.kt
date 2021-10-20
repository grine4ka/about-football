package ru.bykov.footballteams.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.bykov.footballteams.R
import ru.bykov.footballteams.databinding.ActivityMainBinding
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

    private val viewBinding: ActivityMainBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var presenter: MainContract.Presenter

    // region Activity Callbacks
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        viewBinding.teamsRecycler.adapter = adapter

        viewBinding.swipeRefreshLayout.setOnRefreshListener {
            presenter.loadTeams()
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
        viewBinding.progressBar.show()
    }

    override fun showContent(teams: List<FootballTeamItem>) {
        viewBinding.swipeRefreshLayout.isRefreshing = false
        viewBinding.progressBar.gone()
        viewBinding.teamsRecycler.show()
        adapter.setItems(teams)
    }

    override fun showError() {
        viewBinding.progressBar.gone()
        toast(getString(R.string.default_error))
    }
    // endregion

    // region OnTeamItemClickListener
    override fun onTeamItemClicked(team: FootballTeam) {
        toast("Team ${team.name} clicked")
        showTeamDetails(team.id, team.name)
    }
    // endregion
}