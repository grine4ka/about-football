package ru.bykov.footballteams.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import ru.bykov.footballteams.databinding.ItemFootballTeamBinding

abstract class BaseViewHolder<in T : DisplayableItem, in B : ViewBinding>(
    viewBinding: B
) : RecyclerView.ViewHolder(viewBinding.root) {
    open fun bind(item: T) = Unit
}

class FootballTeamViewHolder(
    private val viewBinding: ItemFootballTeamBinding,
    itemClickListener: (Int) -> Unit
) : BaseViewHolder<FootballTeamItem, ItemFootballTeamBinding>(viewBinding) {

    init {
        itemView.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                itemClickListener(position)
            }
        }
    }

    override fun bind(item: FootballTeamItem) {
        viewBinding.teamName.text = item.footballTeam.name
    }
}


