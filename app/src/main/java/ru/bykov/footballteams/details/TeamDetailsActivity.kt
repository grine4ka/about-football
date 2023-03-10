package ru.bykov.footballteams.details

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.app.Activity
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import ru.bykov.footballteams.FooteaApplication
import ru.bykov.footballteams.R
import ru.bykov.footballteams.di.AppContainer
import ru.bykov.footballteams.di.TeamDetailsContainer
import ru.bykov.footballteams.extensions.getColorFromAttr
import ru.bykov.footballteams.extensions.toast
import ru.bykov.footballteams.models.FootballTeamDetails
import ru.bykov.footballteams.ui.PaletteRequestListener


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

    private lateinit var presenter: TeamDetailsContract.Presenter
    private lateinit var appContainer: AppContainer

    private val appBarLayout: AppBarLayout by lazy(LazyThreadSafetyMode.NONE) {
        findViewById(R.id.app_bar_layout)
    }

    private val collapsingToolbarLayout: CollapsingToolbarLayout by lazy(LazyThreadSafetyMode.NONE) {
        findViewById(R.id.collapsing_toolbar)
    }

    private val toolbar: Toolbar by lazy(LazyThreadSafetyMode.NONE) {
        findViewById(R.id.toolbar)
    }

    private val toolbarTitle: TextView by lazy(LazyThreadSafetyMode.NONE) {
        findViewById(R.id.toolbar_title)
    }

    private val teamBadge: ImageView by lazy(LazyThreadSafetyMode.NONE) {
        findViewById(R.id.team_badge)
    }

    private val teamName: TextView by lazy(LazyThreadSafetyMode.NONE) {
        findViewById(R.id.team_name)
    }

    private val national: TextView by lazy(LazyThreadSafetyMode.NONE) {
        findViewById(R.id.national)
    }

    private val venue: TextView by lazy(LazyThreadSafetyMode.NONE) {
        findViewById(R.id.venue)
    }

    private val appBarOffsetChangeListener = AppBarOffsetChangeListener(
        onOffsetChanged = { _, offsetRange ->
            teamBadge.alpha = 1 - offsetRange
            national.alpha = 1 - offsetRange
            titleViewAnimator.moveTitleView(offsetRange)
        }
    )

    private val titleViewAnimator: TitleViewAnimator = TitleViewAnimator()

    // region Activity Callbacks
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_details)

        appContainer = (application as FooteaApplication).appContainer
        appContainer.teamDetailsContainer = TeamDetailsContainer(appContainer.repository)
        presenter = appContainer.teamDetailsContainer!!.presenter(this)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        appBarLayout.addOnOffsetChangedListener(appBarOffsetChangeListener)
        titleViewAnimator.onViewsCreated(teamName, toolbarTitle)

        presenter.loadTeamDetails(intent.getIntExtra(EXTRA_TEAM_ID, NO_TEAM_ID))
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            titleViewAnimator.onWindowFocused(appBarOffsetChangeListener.currentOffset)
        }
    }
    override fun onDestroy() {
        presenter.destroy()
        titleViewAnimator.onViewsDestroyed()
        appBarLayout.removeOnOffsetChangedListener(appBarOffsetChangeListener)
        appContainer.teamDetailsContainer = null
        super.onDestroy()
    }
    // endregion

    // region TeamDetailsContract.View
    override fun showDetails(details: FootballTeamDetails) {
        Glide.with(this)
            .asBitmap()
            .listener(PaletteRequestListener(::animateColorChange))
            .load(details.badgeUrl)
            .error(R.drawable.ic_launcher_background)
            .into(teamBadge)
        teamName.text = details.name
        national.text = details.country
        venue.text = details.venue
        titleViewAnimator.onTitleTextChanged(appBarOffsetChangeListener.currentOffset)
    }

    override fun showError(message: String?) {
        toast(message ?: getString(R.string.default_error))
    }
    // endregion

    private fun animateColorChange(
        domainColor: Int,
        lightColor: Int,
        textColor: Int
    ) {
        animateDomainColor(domainColor)
        animateLightColor(lightColor)
        animateTextColor(textColor)
    }

    private fun animateDomainColor(domainColor: Int) {
        val colorFrom = getColorFromAttr(R.attr.colorPrimary)
        animateColor(colorFrom, domainColor) { color ->
            appBarLayout.setBackgroundColor(color)
            toolbar.setBackgroundColor(color)
            collapsingToolbarLayout.contentScrim = ColorDrawable(color)
        }
    }

    private fun animateLightColor(lightColor: Int) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        val colorFrom = getColorFromAttr(R.attr.colorPrimaryVariant)
        animateColor(colorFrom, lightColor) { color ->
            collapsingToolbarLayout.statusBarScrim = ColorDrawable(color)
            window.navigationBarColor = color
            window.statusBarColor = color
        }
    }

    private fun animateTextColor(textColor: Int) {
        val colorFrom = teamName.currentTextColor
        animateColor(colorFrom, textColor) { color ->
            teamName.setTextColor(color)
            national.setTextColor(color)
        }
    }

    private fun animateColor(@ColorInt colorFrom: Int, colorTo: Int, onColorChange: (Int) -> Unit) {
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo).apply {
            duration = 250 // milliseconds
            addUpdateListener { animator ->
                onColorChange(animator.animatedValue as Int)
            }
        }
        colorAnimation.start()
    }
}
