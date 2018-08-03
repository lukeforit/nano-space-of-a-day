package me.lukeforit.spaceofaday.ui.base;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

class DataBindingViewHolder<B extends ViewDataBinding, D> extends RecyclerView.ViewHolder {

    private B binding;

    DataBindingViewHolder(B binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    void bindVariable(int br, D data){
        binding.setVariable(br, data);
        binding.executePendingBindings();
    }
}
