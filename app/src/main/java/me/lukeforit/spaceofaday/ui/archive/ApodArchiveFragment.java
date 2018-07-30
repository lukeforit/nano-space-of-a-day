package me.lukeforit.spaceofaday.ui.archive;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;

import me.lukeforit.spaceofaday.R;
import me.lukeforit.spaceofaday.data.model.Apod;
import me.lukeforit.spaceofaday.databinding.FragmentApodArchiveBinding;
import me.lukeforit.spaceofaday.ui.base.DIFragment;

public class ApodArchiveFragment extends DIFragment<ApodArchiveViewModel, FragmentApodArchiveBinding> {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;

    public ApodArchiveFragment() {
    }

    public static ApodArchiveFragment newInstance(int columnCount) {
        ApodArchiveFragment fragment = new ApodArchiveFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_apod_archive, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new ApodArchiveAdapter(Collections.<Apod>emptyList()));
        }
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    protected Class<ApodArchiveViewModel> getViewModelClass() {
        return ApodArchiveViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return 0;
    }

    @Override
    protected void bind() {

    }
}
