package me.lukeforit.spaceofaday.ui.archive;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import me.lukeforit.spaceofaday.R;
import me.lukeforit.spaceofaday.databinding.FragmentApodArchiveBinding;
import me.lukeforit.spaceofaday.ui.archive.items.ArchiveItem;
import me.lukeforit.spaceofaday.ui.base.DIFragment;
import me.lukeforit.spaceofaday.ui.base.Event;
import me.lukeforit.spaceofaday.ui.home.HomeViewModel;

public class ApodArchiveFragment extends DIFragment<ApodArchiveViewModel, FragmentApodArchiveBinding> {

    private ApodArchiveAdapter adapter = new ApodArchiveAdapter(Collections.<ArchiveItem>emptyList());
    private HomeViewModel homeViewModel;

    public ApodArchiveFragment() {
    }

    public static ApodArchiveFragment newInstance() {
        return new ApodArchiveFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            viewModel.init();
        }
        homeViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity()), viewModelFactory).get(HomeViewModel.class);

        viewModel.getArchiveItemListLiveData().observe(this, new Observer<List<ArchiveItem>>() {
            @Override
            public void onChanged(@Nullable List<ArchiveItem> list) {
                adapter.setData(list);
                adapter.notifyDataSetChanged();
            }
        });
        viewModel.getDisplayApodEventLiveData().observe(this, new Observer<Event<String>>() {
            @Override
            public void onChanged(@Nullable Event<String> stringEvent) {
                if (stringEvent != null && !stringEvent.isDelivered()) {
                    homeViewModel.displayApod(stringEvent.deliverData());
                }
            }
        });
    }

    @Override
    protected Class<ApodArchiveViewModel> getViewModelClass() {
        return ApodArchiveViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_apod_archive;
    }

    @Override
    protected void bind() {
        binding.setVm(viewModel);
        binding.archiveListRv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.archiveListRv.setAdapter(adapter);
    }
}
