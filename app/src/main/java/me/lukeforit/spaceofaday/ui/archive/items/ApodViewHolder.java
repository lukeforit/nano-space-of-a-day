package me.lukeforit.spaceofaday.ui.archive.items;

import android.os.Build;
import android.support.annotation.NonNull;
import android.text.Layout;
import android.view.View;

import me.lukeforit.spaceofaday.databinding.ListItemApodBinding;
import me.lukeforit.spaceofaday.ui.base.DataBindingViewHolder;

public class ApodViewHolder extends DataBindingViewHolder<ListItemApodBinding, ApodItem> {

    public ApodViewHolder(@NonNull ListItemApodBinding binding) {
        super(binding);
    }

    @Override
    public void bind(@NonNull final ApodItem data) {
        binding.setItem(data.getApod());
        binding.moreIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.getListener().onClick(data.getApod().getDate());
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            binding.explanationTv.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        }
    }
}
