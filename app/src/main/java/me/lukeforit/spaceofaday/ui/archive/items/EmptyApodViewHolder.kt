package me.lukeforit.spaceofaday.ui.archive.items

import me.lukeforit.spaceofaday.databinding.ListItemApodEmptyBinding
import me.lukeforit.spaceofaday.ui.base.DataBindingViewHolder

class EmptyApodViewHolder(binding: ListItemApodEmptyBinding) : DataBindingViewHolder<ListItemApodEmptyBinding, EmptyApodItem>(binding) {

    override fun bind(data: EmptyApodItem) {
        binding.item = data.date
        binding.moreIv.setOnClickListener { data.listener.onClick(data.date!!) }
    }
}
