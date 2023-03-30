package com.bykov.footea.ui.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.Rect
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import com.bykov.footea.R

/**
 * Draws half of the field.
 *
 * Inspired by https://proandroiddev.com/building-a-team-lineup-view-on-android-daaf27e3901e
 */
class HalfFieldDrawable(context: Context) : Drawable() {

    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = context.resources.getDimension(R.dimen.field_line_width)
        color = ResourcesCompat.getColor(context.resources, R.color.field_line, context.theme)
        style = Paint.Style.STROKE
    }

    private val rect = Rect()
    private val padding = context.resources.getDimensionPixelSize(R.dimen.field_line_padding)
    private val halfInnerGoalWidth = context.resources.getDimensionPixelSize(R.dimen.field_inner_goal_width)
    private val halfInnerGoalHeight = context.resources.getDimensionPixelSize(R.dimen.field_inner_goal_height)
    private val halfGoalWidth = context.resources.getDimensionPixelSize(R.dimen.field_goal_width)
    private val halfGoalHeight = context.resources.getDimensionPixelSize(R.dimen.field_goal_height)
    private val cornerSize = context.resources.getDimensionPixelSize(R.dimen.field_corner_size)
    private val centerCircleSize = context.resources.getDimensionPixelSize(R.dimen.field_center_circle_size)
    private val centerInnerCircleSize = context.resources.getDimensionPixelSize(R.dimen.field_center_inner_circle_size)

    override fun draw(canvas: Canvas) {
        val width = bounds.width()
        val height = bounds.height()
        canvas.apply {
            // draw field lines
            rect.set(padding, padding, width - padding, height - padding)
            drawRect(rect, linePaint)
            // draw goals
            rect.set(
                width / 2 - halfInnerGoalWidth,
                padding,
                width / 2 + halfInnerGoalWidth,
                padding + halfInnerGoalHeight
            )
            drawRect(rect, linePaint)
            rect.set(
                width / 2 - halfGoalWidth,
                padding,
                width / 2 + halfGoalWidth,
                padding + halfGoalHeight
            )
            drawRect(rect, linePaint)
            // draw corners
            drawArc(
                padding.toFloat() - cornerSize,
                padding.toFloat() - cornerSize,
                (padding + cornerSize).toFloat(),
                (padding + cornerSize).toFloat(),
                0f,
                90f,
                false,
                linePaint
            )
            drawArc(
                (width - padding - cornerSize).toFloat(),
                padding.toFloat() - cornerSize,
                (width - padding + cornerSize).toFloat(),
                (padding + cornerSize).toFloat(),
                90f,
                90f,
                false,
                linePaint
            )
            // draw center circle
            drawArc(
                (width / 2 - centerCircleSize).toFloat(),
                (height - padding - centerCircleSize).toFloat(),
                (width / 2 + centerCircleSize).toFloat(),
                (height - padding + centerCircleSize).toFloat(),
                180f,
                180f,
                false,
                linePaint
            )
            linePaint.style = Paint.Style.FILL
            drawArc(
                (width / 2 - centerInnerCircleSize).toFloat(),
                (height - padding - centerInnerCircleSize).toFloat(),
                (width / 2 + centerInnerCircleSize).toFloat(),
                (height - padding + centerInnerCircleSize).toFloat(),
                180f,
                180f,
                false,
                linePaint
            )
        }
    }

    override fun setAlpha(alpha: Int) {
        // no-op
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        // no-op
    }

    @Deprecated("Deprecated in Java",
        ReplaceWith("PixelFormat.OPAQUE", "android.graphics.PixelFormat")
    )
    override fun getOpacity(): Int {
        return PixelFormat.OPAQUE
    }
}
