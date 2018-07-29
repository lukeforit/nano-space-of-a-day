package me.lukeforit.spaceofaday.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import dagger.Binds;
import dagger.MapKey;
import dagger.Module;
import dagger.multibindings.IntoMap;
import me.lukeforit.spaceofaday.ui.base.DIViewModelFactory;
import me.lukeforit.spaceofaday.ui.pod.ApodDetailsViewModel;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(DIViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(ApodDetailsViewModel.class)
    abstract ViewModel bindApodDetailsViewModel(ApodDetailsViewModel apodDetailsViewModel);

    @Retention(RetentionPolicy.RUNTIME)
    @MapKey
    @interface ViewModelKey {
        Class<? extends ViewModel> value();
    }
}
