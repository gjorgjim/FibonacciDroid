package com.iamgjm.fibonacidroid.base.presentation

import androidx.lifecycle.ViewModel
import com.iamgjm.fibonacidroid.base.domain.Logout
import com.iamgjm.fibonacidroid.presentation.IShared
import com.iamgjm.fibonacidroid.presentation.NavigationEvent

open class BaseViewModel(
    private val sharedViewModel: IShared
) : ViewModel() {

    private val logout = Logout()

    val goTo = { event: NavigationEvent ->
        sharedViewModel.navigate(event)
    }

    fun signOut() {
        logout()
        sharedViewModel.navigate(NavigationEvent.Login)
    }

    open fun goBack() {
        //do nothing on purpose
    }
}