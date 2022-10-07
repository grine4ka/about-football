package ru.bykov.footballteams.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.bykov.footballteams.R

interface FootballTeamsViewHolderFactory {

    fun createHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DisplayableItem>

    class Impl(
        context: Context,
        private val itemClickListener: (Int) -> Unit
    ) : FootballTeamsViewHolderFactory {

        private val inflater: LayoutInflater = LayoutInflater.from(context)

        override fun createHolder(
            parent: ViewGroup,
            viewType: Int
        ): BaseViewHolder<DisplayableItem> {
            val viewHolder = when (viewType) {
                R.layout.item_football_team -> FootballTeamViewHolder(
                    inflater.inflate(viewType, parent, false),
                    itemClickListener
                )
                else -> throw IllegalArgumentException("Not compatible view type: $viewType")
            }
            return viewHolder as BaseViewHolder<DisplayableItem>
        }
    }
}
