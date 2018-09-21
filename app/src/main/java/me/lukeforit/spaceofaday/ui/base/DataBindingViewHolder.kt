package me.lukeforit.spaceofaday.ui.base

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView

open class DataBindingViewHolder<B : ViewDataBinding, D>(protected var binding: B) : RecyclerView.ViewHolder(binding.root) {

    open fun bind(data: D) {}
}
