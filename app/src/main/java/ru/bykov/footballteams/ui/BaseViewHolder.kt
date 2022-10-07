package ru.bykov.footballteams.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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

    private val teamName: TextView by lazy(LazyThreadSafetyMode.NONE) {
        itemView.findViewById(R.id.team_name)
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
        teamName.text = item.footballTeam.name
    }
}


