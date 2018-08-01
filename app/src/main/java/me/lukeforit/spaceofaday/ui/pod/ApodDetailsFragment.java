package me.lukeforit.spaceofaday.ui.pod;

import android.os.Bundle;
import android.support.annotation.Nullable;

import me.lukeforit.spaceofaday.R;
import me.lukeforit.spaceofaday.databinding.FragmentApodDetailsBinding;
import me.lukeforit.spaceofaday.ui.base.DIFragment;

public class ApodDetailsFragment extends DIFragment<ApodDetailsViewModel, FragmentApodDetailsBinding> {

    private static final String ARG_APOD_ID = "apod_id";

    private String apodId;

    public ApodDetailsFragment() {
    }

    public static ApodDetailsFragment newInstance(String param1) {
        ApodDetailsFragment fragment = new ApodDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_APOD_ID, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            apodId = getArguments().getString(ARG_APOD_ID);
        }
        viewModel.init();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected Class<ApodDetailsViewModel> getViewModelClass() {
        return ApodDetailsViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_apod_details;
    }

    @Override
    protected void bind() {
        binding.setVm(viewModel);
    }
}
