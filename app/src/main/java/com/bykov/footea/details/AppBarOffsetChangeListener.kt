package com.bykov.footea.details

import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

internal class AppBarOffsetChangeListener(
    private val onOffsetChanged: (state: State, offsetRange: Float) -> Unit = { _: State, _: Float -> },
    private val onStateChanged: (appBarLayout: AppBarLayout, state: State) -> Unit = { _: AppBarLayout, _: State -> },
) : AppBarLayout.OnOffsetChangedListener {

    enum class State {
        EXPANDED, COLLAPSED, IDLE
    }

    private var _currentState = State.IDLE
    private var _currentOffset = 0f

    val currentOffset: Float
        get() = _currentOffset

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        if (verticalOffset == 0) {
            setCurrentStateAndNotify(appBarLayout, State.EXPANDED)
        } else if (abs(verticalOffset) >= appBarLayout.totalScrollRange) {
            setCurrentStateAndNotify(appBarLayout, State.COLLAPSED)
        } else {
            setCurrentStateAndNotify(appBarLayout, State.IDLE)
        }
        val offset = abs(verticalOffset / appBarLayout.totalScrollRange.toFloat())
        if (offset != _currentOffset) {
            _currentOffset = offset
            onOffsetChanged(_currentState, offset)
        }
    }

    private fun setCurrentStateAndNotify(appBarLayout: AppBarLayout, state: State) {
        if (_currentState !== state) {
            onStateChanged(appBarLayout, state)
        }
        _currentState = state
    }
}