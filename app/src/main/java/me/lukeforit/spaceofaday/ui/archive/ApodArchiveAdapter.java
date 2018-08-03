package me.lukeforit.spaceofaday.ui.archive;

import android.support.annotation.NonNull;

import java.util.List;

import me.lukeforit.spaceofaday.BR;
import me.lukeforit.spaceofaday.R;
import me.lukeforit.spaceofaday.data.model.Apod;
import me.lukeforit.spaceofaday.databinding.ListItemApodBinding;
import me.lukeforit.spaceofaday.ui.base.DataBindingAdapter;

public class ApodArchiveAdapter extends DataBindingAdapter<ListItemApodBinding, Apod> {

    public ApodArchiveAdapter(@NonNull List<Apod> data) {
        super(data);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.list_item_apod;
    }

    @Override
    protected int getBindingVariable() {
        return BR.apod;
    }
}
