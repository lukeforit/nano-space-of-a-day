package me.lukeforit.spaceofaday.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import dagger.Binds;
import dagger.MapKey;
import dagger.Module;
import dagger.multibindings.IntoMap;
import me.lukeforit.spaceofaday.ui.archive.ApodArchiveViewModel;
import me.lukeforit.spaceofaday.ui.base.DIViewModelFactory;
import me.lukeforit.spaceofaday.ui.home.HomeViewModel;
import me.lukeforit.spaceofaday.ui.pod.ApodDetailsViewModel;
import me.lukeforit.spaceofaday.ui.widget.config.ApodWidgetConfigureViewModel;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(DIViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    abstract ViewModel bindHomeViewModel(HomeViewModel homeViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ApodDetailsViewModel.class)
    abstract ViewModel bindApodDetailsViewModel(ApodDetailsViewModel apodDetailsViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ApodArchiveViewModel.class)
    abstract ViewModel bindApodArchiveViewModel(ApodArchiveViewModel apodArchiveViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ApodWidgetConfigureViewModel.class)
    abstract ViewModel bindApodWidgetConfigureViewModel(ApodWidgetConfigureViewModel apodWidgetConfigureViewModel);

    @Retention(RetentionPolicy.RUNTIME)
    @MapKey
    @interface ViewModelKey {
        Class<? extends ViewModel> value();
    }
}
