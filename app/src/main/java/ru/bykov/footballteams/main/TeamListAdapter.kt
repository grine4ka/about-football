package ru.bykov.footballteams.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.bykov.footballteams.ui.BaseViewHolder
import ru.bykov.footballteams.ui.DisplayableItem
import ru.bykov.footballteams.ui.FootballTeamsViewHolderFactory

class TeamListAdapter(
    private val items: MutableList<DisplayableItem> = mutableListOf(),
    private val holderFactory: FootballTeamsViewHolderFactory
) : RecyclerView.Adapter<BaseViewHolder<DisplayableItem>>() {

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<DisplayableItem> {
        return holderFactory.createHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<DisplayableItem>, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemViewType(position: Int): Int = items[position].viewType

    fun setItems(newItems: List<DisplayableItem>) {
        items.clear()
        items.addAll(newItems)
        notifyItemRangeChanged(0, items.size)
    }
}