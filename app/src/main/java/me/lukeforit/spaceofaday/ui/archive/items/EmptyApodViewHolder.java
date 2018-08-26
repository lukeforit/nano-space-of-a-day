package me.lukeforit.spaceofaday.ui.archive.items;

import android.support.annotation.NonNull;
import android.view.View;

import me.lukeforit.spaceofaday.databinding.ListItemApodEmptyBinding;
import me.lukeforit.spaceofaday.ui.base.DataBindingViewHolder;

public class EmptyApodViewHolder extends DataBindingViewHolder<ListItemApodEmptyBinding, EmptyApodItem>{

    public EmptyApodViewHolder(@NonNull ListItemApodEmptyBinding binding) {
        super(binding);
    }

    @Override
    public void bind(@NonNull final EmptyApodItem data) {
        binding.setItem(data.getDate());
        binding.moreIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.getListener().onClick(data.getDate());
            }
        });
    }
}
