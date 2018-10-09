package me.lukeforit.spaceofaday.ui.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.gms.analytics.HitBuilders
import com.google.android.gms.analytics.Tracker

import java.util.Objects

import javax.inject.Inject

import dagger.android.support.AndroidSupportInjection
import me.lukeforit.spaceofaday.common.SpaceApp

abstract class DIFragment<VM : ViewModel, B : ViewDataBinding> : Fragment() {

    @Inject
    lateinit var viewModelFactory: DIViewModelFactory
    protected lateinit var viewModel: VM
    protected lateinit var binding: B

    private var tracker: Tracker? = null

    //TODO find better solution because it allows viewModel.getClass() to be != getViewModelClass()
    protected abstract val viewModelClass: Class<VM>

    @get:LayoutRes
    protected abstract val layoutRes: Int

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)

        val application = Objects.requireNonNull<Context>(context).applicationContext as SpaceApp
        tracker = application.defaultTracker
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        binding.setLifecycleOwner(this)
        bind()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        tracker!!.setScreenName("Fragment-" + this.javaClass.simpleName)
        tracker!!.send(HitBuilders.ScreenViewBuilder().build())
    }

    protected abstract fun bind()
}
