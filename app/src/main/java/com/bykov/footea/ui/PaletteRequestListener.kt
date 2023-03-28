package com.bykov.footea.ui

import android.graphics.Bitmap
import androidx.core.graphics.ColorUtils
import androidx.palette.graphics.Palette
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class PaletteRequestListener(
    private val onColorsFound: (Int, Int, Int) -> Unit,
) : RequestListener<Bitmap> {

    override fun onLoadFailed(
        e: GlideException?,
        model: Any,
        target: Target<Bitmap>,
        isFirstResource: Boolean
    ): Boolean {
        return false
    }

    override fun onResourceReady(
        resource: Bitmap,
        model: Any?,
        target: Target<Bitmap>,
        dataSource: DataSource,
        isFirstResource: Boolean
    ): Boolean {
        Palette.from(resource)
            .generate { palette ->
                val domainSwatch = palette?.dominantSwatch
                if (domainSwatch != null) {
                    val domainColor = domainSwatch.rgb
                    val lightColor = ColorUtils.setAlphaComponent(domainColor, 0xA0) // 0.5 alpha
                    val textColor = domainSwatch.bodyTextColor
                    onColorsFound(domainColor, lightColor, textColor)
                }
            }
        return false
    }
}