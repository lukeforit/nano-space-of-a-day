package me.lukeforit.spaceofaday.ui.base

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

abstract class DataBindingAdapter<D>(var data: List<D>) : RecyclerView.Adapter<DataBindingViewHolder<out ViewDataBinding, D>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder<out ViewDataBinding, D> {
        return buildViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder<out ViewDataBinding, D>, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    abstract fun buildViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder<out ViewDataBinding, D>
}
