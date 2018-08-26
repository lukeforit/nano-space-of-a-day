package me.lukeforit.spaceofaday.ui.archive.items;

import android.support.annotation.NonNull;

import me.lukeforit.spaceofaday.R;
import me.lukeforit.spaceofaday.data.model.Apod;

public class ApodItem extends ArchiveItem {

    @NonNull
    private Apod apod;

    public ApodItem(OnArchiveItemClickListener listener, @NonNull Apod apod) {
        super(listener);
        this.apod = apod;
    }

    @NonNull
    public Apod getApod() {
        return apod;
    }

    public void setApod(@NonNull Apod apod) {
        this.apod = apod;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.list_item_apod;
    }
}
