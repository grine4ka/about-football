package ru.bykov.footballteams

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import ru.bykov.footballteams.databinding.ActivityMainBinding
import ru.bykov.footballteams.di.TeamListInjection
import ru.bykov.footballteams.extensions.async
import ru.bykov.footballteams.extensions.gone
import ru.bykov.footballteams.extensions.show
import ru.bykov.footballteams.extensions.toast
import ru.bykov.footballteams.models.FootballTeamRepository
import ru.bykov.footballteams.ui.FootballTeamItem

class MainActivity : AppCompatActivity() {

    private val injection: TeamListInjection by lazy(LazyThreadSafetyMode.NONE) {
        TeamListInjection(this)
    }

    private val adapter: TeamListAdapter by lazy(LazyThreadSafetyMode.NONE) {
        injection.adapter
    }

    private val repository: FootballTeamRepository by lazy(LazyThreadSafetyMode.NONE) {
        injection.repository
    }

    private val viewBinding: ActivityMainBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        viewBinding.teamsRecycler.adapter = adapter

        viewBinding.swipeRefreshLayout.setOnRefreshListener {
            viewBinding.swipeRefreshLayout.isRefreshing = false
            toast("Feed refreshed")
        }

        compositeDisposable.add(
            loadFootballTeams()
                .subscribe(
                    {
                        viewBinding.progressBar.gone()
                        viewBinding.teamsRecycler.show()
                        adapter.setItems(it)
                    },
                    {
                        viewBinding.progressBar.gone()
                        toast("Error loading football teams!")
                    }
                )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    private fun loadFootballTeams(): Single<List<FootballTeamItem>> {
        return repository.teams()
            .map { teams -> teams.map { FootballTeamItem(it) } }
            .async()
    }

}