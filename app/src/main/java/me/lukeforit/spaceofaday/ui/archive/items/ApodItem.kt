package me.lukeforit.spaceofaday.ui.archive.items

import me.lukeforit.spaceofaday.R
import me.lukeforit.spaceofaday.data.model.Apod

class ApodItem(listener: ArchiveItem.OnArchiveItemClickListener, var apod: Apod) : ArchiveItem(listener) {
    override val layoutRes: Int
        get() = R.layout.list_item_apod
}
