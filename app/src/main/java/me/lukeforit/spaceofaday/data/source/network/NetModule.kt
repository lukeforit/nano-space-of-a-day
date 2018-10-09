package me.lukeforit.spaceofaday.data.source.network

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import me.lukeforit.spaceofaday.BuildConfig
import me.lukeforit.spaceofaday.data.source.ApodRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class NetModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.baseUrl)
                .client(OkHttpClient.Builder()
                        .addInterceptor(HttpLoggingInterceptor()
                                .setLevel(HttpLoggingInterceptor.Level.BODY))
                        .build())
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun provideApodRestService(retrofit: Retrofit): ApodRestService {
        return retrofit.create(ApodRestService::class.java)
    }

    @Provides
    @Singleton
    fun provideApodRepository(service: ApodRestService): ApodRepository {
        return ApodRepositoryNet(service)
    }
}
