package ru.bykov.footballteams.details

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import ru.bykov.footballteams.R
import ru.bykov.footballteams.di.TeamDetailsInjection
import ru.bykov.footballteams.extensions.toast
import ru.bykov.footballteams.models.FootballTeamDetails

private const val EXTRA_TEAM_ID = "extra_team_id"
private const val EXTRA_TEAM_NAME = "extra_team_name"

private const val NO_TEAM_ID = -1

fun Activity.showTeamDetails(teamId: Int, teamName: String) {
    startActivity(
        Intent(this, TeamDetailsActivity::class.java).apply {
            putExtra(EXTRA_TEAM_ID, teamId)
            putExtra(EXTRA_TEAM_NAME, teamName)
        }
    )
}

class TeamDetailsActivity : AppCompatActivity(), TeamDetailsContract.View {

    private val injection: TeamDetailsInjection by lazy(LazyThreadSafetyMode.NONE) {
        TeamDetailsInjection(this)
    }

    private lateinit var presenter: TeamDetailsContract.Presenter

    private val teamBadge: ImageView by lazy(LazyThreadSafetyMode.NONE) {
        findViewById(R.id.team_badge)
    }

    private val teamNameLabel: TextView by lazy(LazyThreadSafetyMode.NONE) {
        findViewById(R.id.team_name_label)
    }

    private val national: TextView by lazy(LazyThreadSafetyMode.NONE) {
        findViewById(R.id.national)
    }

    private val venue: TextView by lazy(LazyThreadSafetyMode.NONE) {
        findViewById(R.id.venue)
    }

    // region Activity Callbacks
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_details)

        supportActionBar?.title = intent.getStringExtra(EXTRA_TEAM_NAME)

        presenter = injection.presenter
        presenter.loadTeamDetails(intent.getIntExtra(EXTRA_TEAM_ID, NO_TEAM_ID))
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }
    // endregion

    // region TeamDetailsContract.View
    override fun showDetails(details: FootballTeamDetails) {
        Glide.with(this)
            .load(details.badgeUrl)
            .error(R.drawable.ic_launcher_background)
            .into(teamBadge)
        teamNameLabel.text = details.name
        national.text = details.national.toString()
        venue.text = details.venue
    }

    override fun showError(message: String?) {
        toast(message ?: getString(R.string.default_error))
    }
    // endregion

}