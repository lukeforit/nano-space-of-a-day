package me.lukeforit.spaceofaday.ui.archive.items

import android.support.annotation.LayoutRes

abstract class ArchiveItem(val listener: OnArchiveItemClickListener) {

    @get:LayoutRes
    abstract val layoutRes: Int

    interface OnArchiveItemClickListener {
        fun onClick(date: String)
    }
}
