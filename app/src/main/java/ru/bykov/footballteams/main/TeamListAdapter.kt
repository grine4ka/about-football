package ru.bykov.footballteams.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import ru.bykov.footballteams.ui.BaseViewHolder
import ru.bykov.footballteams.ui.DisplayableItem
import ru.bykov.footballteams.ui.FootballTeamsViewHolderFactory

/**
 * @author Grigorii Bykov
 */
class TeamListAdapter(
    private val items: MutableList<DisplayableItem> = mutableListOf(),
    private val holderFactory: FootballTeamsViewHolderFactory
) : RecyclerView.Adapter<BaseViewHolder<DisplayableItem, ViewBinding>>() {

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<DisplayableItem, ViewBinding> {
        return holderFactory.createHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<DisplayableItem, ViewBinding>, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemViewType(position: Int): Int = items[position].viewType

    fun setItems(newItems: List<DisplayableItem>) {
        items.clear()
        items.addAll(newItems)
        notifyItemRangeChanged(0, items.size)
    }
}