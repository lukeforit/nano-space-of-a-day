package me.lukeforit.spaceofaday.ui.archive.items

import android.os.Build
import android.text.Layout
import android.view.View

import me.lukeforit.spaceofaday.databinding.ListItemApodBinding
import me.lukeforit.spaceofaday.ui.base.DataBindingViewHolder

class ApodViewHolder(binding: ListItemApodBinding) : DataBindingViewHolder<ListItemApodBinding, ApodItem>(binding) {

    override fun bind(data: ApodItem) {
        binding.item = data.apod
        binding.moreIv.setOnClickListener { data.listener.onClick(data.apod.date!!) }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            binding.explanationTv.justificationMode = Layout.JUSTIFICATION_MODE_INTER_WORD
        }
    }
}
