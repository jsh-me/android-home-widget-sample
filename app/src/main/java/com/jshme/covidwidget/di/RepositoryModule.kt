package com.jshme.covidwidget.di

import com.jshme.covidwidget.data.repository.CovidRepositoryImpl
import com.jshme.covidwidget.domain.repository.CovidRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideCovidRepository(
        repositoryImpl: CovidRepositoryImpl
    ): CovidRepository

}
