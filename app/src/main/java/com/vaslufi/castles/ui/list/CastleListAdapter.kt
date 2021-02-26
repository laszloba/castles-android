package com.vaslufi.castles.ui.list

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vaslufi.castles.model.CastleListItemViewModel

class CastleListAdapter(
    private val context: Context
) : ListAdapter<CastleListItemViewModel, CastleListAdapter.CastleListItemViewHolder>(
    CastleListItemDiffCallback
) {
    var items: List<CastleListItemViewModel> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastleListItemViewHolder =
        CastleListItemViewHolder(CastleListItemView(context))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CastleListItemViewHolder, position: Int) {
        holder.view.bind(items[position])
    }

    class CastleListItemViewHolder(val view: CastleListItemView) : RecyclerView.ViewHolder(view)

    object CastleListItemDiffCallback : DiffUtil.ItemCallback<CastleListItemViewModel>() {
        override fun areItemsTheSame(
            oldItem: CastleListItemViewModel,
            newItem: CastleListItemViewModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CastleListItemViewModel,
            newItem: CastleListItemViewModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}
