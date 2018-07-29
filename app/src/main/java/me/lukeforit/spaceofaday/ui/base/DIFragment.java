package me.lukeforit.spaceofaday.ui.base;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public abstract class DIFragment<VM extends ViewModel> extends Fragment {

    @Inject
    protected DIViewModelFactory viewModelFactory;
    protected VM viewModel;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModelClass());
    }

    //TODO find better solution because it allows viewModel.getClass() to be != getViewModelClass()
    protected abstract Class<VM> getViewModelClass();
}
