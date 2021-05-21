package com.jshme.covidwidget.presentation.ui.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddWidgetViewModel : ViewModel() {

    var titleText = MutableLiveData<String>()
    var descriptionText = MutableLiveData<String>()

    var leftIconChecked = MutableLiveData<Boolean>(false)
    var rightIconChecked = MutableLiveData<Boolean>(false)

    fun onClickLeftContainer() {
        leftIconChecked.value = true
        rightIconChecked.value = false
    }

    fun onClickRightContainer() {
        rightIconChecked.value = true
        leftIconChecked.value = false
    }

}
