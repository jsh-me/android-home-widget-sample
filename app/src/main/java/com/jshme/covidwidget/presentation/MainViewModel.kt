package com.jshme.covidwidget.presentation

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jshme.covidwidget.domain.usecase.GetCovidCount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCovidCount: GetCovidCount
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
                getCovidCount().collectLatest { entity ->
                        mainViewState.value = MainViewState.Success(entity)
                    }
            } catch (e: Exception) {
                mainViewState.value = MainViewState.Error(e.localizedMessage)
            }
        }
    }
}
