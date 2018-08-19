package me.lukeforit.spaceofaday.ui.base;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import me.lukeforit.spaceofaday.common.SpaceApp;

public abstract class DIFragment<VM extends ViewModel, B extends ViewDataBinding> extends Fragment {

    @Inject
    protected DIViewModelFactory viewModelFactory;
    protected VM viewModel;
    protected B binding;

    private Tracker tracker;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModelClass());

        SpaceApp application = (SpaceApp) Objects.requireNonNull(getContext()).getApplicationContext();
        tracker = application.getDefaultTracker();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
        binding.setLifecycleOwner(this);
        bind();
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        tracker.setScreenName("Fragment-" + this.getClass().getSimpleName());
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    //TODO find better solution because it allows viewModel.getClass() to be != getViewModelClass()
    protected abstract Class<VM> getViewModelClass();

    @LayoutRes
    protected abstract int getLayoutRes();

    protected abstract void bind();
}
