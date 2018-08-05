package me.lukeforit.spaceofaday.ui.archive;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import java.util.Collections;
import java.util.List;

import me.lukeforit.spaceofaday.R;
import me.lukeforit.spaceofaday.data.model.Apod;
import me.lukeforit.spaceofaday.databinding.FragmentApodArchiveBinding;
import me.lukeforit.spaceofaday.ui.base.DIFragment;

public class ApodArchiveFragment extends DIFragment<ApodArchiveViewModel, FragmentApodArchiveBinding> {

    private ApodArchiveAdapter adapter = new ApodArchiveAdapter(Collections.<Apod>emptyList());

    public ApodArchiveFragment() {
    }

    public static ApodArchiveFragment newInstance() {
        return new ApodArchiveFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.init();
        viewModel.getApodListLiveData().observe(this, new Observer<List<Apod>>() {
            @Override
            public void onChanged(@Nullable List<Apod> list) {
                adapter.setData(list);
                adapter.notifyDataSetChanged();
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
