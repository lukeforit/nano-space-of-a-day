package me.lukeforit.spaceofaday.di

import javax.inject.Singleton

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import me.lukeforit.spaceofaday.common.SpaceApp

@Singleton
@Component(modules = arrayOf(AppModule::class, FragmentContributorModule::class, ActivityContributorModule::class, ServiceContributorModule::class, AndroidInjectionModule::class))
interface AppComponent {
    fun inject(app: SpaceApp)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun appModule(app: SpaceApp): Builder
    }

}