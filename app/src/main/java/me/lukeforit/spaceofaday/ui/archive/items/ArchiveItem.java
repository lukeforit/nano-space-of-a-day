package me.lukeforit.spaceofaday.ui.archive.items;

import android.support.annotation.LayoutRes;

public abstract class ArchiveItem {

    private OnArchiveItemClickListener listener;

    public ArchiveItem(OnArchiveItemClickListener listener) {
        this.listener = listener;
    }

    @LayoutRes
    public abstract int getLayoutRes();

    public OnArchiveItemClickListener getListener() {
        return listener;
    }

    public interface OnArchiveItemClickListener {
        void onClick(String date);
    }
}
