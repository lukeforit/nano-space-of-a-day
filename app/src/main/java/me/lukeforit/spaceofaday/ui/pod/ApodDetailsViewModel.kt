package me.lukeforit.spaceofaday.ui.pod

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log

import javax.inject.Inject

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import me.lukeforit.spaceofaday.data.model.Apod
import me.lukeforit.spaceofaday.data.source.SpaceRepository

class ApodDetailsViewModel @Inject
constructor() : ViewModel() {

    val apodLiveData = MutableLiveData<Apod>()

    @Inject
    internal lateinit var repository: SpaceRepository

    private val disposable = CompositeDisposable()

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

    internal fun init(apodId: String) {
        disposable.add(
                repository!!.fetchApod(apodId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ apod -> apodLiveData.postValue(apod) }, { Log.d("ERROR", "error") }))
    }
}
