package me.lukeforit.spaceofaday.ui.archive.items;

import me.lukeforit.spaceofaday.R;

public class EmptyApodItem extends ArchiveItem {
    private String date;

    public EmptyApodItem(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.list_item_apod_empty;
    }
}
