package me.lukeforit.spaceofaday.ui.pod;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.text.TextUtils;

import me.lukeforit.spaceofaday.R;
import me.lukeforit.spaceofaday.common.Utils;
import me.lukeforit.spaceofaday.databinding.FragmentApodDetailsBinding;
import me.lukeforit.spaceofaday.ui.base.DIFragment;

public class ApodDetailsFragment extends DIFragment<ApodDetailsViewModel, FragmentApodDetailsBinding> {

    private static final String ARG_APOD_ID = "apod_id";

    private String apodId;

    public ApodDetailsFragment() {
    }

    public static ApodDetailsFragment newInstance(String apod) {
        ApodDetailsFragment fragment = new ApodDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_APOD_ID, apod);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            apodId = getArguments().getString(ARG_APOD_ID);
        }
        if (TextUtils.isEmpty(apodId)) {
            apodId = Utils.INSTANCE.getDefaultDateAsString();
        }
        if (savedInstanceState == null) {
            viewModel.init(apodId);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof AppCompatActivity) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(binding.mainToolbar);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            binding.explanationTv.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        }
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
