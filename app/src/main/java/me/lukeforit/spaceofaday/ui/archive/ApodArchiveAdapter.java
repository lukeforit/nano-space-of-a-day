package me.lukeforit.spaceofaday.ui.archive;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import me.lukeforit.spaceofaday.R;
import me.lukeforit.spaceofaday.databinding.ListItemApodBinding;
import me.lukeforit.spaceofaday.databinding.ListItemApodEmptyBinding;
import me.lukeforit.spaceofaday.ui.archive.items.ApodViewHolder;
import me.lukeforit.spaceofaday.ui.archive.items.ArchiveItem;
import me.lukeforit.spaceofaday.ui.archive.items.EmptyApodViewHolder;
import me.lukeforit.spaceofaday.ui.base.DataBindingAdapter;
import me.lukeforit.spaceofaday.ui.base.DataBindingViewHolder;

public class ApodArchiveAdapter extends DataBindingAdapter<ArchiveItem> {

    ApodArchiveAdapter(@NonNull List<ArchiveItem> data) {
        super(data);
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getLayoutRes();
    }

    @Override
    public DataBindingViewHolder<? extends ViewDataBinding, ArchiveItem> buildViewHolder(@NonNull ViewGroup parent, int viewType) {
        //TODO change ugly casting to another solution
        switch (viewType) {
            case R.layout.list_item_apod:
                return (DataBindingViewHolder) new ApodViewHolder(ListItemApodBinding
                        .inflate(LayoutInflater.from(parent.getContext()), parent, false));
            case R.layout.list_item_apod_empty:
                return (DataBindingViewHolder) new EmptyApodViewHolder(ListItemApodEmptyBinding
                        .inflate(LayoutInflater.from(parent.getContext()), parent, false));
            default:
                throw new IllegalStateException("");
        }
    }
}
