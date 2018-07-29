package me.lukeforit.spaceofaday.ui.base;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

public class DIViewModelFactory implements ViewModelProvider.Factory {

    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> viewModelProviderMap;

    @Inject
    public DIViewModelFactory(@NonNull Map<Class<? extends ViewModel>, Provider<ViewModel>> viewModelProviderMap) {
        this.viewModelProviderMap = viewModelProviderMap;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) viewModelProviderMap.get(modelClass).get();
    }
}
