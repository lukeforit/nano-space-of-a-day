package me.lukeforit.spaceofaday.ui.home

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

import javax.inject.Inject

import me.lukeforit.spaceofaday.ui.base.Event

class HomeViewModel @Inject
constructor() : ViewModel() {

    val openApodScreen = MutableLiveData<Event<String>>()

    fun displayApod(date: String) {
        openApodScreen.postValue(Event(date))
    }
}
