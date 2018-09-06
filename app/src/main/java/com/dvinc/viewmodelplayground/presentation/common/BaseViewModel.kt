package com.dvinc.viewmodelplayground.presentation.common

import android.arch.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    //TODO: Add compositeDisp here

    override fun onCleared() {
        super.onCleared()
        //TODO: clear subscs here
    }
}
