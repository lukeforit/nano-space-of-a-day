package me.lukeforit.spaceofaday.ui.archive.items;

import android.support.annotation.NonNull;

import me.lukeforit.spaceofaday.databinding.ListItemApodBinding;
import me.lukeforit.spaceofaday.ui.base.DataBindingViewHolder;

public class ApodViewHolder extends DataBindingViewHolder<ListItemApodBinding, ApodItem> {

    public ApodViewHolder(@NonNull ListItemApodBinding binding) {
        super(binding);
    }

    @Override
    public void bind(@NonNull ApodItem data) {
        binding.setItem(data.getApod());
    }
}
