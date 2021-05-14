package com.jshme.covidwidget.di

import com.google.gson.GsonBuilder
import com.google.gson.internal.GsonBuildConfig
import com.jshme.covidwidget.BuildConfig
import com.jshme.covidwidget.PrivateConst.BASE_URL
import com.jshme.covidwidget.data.api.CovidService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        // log
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
        }

        // timeout
        builder.readTimeout(30, TimeUnit.SECONDS)
        builder.connectTimeout(10, TimeUnit.SECONDS)

        return builder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Singleton
    @Provides
    fun provideCovidService(
        retrofit: Retrofit
    ): CovidService = retrofit.create(CovidService::class.java)
}
