package com.iamgjm.fibonacidroid.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iamgjm.fibonacidroid.domain.NavigationEvent

interface IShared {
    fun navigate(event: NavigationEvent)
}

class FibonacciSharedViewModel : ViewModel(), IShared {

    private val _navigationEvent = MutableLiveData<NavigationEvent>()
    val navigationEvent: LiveData<NavigationEvent>
        get() = _navigationEvent

    override fun navigate(event: NavigationEvent) {
        _navigationEvent.postValue(event)
    }
}