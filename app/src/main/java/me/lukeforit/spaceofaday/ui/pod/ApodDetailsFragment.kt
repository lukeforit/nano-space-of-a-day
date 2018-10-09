package me.lukeforit.spaceofaday.ui.pod

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Layout
import android.text.TextUtils

import me.lukeforit.spaceofaday.R
import me.lukeforit.spaceofaday.common.Utils
import me.lukeforit.spaceofaday.databinding.FragmentApodDetailsBinding
import me.lukeforit.spaceofaday.ui.base.DIFragment

class ApodDetailsFragment : DIFragment<ApodDetailsViewModel, FragmentApodDetailsBinding>() {

    private var apodId: String? = null

    override val viewModelClass: Class<ApodDetailsViewModel>
        get() = ApodDetailsViewModel::class.java

    override val layoutRes: Int
        get() = R.layout.fragment_apod_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            apodId = arguments!!.getString(ARG_APOD_ID)
        }
        if (TextUtils.isEmpty(apodId)) {
            apodId = Utils.defaultDateAsString
        }
        if (savedInstanceState == null) {
            viewModel.init(apodId!!)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(binding.mainToolbar)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            binding.explanationTv.justificationMode = Layout.JUSTIFICATION_MODE_INTER_WORD
        }
    }

    override fun bind() {
        binding.vm = viewModel
    }

    companion object {

        private val ARG_APOD_ID = "apod_id"

        fun newInstance(apod: String): ApodDetailsFragment {
            val fragment = ApodDetailsFragment()
            val args = Bundle()
            args.putString(ARG_APOD_ID, apod)
            fragment.arguments = args
            return fragment
        }
    }
}
