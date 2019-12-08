package me.lukeforit.spaceofaday.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import me.lukeforit.spaceofaday.ui.archive.ApodArchiveViewModel
import me.lukeforit.spaceofaday.ui.base.DIViewModelFactory
import me.lukeforit.spaceofaday.ui.home.HomeViewModel
import me.lukeforit.spaceofaday.ui.pod.ApodDetailsViewModel
import me.lukeforit.spaceofaday.ui.widget.config.ApodWidgetConfigureViewModel
import kotlin.reflect.KClass

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: DIViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ApodDetailsViewModel::class)
    internal abstract fun bindApodDetailsViewModel(apodDetailsViewModel: ApodDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ApodArchiveViewModel::class)
    internal abstract fun bindApodArchiveViewModel(apodArchiveViewModel: ApodArchiveViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ApodWidgetConfigureViewModel::class)
    internal abstract fun bindApodWidgetConfigureViewModel(apodWidgetConfigureViewModel: ApodWidgetConfigureViewModel): ViewModel

    @Retention(AnnotationRetention.RUNTIME)
    @MapKey
    internal annotation class ViewModelKey(val value: KClass<out ViewModel>)
}
