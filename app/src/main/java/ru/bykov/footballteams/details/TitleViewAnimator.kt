package ru.bykov.footballteams.details

import android.graphics.Paint
import android.os.Handler
import android.os.Looper
import android.util.TypedValue
import android.widget.TextView
import com.google.android.material.appbar.AppBarLayout

class TitleViewAnimator {

    private var appBarLayout: AppBarLayout? = null
    private var expandedTeamNameView: TextView? = null
    private var collapsedTeamNameView: TextView? = null

    private val collapsedTextPosition = FloatArray(2)
    private val expandedTextPosition = FloatArray(2)

    private var originalTitleTextSize = 0f

    fun onViewsCreated(
        teamNameView: TextView,
        toolbarTitleView: TextView
    ) {
        expandedTeamNameView = teamNameView
        collapsedTeamNameView = toolbarTitleView
        originalTitleTextSize = teamNameView.textSize
    }

    fun onWindowFocused(offsetRange: Float) {
        resetViewsLocations(offsetRange, false)
    }

    fun onTitleTextChanged(offsetRange: Float) {
        resetViewsLocations(offsetRange, true)
    }

    fun onViewsDestroyed() {
        expandedTeamNameView = null
        collapsedTeamNameView = null
        appBarLayout = null
    }

    fun moveTitleView(offsetRange: Float) {
        val collapsedTeamName = requireNotNull(collapsedTeamNameView)
        val expandedTeamName = requireNotNull(expandedTeamNameView)

        val newTextSize: Float = originalTitleTextSize - (originalTitleTextSize - collapsedTeamName.textSize) * offsetRange

        val expandedCenter = expandedTeamName.height / 2
        val collapsedCenter = collapsedTeamName.height / 2
        val centerDiff = collapsedCenter - expandedCenter
        val xTitleOffset: Float = (collapsedTextPosition[0] - expandedTextPosition[0]) * offsetRange
        val yTitleOffset: Float = (collapsedTextPosition[1] - expandedTextPosition[1] + centerDiff) * offsetRange

        expandedTeamName.setTextSize(TypedValue.COMPLEX_UNIT_PX, newTextSize)
        expandedTeamName.translationX = xTitleOffset
        expandedTeamName.translationY = yTitleOffset
    }


    private fun resetViewsLocations(offsetRange: Float, shouldMoveTitle: Boolean) {
        val collapsedTeamName = requireNotNull(collapsedTeamNameView)
        val expandedTeamName = requireNotNull(expandedTeamNameView)

        val collapsedTitleLocation = IntArray(2)
        collapsedTeamName.getLocationOnScreen(collapsedTitleLocation)
        collapsedTextPosition[0] = collapsedTitleLocation[0].toFloat()
        collapsedTextPosition[1] = collapsedTitleLocation[1].toFloat()

        val paint = Paint(expandedTeamName.paint)
        val newTextWidth: Float = paint.measureText(expandedTeamName.text.toString())
        paint.textSize = originalTitleTextSize
        val originTextWidth: Float = paint.measureText(expandedTeamName.text.toString())

        val expandedTitleLocation = IntArray(2)
        expandedTeamName.getLocationOnScreen(expandedTitleLocation)

        val correction = if (collapsedTeamName.width > newTextWidth) {
            (originTextWidth - newTextWidth) / 2f
        } else {
            0f
        }
        expandedTextPosition[0] = expandedTitleLocation[0] - expandedTeamName.translationX - correction
        expandedTextPosition[1] = expandedTitleLocation[1] - expandedTeamName.translationY

        if (shouldMoveTitle) {
            Handler(Looper.getMainLooper()).post { moveTitleView(offsetRange) }
        }
    }
}