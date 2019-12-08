package me.lukeforit.spaceofaday.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import javax.inject.Inject

import me.lukeforit.spaceofaday.ui.base.Event

class HomeViewModel @Inject
constructor() : ViewModel() {

    val openApodScreen = MutableLiveData<Event<String>>()

    fun displayApod(date: String) {
        openApodScreen.postValue(Event(date))
    }
}
