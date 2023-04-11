package com.bykov.footea.teamlineupview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bykov.footea.teamlineupview.model.Player

class PlayerAvatarNameView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val username: TextView by lazy(LazyThreadSafetyMode.NONE) {
        findViewById(R.id.username)
    }

    private val userAvatar: ImageView by lazy(LazyThreadSafetyMode.NONE) {
        findViewById(R.id.user_avatar)
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.player_avatar_name_view, this, true)
        orientation = VERTICAL
    }

    fun bind(player: Player) {
        username.text = player.name
        Glide.with(userAvatar)
            .load(player.avatarUrl)
            .placeholder(R.drawable.ic_avatar_placeholder)
            .into(userAvatar)
    }
}