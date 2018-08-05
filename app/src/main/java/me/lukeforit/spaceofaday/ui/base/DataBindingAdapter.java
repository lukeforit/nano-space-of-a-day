package me.lukeforit.spaceofaday.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

public abstract class DataBindingAdapter<B extends ViewDataBinding, D> extends RecyclerView.Adapter<DataBindingViewHolder<B, D>> {

    private List<D> data;

    public DataBindingAdapter(@NonNull List<D> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public DataBindingViewHolder<B, D> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        B binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                getLayoutRes(), parent, false);
        return new DataBindingViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DataBindingViewHolder<B, D> holder, int position) {
        holder.bindVariable(getBindingVariable(), data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<D> data) {
        this.data = data;
    }

    @LayoutRes
    protected abstract int getLayoutRes();

    protected abstract int getBindingVariable();
}
