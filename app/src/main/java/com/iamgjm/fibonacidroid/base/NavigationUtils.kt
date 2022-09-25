package com.iamgjm.fibonacidroid.base

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.iamgjm.fibonacidroid.R
import com.iamgjm.fibonacidroid.presentation.FibonacciActivity


fun FibonacciActivity.toFragment(fragment: Fragment) {
    hideKeyboard(this)
    supportFragmentManager
        .beginTransaction()
        .add(R.id.fragmentContainer, fragment, fragment::class.java.simpleName)
        .addToBackStack(fragment::class.java.simpleName)
        .commitAllowingStateLoss()
}

fun FibonacciActivity.goBack() {
    hideKeyboard(this)
    supportFragmentManager
        .popBackStackImmediate()
}

fun hideKeyboard(activity: Activity) {
    val imm: InputMethodManager =
        activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view: View? = activity.currentFocus
    if (view == null) {
        view = View(activity)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}