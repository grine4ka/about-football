package ru.bykov.footballteams.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import ru.bykov.footballteams.R
import ru.bykov.footballteams.databinding.ItemFootballTeamBinding

interface FootballTeamsViewHolderFactory {

    fun createHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DisplayableItem, ViewBinding>

    class Impl(
        context: Context,
        private val itemClickListener: (Int) -> Unit
    ) : FootballTeamsViewHolderFactory {

        private val inflater: LayoutInflater = LayoutInflater.from(context)

        override fun createHolder(
            parent: ViewGroup,
            viewType: Int
        ): BaseViewHolder<DisplayableItem, ViewBinding> {
            val viewHolder = when (viewType) {
                R.layout.item_football_team -> FootballTeamViewHolder(
                    ItemFootballTeamBinding.inflate(inflater),
                    itemClickListener
                )
                else -> throw IllegalArgumentException("Not compatible view type: $viewType")
            }
            return viewHolder as BaseViewHolder<DisplayableItem, ViewBinding>
        }
    }
}
