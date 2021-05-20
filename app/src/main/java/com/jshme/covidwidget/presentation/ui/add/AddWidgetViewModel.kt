package com.jshme.covidwidget.presentation.ui.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddWidgetViewModel : ViewModel() {

    var titleText = MutableLiveData<String>()
    var descriptionText = MutableLiveData<String>()

}
