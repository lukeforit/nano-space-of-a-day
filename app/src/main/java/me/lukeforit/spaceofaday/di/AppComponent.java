package me.lukeforit.spaceofaday.di;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import me.lukeforit.spaceofaday.common.SpaceApp;

@Singleton
@Component(modules = {
        AppModule.class,
        FragmentContributorModule.class,
        ActivityContributorModule.class,
        AndroidInjectionModule.class
})
public interface AppComponent {
    void inject(SpaceApp app);

    @Component.Builder
    interface Builder {
        AppComponent build();

        @BindsInstance
        Builder appModule(SpaceApp app);
    }

}