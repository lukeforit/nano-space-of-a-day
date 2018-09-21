package me.lukeforit.spaceofaday.ui.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

import javax.inject.Inject
import javax.inject.Provider

class DIViewModelFactory @Inject
constructor(private val viewModelProviderMap: Map<Class<out ViewModel>, Provider<ViewModel>>) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModelProviderMap[modelClass]!!.get() as T
    }
}
