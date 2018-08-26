package me.lukeforit.spaceofaday.ui.base;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

public class DataBindingViewHolder<B extends ViewDataBinding, D> extends RecyclerView.ViewHolder {

    @NonNull
    protected B binding;

    public DataBindingViewHolder(@NonNull B binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(@NonNull D data){
    }
}
