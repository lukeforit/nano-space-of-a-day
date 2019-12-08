package me.lukeforit.spaceofaday.ui.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class DataBindingViewHolder<B : ViewDataBinding, D>(protected var binding: B) : RecyclerView.ViewHolder(binding.root) {

    open fun bind(data: D) {}
}
