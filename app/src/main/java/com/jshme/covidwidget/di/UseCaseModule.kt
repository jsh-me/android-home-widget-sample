package com.jshme.covidwidget.di

import com.jshme.covidwidget.domain.usecase.BaseUseCase
import com.jshme.covidwidget.domain.usecase.GetCovidCount
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class UseCaseModule {

    @Binds
    abstract fun bindsGetCovidCounts(
        getCovidCount: GetCovidCount
    ): BaseUseCase

}
