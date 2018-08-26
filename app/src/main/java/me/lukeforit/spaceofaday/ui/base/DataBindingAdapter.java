package me.lukeforit.spaceofaday.ui.base;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public abstract class DataBindingAdapter<D> extends RecyclerView.Adapter<DataBindingViewHolder<? extends ViewDataBinding, D>> {

    protected List<D> data;

    public DataBindingAdapter(@NonNull List<D> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public DataBindingViewHolder<? extends ViewDataBinding, D> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return buildViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull DataBindingViewHolder<? extends ViewDataBinding, D> holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<D> data) {
        this.data = data;
    }

    public abstract DataBindingViewHolder<? extends ViewDataBinding, D> buildViewHolder(@NonNull ViewGroup parent, int viewType);
}
