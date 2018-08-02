package me.lukeforit.spaceofaday.ui.archive;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import java.util.Collections;

import me.lukeforit.spaceofaday.R;
import me.lukeforit.spaceofaday.data.model.Apod;
import me.lukeforit.spaceofaday.databinding.FragmentApodArchiveBinding;
import me.lukeforit.spaceofaday.ui.base.DIFragment;

public class ApodArchiveFragment extends DIFragment<ApodArchiveViewModel, FragmentApodArchiveBinding> {

    public ApodArchiveFragment() {
    }

    public static ApodArchiveFragment newInstance() {
        return new ApodArchiveFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.init();
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
        binding.archiveListRv.setAdapter(new ApodArchiveAdapter(Collections.<Apod>emptyList()));
    }
}
