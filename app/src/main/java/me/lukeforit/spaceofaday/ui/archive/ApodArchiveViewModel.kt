package me.lukeforit.spaceofaday.ui.archive

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log

import java.text.ParseException
import java.util.ArrayList
import java.util.Collections
import java.util.Comparator
import java.util.HashMap

import javax.inject.Inject

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import me.lukeforit.spaceofaday.common.Utils
import me.lukeforit.spaceofaday.data.model.Apod
import me.lukeforit.spaceofaday.data.source.SpaceRepository
import me.lukeforit.spaceofaday.ui.archive.items.ApodItem
import me.lukeforit.spaceofaday.ui.archive.items.ArchiveItem
import me.lukeforit.spaceofaday.ui.archive.items.EmptyApodItem
import me.lukeforit.spaceofaday.ui.base.Event

class ApodArchiveViewModel @Inject
constructor() : ViewModel() {

    @Inject
    internal lateinit var repository: SpaceRepository
    val archiveItemListLiveData = MutableLiveData<List<ArchiveItem>>()
    val displayApodEventLiveData = MutableLiveData<Event<String>>()

    private val disposable = CompositeDisposable()

    private val onApodClickListener = object : ArchiveItem.OnArchiveItemClickListener {
        override fun onClick(date: String) {
            displayApodEventLiveData.postValue(Event(date))
        }
    }
    private val onEmptyApodClickListener = object : ArchiveItem.OnArchiveItemClickListener {
        override fun onClick(date: String) {
            displayApodEventLiveData.postValue(Event(date))
        }
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

    internal fun init() {
        disposable.add(
                repository!!.fetchAllApods()
                        .map { list ->
                            list.toMutableList().sortWith(Comparator { o1, o2 -> o1.date!!.compareTo(o2.date!!) })
                            list
                        }
                        .map { list ->
                            val apodsByDate = HashMap<String, Apod>()
                            for (apod in list) {
                                apodsByDate[apod.date!!] = apod
                            }
                            val dates = Utils.getDatesInRange(
                                    list[0].date!!,
                                    list[list.size - 1].date!!,
                                    10)
                            val result = ArrayList<ArchiveItem>()
                            for (date in dates) {
                                if (apodsByDate.containsKey(date)) {
                                    result.add(ApodItem(onApodClickListener, apodsByDate[date]!!))
                                } else {
                                    result.add(EmptyApodItem(onEmptyApodClickListener, date))
                                }
                            }
                            result
                        }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ list -> archiveItemListLiveData.postValue(list) }, { Log.d("ERROR", "error") }))
    }
}
