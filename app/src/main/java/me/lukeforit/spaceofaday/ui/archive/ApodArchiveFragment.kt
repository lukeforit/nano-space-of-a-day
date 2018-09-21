package me.lukeforit.spaceofaday.ui.archive

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import me.lukeforit.spaceofaday.R
import me.lukeforit.spaceofaday.databinding.FragmentApodArchiveBinding
import me.lukeforit.spaceofaday.ui.base.DIFragment
import me.lukeforit.spaceofaday.ui.home.HomeViewModel
import java.util.*

class ApodArchiveFragment : DIFragment<ApodArchiveViewModel, FragmentApodArchiveBinding>() {

    private val adapter = ApodArchiveAdapter(emptyList())
    private var homeViewModel: HomeViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.init()
        }
        homeViewModel = ViewModelProviders.of(Objects.requireNonNull<FragmentActivity>(activity), viewModelFactory).get(HomeViewModel::class.java)

        viewModel.archiveItemListLiveData.observe(this, Observer { list ->
            adapter.setData(list)
            adapter.notifyDataSetChanged()
        })
        viewModel.displayApodEventLiveData.observe(this, Observer { stringEvent ->
            if (stringEvent != null && !stringEvent.isDelivered) {
                homeViewModel!!.displayApod(stringEvent.deliverData())
            }
        })
    }

    override fun getViewModelClass(): Class<ApodArchiveViewModel> {
        return ApodArchiveViewModel::class.java
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_apod_archive
    }

    override fun bind() {
        binding.vm = viewModel
        binding.archiveListRv.layoutManager = LinearLayoutManager(context)
        binding.archiveListRv.adapter = adapter
    }

    companion object {

        fun newInstance(): ApodArchiveFragment {
            return ApodArchiveFragment()
        }
    }
}
