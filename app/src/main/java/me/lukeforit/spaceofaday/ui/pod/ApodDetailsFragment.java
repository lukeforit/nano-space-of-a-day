package me.lukeforit.spaceofaday.ui.pod;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.lukeforit.spaceofaday.R;
import me.lukeforit.spaceofaday.databinding.FragmentApodDetailsBinding;
import me.lukeforit.spaceofaday.ui.base.DIFragment;

public class ApodDetailsFragment extends DIFragment<ApodDetailsViewModel, FragmentApodDetailsBinding> {

    private static final String ARG_APOD_ID = "apod_id";

    private String apodId;

    public ApodDetailsFragment() {
    }

    public static ApodDetailsFragment newInstance(String param1, String param2) {
        ApodDetailsFragment fragment = new ApodDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_APOD_ID, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            apodId = getArguments().getString(ARG_APOD_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_apod_details, container, false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    protected Class<ApodDetailsViewModel> getViewModelClass() {
        return ApodDetailsViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return 0;
    }

    @Override
    protected void bind() {

    }
}
