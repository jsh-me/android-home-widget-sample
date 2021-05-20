package com.jshme.covidwidget.presentation.ui.main

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jshme.covidwidget.domain.usecase.GetCountryCovidCount
import com.jshme.covidwidget.domain.usecase.GetDomesticCovidCount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getDomesticCovidCount: GetDomesticCovidCount,
    private val getCountryCovidCount: GetCountryCovidCount
) : ViewModel() {
    var mainViewState = MutableLiveData<MainViewState>(MainViewState.UnInitialized)


    init {
        loadData()
    }

    fun refresh() {
        loadData()
    }

    @VisibleForTesting
    private fun loadData() {
        mainViewState.value = MainViewState.Loading
        viewModelScope.launch {
            try {
                getCountryCovidCount().zip(getDomesticCovidCount()) { countryCovidCounterEntity, domesticCovidCounterEntity ->
                    mainViewState.value = MainViewState.Success(
                        domesticCovidCounterEntity,
                        countryCovidCounterEntity
                    )
                }.collect()
            } catch (e: Exception) {
                mainViewState.value = MainViewState.Error(e.localizedMessage)
            }
        }
    }
}
