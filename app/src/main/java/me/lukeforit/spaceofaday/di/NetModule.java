package me.lukeforit.spaceofaday.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.lukeforit.spaceofaday.BuildConfig;
import me.lukeforit.spaceofaday.data.source.SpaceRepository;
import me.lukeforit.spaceofaday.data.source.network.SpaceRepositoryNet;
import me.lukeforit.spaceofaday.data.source.network.SpaceRestService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
public class NetModule {

    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.baseUrl)
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new HttpLoggingInterceptor()
                                .setLevel(HttpLoggingInterceptor.Level.BODY))
                        .build())
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public SpaceRestService provideSpaceRestService(Retrofit retrofit) {
        return retrofit.create(SpaceRestService.class);
    }

    @Provides
    @Singleton
    public SpaceRepository provideSpaceRepository(SpaceRestService service) {
        return new SpaceRepositoryNet(service);
    }
}
