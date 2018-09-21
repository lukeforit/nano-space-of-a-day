package me.lukeforit.spaceofaday.ui.archive.items

import me.lukeforit.spaceofaday.R

class EmptyApodItem(listener: ArchiveItem.OnArchiveItemClickListener, var date: String?) : ArchiveItem(listener) {

    override val layoutRes: Int
        get() = R.layout.list_item_apod_empty
}
