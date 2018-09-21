package me.lukeforit.spaceofaday.service

import com.firebase.jobdispatcher.JobParameters
import com.firebase.jobdispatcher.JobService
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.lukeforit.spaceofaday.common.Utils
import me.lukeforit.spaceofaday.data.source.SpaceRepository
import me.lukeforit.spaceofaday.ui.widget.ApodWidget
import javax.inject.Inject

class FetchApodFirebaseJobService : JobService() {

    @Inject
    internal var repository: SpaceRepository? = null

    private val disposable = CompositeDisposable()

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }

    override fun onStartJob(job: JobParameters): Boolean {
        disposable.add(
                repository!!.fetchApod(Utils.defaultDateAsString)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            jobFinished(job, false)
                            ApodWidget.triggerWidgetUpdate(applicationContext)
                        }, {
                            //TODO log error
                            jobFinished(job, true)
                        })
        )
        return true
    }

    override fun onStopJob(job: JobParameters): Boolean {
        disposable.clear()
        return true
    }
}
