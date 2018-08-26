package me.lukeforit.spaceofaday.ui.archive.items;

import android.support.annotation.NonNull;

import me.lukeforit.spaceofaday.databinding.ListItemApodEmptyBinding;
import me.lukeforit.spaceofaday.ui.base.DataBindingViewHolder;

public class EmptyApodViewHolder extends DataBindingViewHolder<ListItemApodEmptyBinding, EmptyApodItem>{

    public EmptyApodViewHolder(@NonNull ListItemApodEmptyBinding binding) {
        super(binding);
    }

    @Override
    public void bind(@NonNull EmptyApodItem data) {
        binding.setItem(data.getDate());
    }
}
