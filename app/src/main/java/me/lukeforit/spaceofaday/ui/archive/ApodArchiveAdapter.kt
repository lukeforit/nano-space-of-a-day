package me.lukeforit.spaceofaday.ui.archive

import androidx.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.ViewGroup

import me.lukeforit.spaceofaday.R
import me.lukeforit.spaceofaday.databinding.ListItemApodBinding
import me.lukeforit.spaceofaday.databinding.ListItemApodEmptyBinding
import me.lukeforit.spaceofaday.ui.archive.items.ApodViewHolder
import me.lukeforit.spaceofaday.ui.archive.items.ArchiveItem
import me.lukeforit.spaceofaday.ui.archive.items.EmptyApodViewHolder
import me.lukeforit.spaceofaday.ui.base.DataBindingAdapter
import me.lukeforit.spaceofaday.ui.base.DataBindingViewHolder

class ApodArchiveAdapter internal constructor(data: List<ArchiveItem>) : DataBindingAdapter<ArchiveItem>(data) {

    override fun getItemViewType(position: Int): Int {
        return data[position].layoutRes
    }

    override fun buildViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder<out ViewDataBinding, ArchiveItem> {
        //TODO change ugly casting to another solution
        return when (viewType) {
            R.layout.list_item_apod -> ApodViewHolder(ListItemApodBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)) as DataBindingViewHolder<out ViewDataBinding, ArchiveItem>
            R.layout.list_item_apod_empty -> EmptyApodViewHolder(ListItemApodEmptyBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)) as DataBindingViewHolder<out ViewDataBinding, ArchiveItem>
            else -> throw IllegalStateException("")
        }
    }
}
