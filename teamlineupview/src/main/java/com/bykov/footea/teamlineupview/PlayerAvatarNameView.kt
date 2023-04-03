package com.bykov.footea.teamlineupview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.bykov.footea.teamlineupview.databinding.PlayerAvatarNameViewBinding
import com.bykov.footea.teamlineupview.model.Player

class PlayerAvatarNameView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: PlayerAvatarNameViewBinding = PlayerAvatarNameViewBinding.inflate(
        LayoutInflater.from(context),
        this
    )

    init {
        orientation = VERTICAL
    }

    fun bind(player: Player) {
        binding.username.text = player.name
        binding.userAvatar.setImageResource(R.drawable.ic_avatar_placeholder)
    }
}