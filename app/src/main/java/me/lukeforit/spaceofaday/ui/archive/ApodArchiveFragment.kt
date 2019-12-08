package me.lukeforit.spaceofaday.ui.archive

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import me.lukeforit.spaceofaday.R
import me.lukeforit.spaceofaday.databinding.FragmentApodArchiveBinding
import me.lukeforit.spaceofaday.ui.base.DIFragment
import me.lukeforit.spaceofaday.ui.home.HomeViewModel
import java.util.*

class ApodArchiveFragment : DIFragment<ApodArchiveViewModel, FragmentApodArchiveBinding>() {

    override val viewModelClass: Class<ApodArchiveViewModel>
        get() = ApodArchiveViewModel::class.java
    override val layoutRes: Int
        get() = R.layout.fragment_apod_archive

    private val adapter = ApodArchiveAdapter(emptyList())
    private var homeViewModel: HomeViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.init()
        }
        homeViewModel = ViewModelProviders.of(Objects.requireNonNull<androidx.fragment.app.FragmentActivity>(activity), viewModelFactory).get(HomeViewModel::class.java)

        viewModel.archiveItemListLiveData.observe(this, Observer { list ->
            adapter.data = list ?: emptyList()
            adapter.notifyDataSetChanged()
        })
        viewModel.displayApodEventLiveData.observe(this, Observer { stringEvent ->
            if (stringEvent != null && !stringEvent.isDelivered) {
                homeViewModel!!.displayApod(stringEvent.deliverData() ?: "")
            }
        })
    }

    override fun bind() {
        binding.vm = viewModel
        binding.archiveListRv.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        binding.archiveListRv.adapter = adapter
    }

    companion object {

        fun newInstance(): ApodArchiveFragment {
            return ApodArchiveFragment()
        }
    }
}
