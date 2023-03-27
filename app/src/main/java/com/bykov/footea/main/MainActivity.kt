package com.bykov.footea.main

import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bykov.footea.FooteaApplication
import com.bykov.footea.R
import com.bykov.footea.details.showTeamDetails
import com.bykov.footea.di.AppContainer
import com.bykov.footea.di.TeamListContainer
import com.bykov.footea.extensions.gone
import com.bykov.footea.extensions.show
import com.bykov.footea.extensions.toast
import com.bykov.footea.models.FootballTeam
import com.bykov.footea.ui.FootballTeamItem
import com.bykov.footea.ui.OnTeamItemClickListener

class MainActivity : AppCompatActivity(), MainContract.View, OnTeamItemClickListener {

    private val teamsRecycler: RecyclerView by lazy(LazyThreadSafetyMode.NONE) {
        findViewById(R.id.teams_recycler)
    }

    private val progressBar: ProgressBar by lazy(LazyThreadSafetyMode.NONE) {
        findViewById(R.id.progress_bar)
    }

    private lateinit var presenter: MainContract.Presenter
    private lateinit var adapter: TeamListAdapter
    private lateinit var appContainer: AppContainer

    // region Activity Callbacks
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appContainer = (application as FooteaApplication).appContainer
        appContainer.teamListContainer = TeamListContainer(
            appContainer.preferencesRepository,
            appContainer.localRepository,
            appContainer.remoteRepository,
        )
        presenter = appContainer.teamListContainer!!.presenter(this)
        adapter = appContainer.teamListContainer!!.adapter(this, this)

        teamsRecycler.adapter = adapter

        presenter.loadTeams()
    }

    override fun onDestroy() {
        presenter.destroy()
        appContainer.teamListContainer = null
        super.onDestroy()
    }
    // endregion

    // region MainContract.View
    override fun showLoading() {
        progressBar.show()
    }

    override fun showContent(teams: List<FootballTeamItem>) {
        progressBar.gone()
        teamsRecycler.show()
        adapter.setItems(teams)
    }

    override fun showError() {
        progressBar.gone()
        toast(getString(R.string.default_error))
    }
    // endregion

    // region OnTeamItemClickListener
    override fun onTeamItemClicked(team: FootballTeam) {
        showTeamDetails(team.id, team.name)
    }
    // endregion
}
