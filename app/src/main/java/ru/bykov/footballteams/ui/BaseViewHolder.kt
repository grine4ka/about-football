package ru.bykov.footballteams.ui

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.bykov.footballteams.R

abstract class BaseViewHolder<in T : DisplayableItem>(
    protected val view: View
) : RecyclerView.ViewHolder(view) {
    open fun bind(item: T) = Unit
}

class FootballTeamViewHolder(
    itemView: View,
    itemClickListener: (Int) -> Unit
) : BaseViewHolder<FootballTeamItem>(itemView) {

    private val teamImage: AppCompatImageView by lazy(LazyThreadSafetyMode.NONE) {
        itemView.findViewById(R.id.team_image)
    }

    init {
        itemView.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                itemClickListener(position)
            }
        }
    }

    override fun bind(item: FootballTeamItem) {
        Glide.with(itemView.context)
            .asBitmap()
            .load(item.footballTeam.logoUrl)
            .error(R.drawable.ic_launcher_background)
            .into(teamImage)
    }
}


